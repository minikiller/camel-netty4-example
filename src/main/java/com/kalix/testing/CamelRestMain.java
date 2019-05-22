package com.kalix.testing;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CamelRestMain {
    public static void main(String[] args) throws Exception {
//        SimpleRegistry registry=new SimpleRegistry();
//        registry.put("customerService",new CustomerService());

        CamelContext context = new CdiCamelContext();
//        CamelContext context = new DefaultCamelContext(registry);
        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                restConfiguration()
                        .component("restlet")
                        .host("localhost").port("8081").enableCORS(true);
                // Endpoints will be defined here
                rest("/customers?country={country}")
                        .get().to("bean:customerService?method=listCustomers(${header.country})");
            }
        });

        context.start();

    }
}
