package org.example.spring.inventory.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "categories")
public class Categories implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "categoryName")
    private String categoryName;

    /**
     * One to many mapping to Products Entity.
     */
    @OneToMany(targetEntity = Products.class,cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<Products> products = new HashSet<>();

    public void addProducts(Products product){
        products.add(product);
        product.setCategories(this);
    }

    public void removeProducts(Products product){
        products.remove(product);
        product.setCategories(null);
    }
}
