package com.productservice.fakestoreproductservice.controllers;

import com.productservice.fakestoreproductservice.models.Product;
import com.productservice.fakestoreproductservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//localhost:8080/products
@RestController  //this controller is going to REST HTTP API's
@RequestMapping("/products")

public class ProductController {
    private ProductService productService;
    ProductController(ProductService productService){
        this.productService= productService;
    }
    //localhost:8080/products/1
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
    // localhost:8080/products
    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //createProduct
    //deleteProduct
    //updateProduct
    //replaceProduct
}
