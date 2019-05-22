package com.kalix.testing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class ServerRoute extends RouteBuilder {
    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void configure() throws Exception {
        from("netty4:tcp://192.168.98.53:7000?sync=true&allowDefaultCodec=false" +
                "&encoder=#stringEncoder&decoder=#broadCastChannelHandler")
                //"&encoder=#stringEncoder&decoder=#stringDecoder")
                .to("bean:echoService");
    }
}
