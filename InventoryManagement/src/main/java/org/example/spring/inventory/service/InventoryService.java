package org.example.spring.inventory.service;

import org.example.spring.inventory.exception.InventoryException;
import org.example.spring.inventory.model.CategoriesModel;
import org.example.spring.inventory.model.ProductsModel;

import java.util.List;

public interface InventoryService {

    List<ProductsModel> getProducts() throws InventoryException;

    ProductsModel getProduct(String id) throws InventoryException;

    ProductsModel createProducts(ProductsModel products) throws InventoryException;

    void updateProducts(String id, ProductsModel products) throws Exception;

    void deleteProducts(String id) throws InventoryException;
}
