package com.productservice.fakestoreproductservice.controllers;

import com.productservice.fakestoreproductservice.models.Product;
import com.productservice.fakestoreproductservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }


    //createProduct
    //deleteProduct
    //updateProduct
    //replaceProduct
}
