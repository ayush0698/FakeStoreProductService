package com.productservice.fakestoreproductservice.services;

import com.productservice.fakestoreproductservice.dtos.FakeStoreProductDto;
import com.productservice.fakestoreproductservice.models.Category;
import com.productservice.fakestoreproductservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service  // here we can use @repository or @component also
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate= restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product= new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category= new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
    public Product getProductById(Long id){
        //call fakestore API here to get the product with given .
        //RestTemplate restTemplate= new RestTemplate();
        // we are not creating here bcoz we need this RestTemplete object multiple time,
        // so to avoid multiple object creation ,we will create it at centre place and reuse it;

        // first parameter -URL, second parameter- responseType
        FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
       if(fakeStoreProductDto==null)
           return null;
        //convert dto into product object
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    public List<Product> getAllProducts(){
        FakeStoreProductDto[] fakeStoreProductDto= restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products=new ArrayList<>();
        for (FakeStoreProductDto obj : fakeStoreProductDto) {
            products.add(convertFakeStoreDtoToProduct(obj));
        }
        return products;
    }

    public Product replaceProduct(Long id, Product product){
        return null;
    }

}
