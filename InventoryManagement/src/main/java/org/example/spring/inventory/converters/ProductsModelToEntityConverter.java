package org.example.spring.inventory.converters;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.inject.Named;

import org.example.spring.inventory.entity.Products;
import org.example.spring.inventory.model.ProductsModel;
import org.springframework.core.convert.converter.Converter;

@Named
public class ProductsModelToEntityConverter implements Converter<ProductsModel, Products> {

    @Override
    public Products convert(ProductsModel products) {
        return Products.builder()
                .productId(products.getProductId())
                .name(products.getName())
                .lastUpdatedUserName(products.getUserName())
                .lastUpdatedDateTime(new Timestamp(new Timestamp((new Date()).getTime()).getTime()/1000 *1000))
                .deleted(false)
                .build();
    }

	
}
