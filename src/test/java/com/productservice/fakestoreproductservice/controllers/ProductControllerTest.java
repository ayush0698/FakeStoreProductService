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
}