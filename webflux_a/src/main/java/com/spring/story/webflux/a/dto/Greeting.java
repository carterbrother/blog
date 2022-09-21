package com.spring.story.webflux.a.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Greeting implements Serializable {

    private String message;


    public Greeting(String message) {
        this.message = message;
    }
}
