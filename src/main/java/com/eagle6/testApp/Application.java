package com.eagle6.testApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by stefanos on 06/12/2018.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {


    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
