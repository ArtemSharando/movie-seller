package com.luxoft.movieanalyzer.service;

import com.lufoxt.movieseller.entity.dto.MovieDto;

import java.util.List;

public interface MovieAnalyzerService {

    List<MovieDto> findAllMoviesByReleaseDate();

    boolean isAvailableMovieForBuying(Long movieId, Long userId);

    List<Long> checkCustomersHaveDeletingMovie(Long movieId);
}
