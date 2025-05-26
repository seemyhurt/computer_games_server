package info.trsis.games.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractDAO 
{
    protected final EntityManager entityManager;
    protected final EntityTransaction entityTransaction;

    public AbstractDAO(EntityManager entityManager) 
    {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    protected void beginTransaction() 
    {
        try {
            entityTransaction.begin();
        } catch (Exception e) {
            rollbackTransaction();
        }
    }

    protected void commitTransaction() 
    {
        try {
            entityTransaction.commit();
        } catch (Exception e) {
            rollbackTransaction();
        }
    }

    protected void rollbackTransaction() 
    {
        try {
            entityTransaction.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
