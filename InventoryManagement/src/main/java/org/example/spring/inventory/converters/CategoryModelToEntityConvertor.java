package org.example.spring.inventory.converters;

import org.example.spring.inventory.entity.Categories;
import org.example.spring.inventory.entity.Products;
import org.example.spring.inventory.model.CategoriesModel;
import org.example.spring.inventory.model.ProductsModel;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class CategoryModelToEntityConvertor implements Converter<CategoriesModel, Categories> {

    @Inject
    private ConversionService conversionService;

    @Override
    public Categories convert(CategoriesModel categories) {
        return Categories.builder()
                .id(categories.getCategoryId())
                .categoryName(categories.getName())
                .build();
    }
}
