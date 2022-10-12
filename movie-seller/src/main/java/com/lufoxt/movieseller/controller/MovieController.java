package com.lufoxt.movieseller.controller;

import com.lufoxt.movieseller.entity.dto.MovieDto;
import com.lufoxt.movieseller.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<HttpStatus> saveNewMovie(@RequestBody MovieDto movieDto) {
        movieService.saveNewMovie(movieDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/disable")
    public ResponseEntity<HttpStatus> disableMovieFromPlatform(@RequestParam Long movieId) {
        movieService.disableMovieFromPlatform(movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("enable")
    public ResponseEntity<HttpStatus> activeMovieForPlatform(@RequestParam Long movieId) {
        movieService.activeMovieForPlatform(movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllCustomers() {
        List<MovieDto> movies = movieService.findAllMovies();
        return ResponseEntity.ok(movies);
    }
}
