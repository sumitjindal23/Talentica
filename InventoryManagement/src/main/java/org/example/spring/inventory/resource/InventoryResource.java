package org.example.spring.inventory.resource;


import org.example.spring.inventory.model.ProductsModel;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Controller
@Path("/inventory")
public interface InventoryResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    Response getProducts();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{product-id}")
    Response getProduct(@PathParam(value = "product-id") String id);

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    Response createProduct(ProductsModel products);

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{product-id}")
    Response updateProduct(@PathParam(value = "product-id") String id, ProductsModel products);

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{product-id}")
    Response deleteProduct(@PathParam(value = "product-id") String id);

}
