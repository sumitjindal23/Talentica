package org.example.spring.inventory.dao.Impl;

import org.example.spring.inventory.dao.AbstractDAO;
import org.example.spring.inventory.dao.ProductsDAO;
import org.example.spring.inventory.entity.Products;
import org.springframework.stereotype.Repository;


@Repository("productsdaoimpl")
public class ProductsDAOImpl extends AbstractDAO<Products, String> implements ProductsDAO {
}
