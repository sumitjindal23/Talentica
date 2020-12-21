package org.example.spring.inventory.dao;

import org.example.spring.inventory.dao.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;


public abstract class AbstractDAO<T, ID extends Serializable> implements
        IDAO<T, ID> {

    protected Class<T> persistentClass = (Class<T>) DAOUtil.getTypeArguments(
            AbstractDAO.class, this.getClass()).get(0);

    @PersistenceContext
    private EntityManager persistenceContext;

    @Override
    public T findByPrimaryKey(ID id) throws DAOException {
        T instance = getEntityManager().find(persistentClass, id);
        return instance;
    }

    @Override
    public List<T> findAll() throws DAOException {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        criteriaQuery.from(persistentClass);
        List<T> all = getEntityManager().createQuery(criteriaQuery).getResultList();

        return all;
    }

    @Override
    public T saveInTx(T entity) throws DAOException {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public T updateInTx(T entity) throws DAOException {
        getEntityManager().merge(entity);
        return entity;
    }

    @Override
    public T mergeInTx(T entity) throws DAOException {
        getEntityManager().merge(entity);
        return entity;
    }

    @Override
    public void deleteInTx(T entity) throws DAOException {
        getEntityManager().remove(entity);
    }

    public EntityManager getEntityManager() {
        return persistenceContext;
    }
}
