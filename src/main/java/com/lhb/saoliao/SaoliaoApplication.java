package com.lhb.saoliao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.n3r.idworker","com.lhb.saoliao"})
public class SaoliaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaoliaoApplication.class, args);
    }
}
