package com.github.vote_restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VoteRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteRestaurantApplication.class, args);
    }

}
