package info.trsis.games.services;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import info.trsis.games.storage.Game;
import info.trsis.games.storage.Publisher;
import info.trsis.games.storage.Developer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JPAHelper 
{
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("games-unit");

    public static EntityManager getEntityManager() 
    {
        return entityManagerFactory.createEntityManager();
    }

    public static List<Game> getAllGames() throws SQLException 
    {
        GameDAO dao = new GameDAO(getEntityManager());
        return new ArrayList<>(dao.findAll());
    }

    public static void insertGame(        
        String title, 
        double price, 
        LocalDate releaseDate, 
        Integer developer, 
        Integer publisher) throws SQLException 
    {
        GameDAO dao = new GameDAO(getEntityManager());
        dao.persis(title, price, releaseDate, developer, publisher);
    }

    public static void removeGame(Integer id) throws SQLException 
    {
        GameDAO dao = new GameDAO(getEntityManager());
        dao.remove(id);
    }


    public static List<Publisher> getAllPublishers() throws SQLException 
    {
        PublisherDAO dao = new PublisherDAO(getEntityManager());
        return new ArrayList<>(dao.findAll());
    }

    public static Publisher getPublisher(Integer id) throws SQLException 
    {
        PublisherDAO dao = new PublisherDAO(getEntityManager());
        return dao.find(id);
    }

    public static void insertPublisher(        
        String name, 
        String county) throws SQLException 
    {
        PublisherDAO dao = new PublisherDAO(getEntityManager());
        dao.persis(name, county);
    }

    public static void removePublisher(Integer id) throws SQLException 
    {
        PublisherDAO dao = new PublisherDAO(getEntityManager());
        dao.remove(id);
    }

    
    public static List<Developer> getAllDevelopers() throws SQLException 
    {
        DeveloperDAO dao = new DeveloperDAO(getEntityManager());
        return new ArrayList<>(dao.findAll());
    }

    public static Developer getDeveloper(Integer id) throws SQLException 
    {
        DeveloperDAO dao = new DeveloperDAO(getEntityManager());
        return dao.find(id);
    }

    public static void insertDeveloper(        
        String name, 
        String county,
        LocalDate foundedDate) throws SQLException 
    {
        DeveloperDAO dao = new DeveloperDAO(getEntityManager());
        dao.persis(name, county, foundedDate);
    }

    public static void removeDeveloper(Integer id) throws SQLException 
    {
        DeveloperDAO dao = new DeveloperDAO(getEntityManager());
        dao.remove(id);
    }
}
