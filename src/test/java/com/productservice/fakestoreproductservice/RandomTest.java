package com.productservice.fakestoreproductservice;

import com.productservice.fakestoreproductservice.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

     //   assert i==2;
        assertEquals(2, i, "1+1 should be equals to 2"); // this msg print after running test cases

        boolean flag = true;
        assertTrue(flag);
       /*
        assertThrows(
                ProductNotFoundException.class;
                fun(10);    //it will check whether this fun(10) will throw above exception or not
        );
        */

        //assertNull();
        //assertTimeout();
    }
}
