package info.trsis.games.services;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import info.trsis.games.storage.Developer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeveloperDAO extends AbstractDAO 
{
    public DeveloperDAO(EntityManager entityManager) 
    {
        super(entityManager);
    }

    public void persis(
        String name, 
        String county, 
        LocalDate foundedDate)
    {
        beginTransaction();
        Developer dev = new Developer(name, county, foundedDate);
        entityManager.persist(dev);
        commitTransaction();
    }

    public Developer find(Integer id)
    {
        return entityManager.find(Developer.class, id);
    }

    public Collection<Developer> findAll()
    {
        Query query = entityManager.createQuery("SELECT d FROM Developer d");
        return (Collection<Developer>) query.getResultList();
    }

    public void update(
        Integer id,
        String name, 
        String county, 
        LocalDate foundedDate)
    {
        beginTransaction();
        Developer dev = entityManager.find(Developer.class, id);
        dev.setName(name);
        dev.setCounty(county);
        dev.setFoundedDate(foundedDate);
        entityManager.merge(dev);
        commitTransaction();
    }

    public void remove(Integer id)
    {
        beginTransaction();
        Developer dev = entityManager.find(Developer.class, id);
        entityManager.remove(dev);
        commitTransaction();
    }
}
