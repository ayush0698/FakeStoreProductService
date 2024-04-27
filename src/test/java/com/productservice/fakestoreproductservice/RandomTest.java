package com.productservice.fakestoreproductservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class RandomTest {
    @Test
    //@SpringBootTest (if we are using any springboot functionality, then this annotation is required
    void testIsOnePlusOneIsTwo(){
        int i= 1 + 1;  // Arrange & Act  (since test case is basic, we are doing two steps together)

        // Assert --> Check against the expected value
    /*    if(i == 2){
            // SUCCESS
        } else {
            //fail
        }
     */  // don't need to do this if....else block, we have other functionality

        assert i==2;
    }
}
