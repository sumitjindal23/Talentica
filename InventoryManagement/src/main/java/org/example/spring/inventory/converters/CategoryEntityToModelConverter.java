package org.example.spring.inventory.converters;

import org.example.spring.inventory.entity.Categories;
import org.example.spring.inventory.entity.Products;
import org.example.spring.inventory.model.CategoriesModel;
import org.example.spring.inventory.model.ProductsModel;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryEntityToModelConverter implements Converter<Categories, CategoriesModel> {

    @Inject
    private ConversionService conversionService;

    @Override
    public CategoriesModel convert(Categories categories) {
        return CategoriesModel.builder()
                .categoryId(categories.getId())
                .name(categories.getCategoryName())
                .build();
    }
}
