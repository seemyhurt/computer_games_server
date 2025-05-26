package info.trsis.games.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.trsis.games.services.JPAHelper;
import info.trsis.games.storage.Developer;

public class DevelopersServlet extends javax.servlet.http.HttpServlet 
{
    private String htmlTemplate;
    private String styleTemplate;

    @Override
    public void init() throws ServletException {
        try {
            htmlTemplate = readResourceFile("/static/developers.html.in");
            styleTemplate = readResourceFile("/static/style.css");
        } catch (IOException e) {
            throw new ServletException("Failed to load template files", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(fillHtmlPage());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {
        try {
            HashMap<String, String> map = new HashMap<>();
            req.getParameterMap().forEach((key, values) -> {
                if (values != null && values.length > 0) {
                    map.put(key, values[0]);
                }
            });

            String name = map.get("name");
            String country = map.get("country");
            LocalDate foundedDate = LocalDate.parse(map.get("foundedDate"));

            if (name == null || name.trim().isEmpty() || 
                country == null || country.trim().isEmpty() ||
                foundedDate == null) {
                throw new IllegalArgumentException("Fields are invalid");
            }

            JPAHelper.insertDeveloper(name, country, foundedDate);
            
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"status\":\"success\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(e.getMessage());
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            JPAHelper.removeDeveloper(id);
            
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"status\":\"success\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private String readResourceFile(String path) throws IOException 
    {
        try (InputStream is = getServletContext().getResourceAsStream(path)) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private String fillHtmlPage() 
    {
        try {
            List<Developer> items = JPAHelper.getAllDevelopers();
            StringBuilder tableBuilder = new StringBuilder();
            
            for (Developer entry : items) 
            { 
                tableBuilder.append("<tr>")
                    .append("<td>").append(entry.getId()).append("</td>")
                    .append("<td>").append(entry.getName()).append("</td>")
                    .append("<td>").append(entry.getCounty()).append("</td>")
                    .append("<td>").append(entry.getFoundedDate()).append("</td>")
                    .append("<td>")
                    .append("<button class=\"action-btn delete-btn\" title=\"Delete\" ")
                    .append("onclick=\"deleteItem(").append(entry.getId()).append(")\">")
                    .append("x</button>")
                    .append("</td>")
                    .append("</tr>");
            }
            
            String html = htmlTemplate
                .replace("${pageStyle}", styleTemplate)
                .replace("${developersTable}", tableBuilder.toString());
                
            return html;
        } catch (Exception e) {
            return new String();
        }

    }
}
