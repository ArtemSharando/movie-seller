package com.lufoxt.movieseller.service.impl;

import com.lufoxt.movieseller.entity.Movie;
import com.lufoxt.movieseller.entity.dto.MovieDto;
import com.lufoxt.movieseller.exception.MovieNotFoundException;
import com.lufoxt.movieseller.repository.MovieRepository;
import com.lufoxt.movieseller.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static com.lufoxt.movieseller.util.config.MovieConfig.MOVIE_ANALYZER_URL;
import static com.lufoxt.movieseller.util.converter.MovieConverter.convertMovieDtoToMovieEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate;

    @Override
    public void saveNewMovie(MovieDto movieDto) {
        Movie newMovie = convertMovieDtoToMovieEntity(movieDto, Collections.emptySet());

        movieRepository.save(newMovie);

        log.info("Registered new movie: {}", movieDto.getTitle());
    }

    @Override
    public void disableMovieFromPlatform(Long movieId) {
        Movie enabledMovie = movieRepository.findByIdAndActiveStatusTrue(movieId).orElseThrow(MovieNotFoundException::new);
        enabledMovie.setActiveStatus(false);
        Long[] customersIds = restTemplate.getForEntity(
                        MOVIE_ANALYZER_URL + "/checkDelete/{movieId}",
                        Long[].class, movieId)
                .getBody();

        movieRepository.save(enabledMovie);
        log.info("Disable movie for platform with id: {}", movieId);

        if (customersIds.length == 0) {
            log.info("No customer has bought movie with id: {}", movieId);
        } else {
            log.info("{} customers bought this movie, an email has been sent to customers " +
                    "about disabling movie with id: {} from the platform", customersIds.length, movieId);
        }
    }

    @Override
    public void activeMovieForPlatform(Long movieId) {
        Movie disabledMovie = movieRepository.findByIdAndActiveStatusFalse(movieId).orElseThrow(MovieNotFoundException::new);
        disabledMovie.setActiveStatus(true);
        movieRepository.save(disabledMovie);

        log.info("Enable movie for platform with id: {}", movieId);
    }

    @Override
    public List<MovieDto> findAllMovies() {
        MovieDto[] movieDtoArray = restTemplate.getForEntity(
                MOVIE_ANALYZER_URL + "/movies", MovieDto[].class).getBody();
        return movieDtoArray != null ? List.of(movieDtoArray) : Collections.emptyList();
    }
}
