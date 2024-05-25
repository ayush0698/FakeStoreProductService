package com.productservice.fakestoreproductservice.controllers;

import com.productservice.fakestoreproductservice.commons.AuthCommons;
import com.productservice.fakestoreproductservice.dtos.UserDto;
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
    private AuthCommons authCommons;
    public ProductController(@Qualifier("SelfProductService") ProductService productService,
                             AuthCommons authCommons){
        this.authCommons= authCommons;
        this.productService= productService;
    }
    //localhost:8080/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id, @RequestHeader("authToken") String token) throws ProductNotFoundException {

        //Call UserService validateToken API to validate the token.
        UserDto userDto= authCommons.validateToken(token);
        ResponseEntity<Product> responseEntity;
        if (userDto==null){
            responseEntity = new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            return responseEntity;
        }

        //Role based access


        Product product= productService.getProductById(id);
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
