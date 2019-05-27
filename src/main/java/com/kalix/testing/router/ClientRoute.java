package com.kalix.testing.router;

import com.kalix.testing.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.Main;

public class ClientRoute extends RouteBuilder {
    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("jetty")
//                .contextPath("swagger")
                .host("localhost").port("8082")
                .contextPath("swagger")
                .dataFormatProperty("prettyPrint", "true")
                .apiContextListing(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "User API").apiProperty("api.version", "1.2.3")
                .enableCORS(true).bindingMode(RestBindingMode.json);
        // Endpoints will be defined here
        rest("/customers/")
                .post()
                .produces("application/json")
                .type(Message.class)
                .consumes("application/json")

                .outType(Message.class)
//                .route().setBody(constant(new Message()))
//                .log("Request:  ${id}:${body}")
//                .to(HOST + "?allowDefaultCodec=false" +
//                        "&encoder=#stringEncoder&decoder=#stringDecoder")
//                .log("Request:  ${id}:${body}")
                .route()
//                .unmarshal("json")
                .to("bean-validator:myvalidatorname")
                .to("bean:echoService")
        ;
    }
}
