package org.example.spring.inventory.resource;

import org.example.spring.inventory.model.ProductsModel;
import org.example.spring.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.provider.certpath.OCSPResponse;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
public class InventoryResourceImpl implements InventoryResource {

    Logger logger = LoggerFactory.getLogger(InventoryResourceImpl.class);

    @Inject
    private InventoryService inventoryService;

    @Override
    public Response getProducts() {
        try {
            logger.info("Getting all Product Details");
            List<ProductsModel> products = inventoryService.getProducts();
            return Response.ok().entity(products).build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public Response getProduct(String id) {
        try {
            logger.info("Getting Product {} Details",id);
            ProductsModel employee = inventoryService.getProduct(id);
            return Response.ok().entity(employee).build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public Response createProduct(ProductsModel products) {
        try {
            logger.info("Creation of Product started");
            ProductsModel employee = inventoryService.createProducts(products);
            return Response.status(Response.Status.CREATED).entity(employee).build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public Response updateProduct(String id, ProductsModel products) {
        try {
            logger.info("Update of Product started");
            inventoryService.updateProducts(id, products);
            return Response.noContent().build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Response.notModified(e.getLocalizedMessage()).build();
        }
    }

    @Override
    public Response deleteProduct(String id) {
        try {
            logger.info("Deletion of Product started");
            inventoryService.deleteProducts(id);
            return Response.noContent().build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Response.notModified(e.getLocalizedMessage()).build();
        }
    }

}
