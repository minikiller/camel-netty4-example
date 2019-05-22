package com.kalix.testing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.Main;

import static com.kalix.testing.Constant.*;

public class ClientRoute extends RouteBuilder {
    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("restlet")
                .host("localhost").port("8081").enableCORS(true).bindingMode(RestBindingMode.auto);
        // Endpoints will be defined here
        rest("/customers?country={country}")
                .get().
                route().setBody(simple("${header.country}"))
                .log("Request:  ${id}:${body}")
                .to(HOST + "?allowDefaultCodec=false" +
                        "&encoder=#stringEncoder&decoder=#stringDecoder")
                .log("Request:  ${id}:${body}")
                .to("bean:echoService")
        ;
    }
}
