package com.kalix.testing;

//import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 封装发送的消息
 */
//@Service
public class Message implements Serializable {
    @NotNull
    String id;
    @NotNull
    String login;
    @NotNull
    String name;

    public Message(String id, String login) {
        this.id = id;
        this.login = login;
    }

    public Message(){

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
