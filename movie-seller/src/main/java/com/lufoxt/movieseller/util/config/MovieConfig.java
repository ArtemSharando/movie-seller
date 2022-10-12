package com.lufoxt.movieseller.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MovieConfig {

    public static final String MOVIE_ANALYZER_URL = "http://localhost:8082/api/v1/movie-analyzer";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
