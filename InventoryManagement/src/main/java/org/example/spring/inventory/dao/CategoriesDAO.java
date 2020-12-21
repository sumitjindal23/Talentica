package org.example.spring.inventory.dao;

import org.example.spring.inventory.dao.exception.DAOException;
import org.example.spring.inventory.entity.Categories;
import org.example.spring.inventory.entity.Products;

public interface CategoriesDAO extends IDAO<Categories, Integer> {

    public Categories findByCategoryName(String name)
            throws DAOException;
}
