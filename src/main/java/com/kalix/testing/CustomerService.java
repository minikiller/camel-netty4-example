package com.kalix.testing;

import javax.inject.Named;
import javax.inject.Singleton;

//import org.apache.camel.Handler;
@Singleton
@Named("customerService")
public class CustomerService {
//    @Handler

    public void listCustomers(String hello){
        System.out.println("hello"+hello);
    }
}
