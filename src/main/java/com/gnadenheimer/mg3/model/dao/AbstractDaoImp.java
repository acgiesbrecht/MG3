package com.gnadenheimer.mg3.model.dao;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractDaoImp<T> implements AbstractDao<T> {
    protected Class<T> entityClass;

    @Inject
    private EntityManager em;

    public AbstractDaoImp(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * Retrieves the meta-model for a certain entity.
     *
     * @return the meta-model of a certain entity.
     */
    protected EntityType<T> getMetaModel() {
        return getEntityManager().getMetamodel().entity(entityClass);
    }

    public void persist(List<T> listEntity) {
        beginTransaction();
        listEntity.stream().forEach(e -> {
            getEntityManager().persist(e);
        });
        commitTransaction();
    }

    public void persist(T entity) {
        beginTransaction();
        getEntityManager().persist(entity);
        commitTransaction();
    }

    public void merge(T entity) {
        beginTransaction();
        getEntityManager().merge(entity);
        commitTransaction();
    }

    public void remove(T entity) {
        beginTransaction();
        getEntityManager().remove(getEntityManager().merge(entity));
        commitTransaction();
    }

    public void removeAll() {
        beginTransaction();
        getEntityManager().createQuery("delete from " + entityClass.getName() + " t").executeUpdate();
        commitTransaction();
    }

    @Transactional
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Transactional
    public List<T> getListFromQuery(String jpqlQuery) {
        return (List<T>) getEntityManager().createQuery(jpqlQuery).getResultList();
    }

    @Transactional
    public T getEntityFromQuery(String jpqlQuery) {
        return (T) getEntityManager().createQuery(jpqlQuery).getSingleResult();
    }

    public void beginTransaction() {
        try {
            getEntityManager().getTransaction().begin();
        } catch (IllegalStateException e) {
            rollBackTransaction();
            e.printStackTrace();
        }
    }

    public void commitTransaction() {
        try {
            getEntityManager().getTransaction().commit();
        } catch (IllegalStateException | RollbackException e) {
            rollBackTransaction();
            e.printStackTrace();
        }
    }

    private void rollBackTransaction() {
        try {
            getEntityManager().getTransaction().rollback();
        } catch (IllegalStateException | PersistenceException e) {
            e.printStackTrace();
        }
    }

}
