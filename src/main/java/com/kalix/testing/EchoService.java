package com.kalix.testing;

import org.springframework.stereotype.Service;

@Service
public class EchoService {
    public Message sayHello(Message guestName) {
        System.out.println("Input Value : "+ guestName);
        return new Message("fdfd","dfdfdf");
    }
}