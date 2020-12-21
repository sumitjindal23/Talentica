package org.example.spring.inventory.service;

import org.example.spring.inventory.dao.CategoriesDAO;
import org.example.spring.inventory.dao.ProductsDAO;
import org.example.spring.inventory.entity.Categories;
import org.example.spring.inventory.entity.Products;
import org.example.spring.inventory.exception.InventoryException;
import org.example.spring.inventory.model.ProductsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class InventoryServiceImpl implements InventoryService {

    Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Inject
    private ConversionService conversionService;

    @Inject
    private ProductsDAO productsDAO;

    @Inject
    private CategoriesDAO categoriesDAO;

    @Transactional
    @Override
    public List<ProductsModel> getProducts() throws InventoryException {
        logger.debug("Entered getProducts Service method");
        try {
            List<Products> productEntities = productsDAO.findAll();
            return productEntities
                    .stream()
                    .map(e -> conversionService.convert(e, ProductsModel.class))
                    .collect(Collectors.toList());
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new InventoryException(e.getLocalizedMessage());
        }
    }

    @Transactional
    @Override
    public ProductsModel getProduct(String id) throws InventoryException {
        logger.debug("Entered getProduct by Id Service method");
        try {
            Products products = productsDAO.findByPrimaryKey(id);
            if (products == null ) {
                throw new InventoryException("Product Id is not present");
            }
            return conversionService.convert(products, ProductsModel.class);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new InventoryException(e.getLocalizedMessage());
        }
    }

    @Transactional(rollbackFor={Exception.class})
    @Override
    public ProductsModel createProducts(ProductsModel productsModel) throws InventoryException {
        logger.debug("Entered createProducts Service method");
        try {
            Categories categories = new Categories();
            categories.setCategoryName(productsModel.getCategoryName());
            categories.addProducts(conversionService.convert(productsModel, Products.class));
            categoriesDAO.saveInTx(categories);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new InventoryException(e.getLocalizedMessage());
        }
        Products products = productsDAO.findByPrimaryKey(productsModel.getProductId());
        return conversionService.convert(products,ProductsModel.class);
    }

    @Transactional(rollbackFor={Exception.class})
    @Override
    public void updateProducts(String id, ProductsModel productsModel) throws InventoryException {
        logger.debug("Entered updateProducts Service method");
        Products product = productsDAO.findByPrimaryKey(id);
        try {
            if (product == null || product.getDeleted()) {
                throw new InventoryException("Product Id is not present");
            }
            if(categoriesDAO.findByCategoryName(productsModel.getCategoryName()) ==null) {
                Categories categories = new Categories();
                categories.setCategoryName(productsModel.getCategoryName());
                categories.addProducts(conversionService.convert(productsModel, Products.class));
                categoriesDAO.updateInTx(categories);
            }else{
                Products newProducts = conversionService.convert(productsModel, Products.class);
                newProducts.setCategories(product.getCategories());
                newProducts.setProductId(product.getProductId());
                productsDAO.updateInTx(newProducts);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new InventoryException(e.getLocalizedMessage());
        }
    }

    @Transactional(rollbackFor={Exception.class})
    @Override
    public void deleteProducts(String id) throws InventoryException {
        logger.debug("Entered deleteProducts Service method");
        try {
            Products products = productsDAO.findByPrimaryKey(id);
            if (products == null || products.getDeleted()) {
                throw new InventoryException("Product {} is not present", id);
            }
            products.setDeleted(true);
            productsDAO.updateInTx(products);
        }catch(Exception e){
            logger.error(e.getMessage());
            throw new InventoryException(e.getLocalizedMessage());
        }
    }
}
