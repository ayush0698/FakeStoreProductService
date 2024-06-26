package com.productservice.fakestoreproductservice.services;

import com.productservice.fakestoreproductservice.dtos.FakeStoreProductDto;
import com.productservice.fakestoreproductservice.exceptions.ProductNotFoundException;
import com.productservice.fakestoreproductservice.models.Category;
import com.productservice.fakestoreproductservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service("FakeStoreProductService")  // here we can use @repository or @component also
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
    public Product getProductById(Long id) throws ProductNotFoundException {
        //int x = 1/0;    //only for checking exception

        //call fakestore API here to get the product with given .
        //RestTemplate restTemplate= new RestTemplate();
        // we are not creating here bcoz we need this RestTemplete object multiple time,
        // so to avoid multiple object creation ,we will create it at centre place and reuse it;

        // first parameter -URL, second parameter- responseType
        FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
       if(fakeStoreProductDto==null)
           throw  new ProductNotFoundException(id, "Product with id" + id + " not found.");
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

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    public Product replaceProduct(Long id, Product product){
        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setDescription(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response= restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreDtoToProduct(response);

    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct() {

    }


}
