package com.productservice.fakestoreproductservice.controllers;

import com.productservice.fakestoreproductservice.exceptions.ProductNotFoundException;
import com.productservice.fakestoreproductservice.models.Product;
import com.productservice.fakestoreproductservice.services.ProductService;
import com.productservice.fakestoreproductservice.services.SelfProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//localhost:8080/products
@RestController  //this controller is going to REST HTTP API's
@RequestMapping("/products")

public class ProductController {
    private ProductService productService;
    public ProductController(@Qualifier("SelfProductService") ProductService productService){
        this.productService= productService;
    }
    //localhost:8080/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product= productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
        if(product==null) { // if product is not found, send status code 400:BAD_REQUEST
        //    throw new ProductNotFoundException(id+ "Product with id "+ id+ " not found.");
        }
        //return new ResponseEntity<>(product, HttpStatus.OK);
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
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

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Void> handleSomeException() {
        return null;
    }

    //createProduct
    //deleteProduct
    //updateProduct
    //replaceProduct

    @PostMapping
    public Product createProduct(@RequestBody Product product){   //can use dto also here
        return productService.createProduct(product);
    }
}
