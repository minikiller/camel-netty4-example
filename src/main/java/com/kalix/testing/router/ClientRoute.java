package com.kalix.testing.router;

import com.kalix.testing.Message;
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
                .host("localhost").port("8081")
                .dataFormatProperty("prettyPrint", "true")
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
                .route().to("bean:echoService")
        ;
    }
}
