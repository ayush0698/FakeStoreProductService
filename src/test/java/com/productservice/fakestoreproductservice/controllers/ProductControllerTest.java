package com.productservice.fakestoreproductservice.controllers;

import com.productservice.fakestoreproductservice.dtos.ProductNotFoundExceptionDto;
import com.productservice.fakestoreproductservice.exceptions.ProductNotFoundException;
import com.productservice.fakestoreproductservice.models.Product;
import com.productservice.fakestoreproductservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean  // this is going to be Mocked Object
    private ProductService productService;

    @Test
    void ValidGetProductById() throws ProductNotFoundException {
        Product product = new Product();   // it is actual product object
        product.setId(1L);
        product.setTitle("MacBook Pro");
        product.setPrice(150000.0);

        when(productService.getProductById(1L))
                .thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
        Product actualProduct = responseEntity.getBody();

        assertEquals(product, actualProduct);
    }

    @Test
    void testAllProducts(){
        List<Product> expectedProducts = new ArrayList<>();

        Product P1= new Product();
        P1.setId(1L);
        P1.setTitle("Iphone 13 pro");

        Product P2= new Product();
        P2.setId(2L);
        P2.setTitle("Iphone 14 pro");

        Product P3= new Product();
        P3.setId(3L);
        P3.setTitle("Iphone 15 pro");

        expectedProducts.add(P1);
        expectedProducts.add(P2);
        expectedProducts.add(P3);

        when(productService.getAllProducts())
                .thenReturn(expectedProducts);

        List<Product> products = productController.getAllProducts();

        assertIterableEquals(expectedProducts, products);
    }
}