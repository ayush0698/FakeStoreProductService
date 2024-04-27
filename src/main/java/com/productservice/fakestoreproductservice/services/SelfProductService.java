package com.productservice.fakestoreproductservice.services;

import com.productservice.fakestoreproductservice.exceptions.ProductNotFoundException;
import com.productservice.fakestoreproductservice.models.Category;
import com.productservice.fakestoreproductservice.models.Product;
import com.productservice.fakestoreproductservice.repositories.CategoryRepository;
import com.productservice.fakestoreproductservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
@Primary     // we can use qualifer annotaton also which bean we want to used
public class SelfProductService implements  ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository= productRepository;
        this.categoryRepository= categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Fetch the product with give id from database
        Optional<Product> optionalProduct= productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(id, "Product not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        //before saving the product object in the DB, save the category object
        Category category= product.getCategory();

        if(category.getId() == null){
            //we need to save the category
            Category savedCategory= categoryRepository.save(category);  //this sav methods and some other methods already present in jpa repository, we don;t need to implement it, you can see in categoryRepositor, we didn't defined this method
            product.setCategory(savedCategory);
        }
        else {
            //we shoudl check if category is valid or not
        }
        Product savedProduct= productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(savedProduct.getCategory().getId());
        Category category1= optionalCategory.get();
        savedProduct.setCategory(category1);
        return savedProduct;
    }

    @Override
    public void deleteProduct() {

    }
}
