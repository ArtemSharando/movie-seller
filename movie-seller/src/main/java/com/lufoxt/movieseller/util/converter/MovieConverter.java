package com.lufoxt.movieseller.util.converter;

import com.lufoxt.movieseller.entity.Customer;
import com.lufoxt.movieseller.entity.Movie;
import com.lufoxt.movieseller.entity.MovieGenre;
import com.lufoxt.movieseller.entity.dto.MovieDto;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieConverter {
    public static Movie convertMovieDtoToMovieEntity(MovieDto movieDto, Set<Customer> movieCustomers) {
        return new Movie(
                movieDto.getMovieId(),
                movieDto.getTitle(),
                MovieGenre.valueOf(movieDto.getMovieGenre().toUpperCase()),
                movieDto.getDescription(),
                movieCustomers,
                movieDto.getActiveStatus(),
                movieDto.getRating(),
                new Date()
        );
    }

    public static MovieDto convertMovieEntityToMovieDto(Movie movie) {
        return MovieDto.builder()
                .movieId(movie.getId())
                .title(movie.getTitle())
                .movieGenre(movie.getMovieGenre().name())
                .description(movie.getDescription())

                .movieCustomerIds(movie.getMovieCustomers()
                        .stream()
                        .map(Customer::getId)
                        .collect(Collectors.toList()))

                .activeStatus(movie.getActiveStatus())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .build();
    }
}
