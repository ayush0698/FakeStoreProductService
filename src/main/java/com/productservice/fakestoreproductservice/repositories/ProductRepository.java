package com.productservice.fakestoreproductservice.repositories;

import com.productservice.fakestoreproductservice.models.Product;
import com.productservice.fakestoreproductservice.projections.ProductWithTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


    //How to write HQL Query(in HQL query, we are using the class name)
    @Query("select p.title as title, p.description as description from Product p where p.id = :id")
    ProductWithTitleAndDescription someRandomQuery(@Param("id") Long id);

    //SQL query also call native query
    //How to write SQL Query(in SQL query, we need to use table name)
    @Query(value = "select title, description from product where id = :id", nativeQuery = true)
    ProductWithTitleAndDescription someOtherRandomQuery(@Param("id") Long id);
}
