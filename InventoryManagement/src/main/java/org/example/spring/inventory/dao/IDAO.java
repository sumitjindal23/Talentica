package org.example.spring.inventory.dao;

import org.example.spring.inventory.dao.exception.DAOException;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface IDAO<T, ID extends Serializable> {
    /**
     * Basic DAO method to find the entity by its primary key
     * @param id
     * @return
     * @throws DAOException
     */
    T findByPrimaryKey(ID id) throws DAOException;
    
    /**
     * Method to list all the entities.
     * @return list of entities
     * @throws DAOException
     */
    List<T> findAll() throws DAOException;

    /**
     * Saves the entity in the current transaction scope
     * @param entity
     * @return
     * @throws DAOException
     */
    T saveInTx(T entity) throws DAOException;
    /**
     * Updates the entity in the current transaction scope
     * @param entity
     * @return
     * @throws DAOException
     */
    T updateInTx(T entity) throws DAOException;
    /**
     * Merges the entity in the current transaction scope
     * @param entity
     * @return
     * @throws DAOException
     */
    T mergeInTx(T entity) throws DAOException;
    /**
     * Deletes the entity in the current transaction scope
     * @param entity
     * @return
     * @throws DAOException
     */
    void deleteInTx(T entity) throws DAOException;

    EntityManager getEntityManager();
}
