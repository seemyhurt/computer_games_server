package info.trsis.games.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.trsis.games.storage.Game;
import info.trsis.games.storage.Publisher;
import info.trsis.games.storage.Developer;
import info.trsis.games.services.JPAHelper;

import java.util.List;
import java.util.HashMap;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.time.LocalDate;

public class ComputerGamesServlet extends javax.servlet.http.HttpServlet 
{
    private String htmlTemplate;
    private String styleTemplate;

    @Override
    public void init() throws ServletException 
    {
        try {
            htmlTemplate = readResourceFile("/static/games.html.in");
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

            String title = map.get("title");
            Integer developer = Integer.parseInt(map.get("developer"));
            Integer publisher = Integer.parseInt(map.get("publisher"));
            Double price = Double.parseDouble(map.get("price"));
            LocalDate releaseDate = LocalDate.parse(map.get("releaseDate"));

            if (title == null || title.trim().isEmpty() || 
                developer == null || publisher == null || releaseDate == null) {
                throw new IllegalArgumentException("Fields are invalid");
            }

            JPAHelper.insertGame(title, price, releaseDate, developer, publisher);
            
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
            JPAHelper.removeGame(id);
            
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
            List<Game> items = JPAHelper.getAllGames();
            StringBuilder tableBuilder = new StringBuilder();
            
            for (Game entry : items) 
            {  
                Developer dev = JPAHelper.getDeveloper(entry.getDeveloper());
                Publisher pub = JPAHelper.getPublisher(entry.getPublisher());

                tableBuilder.append("<tr>")
                    .append("<td>").append(entry.getId()).append("</td>")
                    .append("<td>").append(entry.getTitle()).append("</td>")
                    .append("<td class=\"price\">$").append(entry.getPrice()).append("</td>")
                    .append("<td>").append(dev.getName()).append("</td>")
                    .append("<td>").append(pub.getName()).append("</td>")
                    .append("<td>").append(entry.getReleaseDate()).append("</td>")
                    .append("<td>")
                    .append("<button class=\"action-btn delete-btn\" title=\"Delete\" ")
                    .append("onclick=\"deleteItem(").append(entry.getId()).append(")\">")
                    .append("x</button>")
                    .append("</td>")
                    .append("</tr>");
            }

            StringBuilder devOptions = new StringBuilder();
            List<Developer> developers = JPAHelper.getAllDevelopers();
            for (Developer dev : developers) {
                devOptions.append("<option value=\"").append(dev.getId())
                        .append("\">").append(dev.getName()).append("</option>");
            }
            
            StringBuilder pubOptions = new StringBuilder();
            List<Publisher> publishers = JPAHelper.getAllPublishers();
            for (Publisher pub : publishers) {
                pubOptions.append("<option value=\"").append(pub.getId())
                        .append("\">").append(pub.getName()).append("</option>");
            }
            
            String html = htmlTemplate
                .replace("${pageStyle}", styleTemplate)
                .replace("${gamesTable}", tableBuilder.toString())
                .replace("${developerOptions}", devOptions.toString())
                .replace("${publisherOptions}", pubOptions.toString());
                
            return html;
        } catch (Exception e) {
            return new String();
        }
    }
}
