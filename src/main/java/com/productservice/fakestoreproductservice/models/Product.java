package com.productservice.fakestoreproductservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter   //or we can use @data annotation to get getter and setter together.
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    private String image;
    @ManyToOne
    //@ManyToOne(cascade = CascadeType.ALL)
    //@ManyToOne(cascade = CascadeType.PERSIST)  (along with product automatically category also saved , no need to write extra code)
    private Category category;
}

/*
      1        --->       1
    Product ----------> Category
      M      <---         1
      -------------------------
      M        :         1
 */