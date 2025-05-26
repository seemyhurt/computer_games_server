package info.trsis.games.services;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import info.trsis.games.storage.Game;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameDAO extends AbstractDAO 
{
    public GameDAO(EntityManager entityManager) 
    {
        super(entityManager);
    }

    public void persis(
        String title, 
        double price, 
        LocalDate releaseDate, 
        Integer developer, 
        Integer publisher)
    {
        beginTransaction();
        Game game = new Game(title, price, releaseDate, developer, publisher);
        entityManager.persist(game);
        commitTransaction();
    }

    public Game find(Integer id)
    {
        return entityManager.find(Game.class, id);
    }

    public Collection<Game> findAll()
    {
        Query query = entityManager.createQuery("SELECT g FROM Game g");
        return (Collection<Game>) query.getResultList();
    }

    public void update(
        Integer id,
        String title, 
        double price, 
        LocalDate releaseDate, 
        Integer developer, 
        Integer publisher)
    {
        beginTransaction();
        Game game = entityManager.find(Game.class, id);
        game.setTitle(title);
        game.setPrice(price);
        game.setReleaseDate(releaseDate);
        game.setDeveloper(developer);
        game.setPublisher(publisher);
        entityManager.merge(game);
        commitTransaction();
    }

    public void remove(Integer id)
    {
        beginTransaction();
        Game game = entityManager.find(Game.class, id);
        entityManager.remove(game);
        commitTransaction();
    }
}
