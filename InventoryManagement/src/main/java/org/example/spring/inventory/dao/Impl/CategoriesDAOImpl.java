package org.example.spring.inventory.dao.Impl;

import org.example.spring.inventory.dao.AbstractDAO;
import org.example.spring.inventory.dao.CategoriesDAO;
import org.example.spring.inventory.dao.exception.DAOException;
import org.example.spring.inventory.entity.Categories;
import org.example.spring.inventory.resource.InventoryResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("categoriesdaoimpl")
public class CategoriesDAOImpl extends AbstractDAO<Categories, Integer> implements CategoriesDAO {

    Logger logger = LoggerFactory.getLogger(CategoriesDAOImpl.class);

    @Override
    public Categories findByCategoryName(String name)
            throws DAOException {
        logger.debug("Inside  findByCategoryName for client " + name);
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Categories> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<Categories> root = criteriaQuery.from(persistentClass);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("categoryName"), name));
        List<Categories> all = getEntityManager().createQuery(criteriaQuery).getResultList();
        if (!all.isEmpty()) {
            return all.get(0);
        }
        logger.debug("Returning " + all.size() + " items");
        return null;
    }
}
