package com.productservice.fakestoreproductservice.services;
import com.productservice.fakestoreproductservice.exceptions.ProductNotFoundException;
import com.productservice.fakestoreproductservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);


}
