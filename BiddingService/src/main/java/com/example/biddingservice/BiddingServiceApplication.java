package com.example.biddingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.example.biddingservice"})
public class BiddingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiddingServiceApplication.class, args);
    }

}
