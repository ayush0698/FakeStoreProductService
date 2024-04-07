package com.productservice.fakestoreproductservice.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration //to make it special call., we are telling it to go through this class at the time of intialization
public class RestTemplateConfig {
    @Bean  //it will create bean of this in a containner so that we can reuse it, whenever require
    public RestTemplate getRestTemplate(){
        return new RestTemplateBuilder().build();
    }
}
