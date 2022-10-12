package com.luxoft.movieanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(
        basePackages = "com.lufoxt.movieseller.entity"
)
public class MovieAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieAnalyzerApplication.class, args);
    }

}
