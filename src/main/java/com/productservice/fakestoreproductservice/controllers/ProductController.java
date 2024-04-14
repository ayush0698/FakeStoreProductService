package com.productservice.fakestoreproductservice.controllers;

import com.productservice.fakestoreproductservice.models.Product;
import com.productservice.fakestoreproductservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
     //   Product product= productService.getProductById(id);

       // if(product==null)  // if product is not found, send status code 400:BAD_REQUEST
         //   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        //return new ResponseEntity<>(product, HttpStatus.OK);
        ResponseEntity<Product> responseEntity= null;
        try{
            Product product= productService.getProductById(id);
            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(ArithmeticException e){
            //System.out.println("Something wern wrong");
            responseEntity = new ResponseEntity<>((HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return responseEntity;
        // throw new RuntimeException("Something went wrong);
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
