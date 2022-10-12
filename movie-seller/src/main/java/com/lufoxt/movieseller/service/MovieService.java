package com.lufoxt.movieseller.service;

import com.lufoxt.movieseller.entity.dto.MovieDto;

import java.util.List;

public interface MovieService {

    void saveNewMovie(MovieDto movieDto);

    void disableMovieFromPlatform(Long movieId);

    void activeMovieForPlatform(Long movieId);

    List<MovieDto> findAllMovies();
}
