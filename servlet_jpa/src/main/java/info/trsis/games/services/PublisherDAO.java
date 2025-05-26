package info.trsis.games.services;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import info.trsis.games.storage.Publisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PublisherDAO extends AbstractDAO {
    
    public PublisherDAO(EntityManager entityManager) 
    {
        super(entityManager);
    }

    public void persis(
        String name, 
        String county)
    {
        beginTransaction();
        Publisher pub = new Publisher(name, county);
        entityManager.persist(pub);
        commitTransaction();
    }

    public Publisher find(Integer id)
    {
        return entityManager.find(Publisher.class, id);
    }

    public Collection<Publisher> findAll()
    {
        Query query = entityManager.createQuery("SELECT p FROM Publisher p");
        return (Collection<Publisher>) query.getResultList();
    }

    public void update(
        Integer id,
        String name, 
        String county)
    {
        beginTransaction();
        Publisher pub = entityManager.find(Publisher.class, id);
        pub.setName(name);
        pub.setCounty(county);
        entityManager.merge(pub);
        commitTransaction();
    }

    public void remove(Integer id)
    {
        beginTransaction();
        Publisher dev = entityManager.find(Publisher.class, id);
        entityManager.remove(dev);
        commitTransaction();
    }

}
