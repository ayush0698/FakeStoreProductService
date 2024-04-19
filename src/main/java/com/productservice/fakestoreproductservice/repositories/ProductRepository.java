package com.productservice.fakestoreproductservice.repositories;

import com.productservice.fakestoreproductservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    List<Product> findByTitle(String word);

    //select * from Product where title= <> and description = <>
    List<Product> findByTitleAndDescription(String title, String description);

    List<Product> findByImage(String url);

    void delete(Product entity);

    Product save(Product product);  //create and update data
    //Here the difference between argument and return Product is that, return product
    // comes with id which is assign by DB  (create product)
    // And if input Product Id is alreday present then it means we want to update product
}
