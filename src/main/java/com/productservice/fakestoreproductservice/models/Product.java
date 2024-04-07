package com.productservice.fakestoreproductservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter   //or we can use @data annotation to get getter and setter together.
public class Product {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private Category category;
}
