package org.example.spring.inventory.converters;

import org.example.spring.inventory.entity.Products;
import org.example.spring.inventory.model.ProductsModel;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Named;
import java.util.Date;
import java.util.Optional;

@Named
public class ProductsEntityToModelConverter implements Converter<Products, ProductsModel> {

    @Override
    public ProductsModel convert(Products products) {
        return ProductsModel.builder()
                .productId(products.getProductId())
                .name(products.getName())
                .userName(products.getLastUpdatedUserName())
                .lastUpdatedDateTime(products.getLastUpdatedDateTime())
                .categoryName(products.getCategories().getCategoryName())
                .deleted(products.getDeleted())
                .build();
    }
}
