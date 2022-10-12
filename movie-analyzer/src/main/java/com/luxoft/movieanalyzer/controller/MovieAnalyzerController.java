package com.luxoft.movieanalyzer.controller;

import com.lufoxt.movieseller.entity.dto.MovieDto;
import com.luxoft.movieanalyzer.service.MovieAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movie-analyzer")
public class MovieAnalyzerController {

    private final MovieAnalyzerService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMoviesWithSortByReleaseDate() {
        List<MovieDto> movies = movieService.findAllMoviesByReleaseDate();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Boolean> isAvailableMovieForBuying(@PathVariable Long movieId, @RequestParam Long customerId) {
        boolean availableMovieForBuying = movieService.isAvailableMovieForBuying(movieId, customerId);
        return ResponseEntity.ok(availableMovieForBuying);
    }

    @GetMapping("/checkDelete/{movieId}")
    public ResponseEntity<List<Long>> checkCustomersHaveDeletingMovie(@PathVariable Long movieId) {
        List<Long> customerIds = movieService.checkCustomersHaveDeletingMovie(movieId);
        return ResponseEntity.ok(customerIds);
    }
}
