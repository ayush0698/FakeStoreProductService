package com.productservice.fakestoreproductservice.services;
import com.productservice.fakestoreproductservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);


}
