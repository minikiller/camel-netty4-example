package com.kalix.testing;

import org.apache.camel.builder.RouteBuilder;

public class CamelRouter extends RouteBuilder {
    public void configure() throws Exception{
        from("file:data/input?noop=true")
                .to("file:data/output");
    }
}
