package com.kalix.testing;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

public class CamelMain {
    public static void main(String[] args) throws Exception {
//        Main main = new Main();
//        main.addRouteBuilder(new RouteBuilder() {
//
//            @Override
//            public void configure() throws Exception {
//                from("direct:hello")
//                        .log("UK message")
//                        .to("seda:first");
//            }
//        });

        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:hello")
                        .log("UK message")
                        .to("seda:first");
            }
        });
        context.start();
//        main.getCamelContexts().add(context);
//        main.run();
        context.createProducerTemplate().sendBody("direct:hello", "hello world");
        String str = context.createConsumerTemplate().receiveBody("seda:first", String.class);
        System.out.println(str);

    }
}
