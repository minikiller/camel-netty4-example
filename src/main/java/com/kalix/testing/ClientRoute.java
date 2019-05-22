package com.kalix.testing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class ClientRoute extends RouteBuilder {
    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("restlet")
                .host("localhost").port("8081").enableCORS(true);
        // Endpoints will be defined here
        rest("/customers?country={country}")
                .get().
        route().setBody(simple("${header.country}"))
                .log("Request:  ${id}:${body}")
                .to("netty4:tcp://192.168.98.53:7000?allowDefaultCodec=false" +
                "&encoder=#stringEncoder&decoder=#stringDecoder")
                .log("Request:  ${id}:${body}")
                .to("bean:echoService")
                ;
    }
}
