package com.kalix.testing;

import org.springframework.stereotype.Service;

@Service
public class EchoService {
    public String sayHello(String guestName) {
        System.out.println("Input Value : "+ guestName);
        return "Hello " + guestName;
    }
}