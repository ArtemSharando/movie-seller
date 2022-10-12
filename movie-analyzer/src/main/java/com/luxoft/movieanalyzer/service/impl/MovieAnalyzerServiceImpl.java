package com.luxoft.movieanalyzer.service.impl;

import com.lufoxt.movieseller.entity.Customer;
import com.lufoxt.movieseller.entity.Movie;
import com.lufoxt.movieseller.entity.dto.MovieDto;
import com.lufoxt.movieseller.exception.MovieNotFoundException;
import com.lufoxt.movieseller.repository.MovieRepository;
import com.lufoxt.movieseller.util.converter.MovieConverter;
import com.luxoft.movieanalyzer.service.MovieAnalyzerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieAnalyzerServiceImpl implements MovieAnalyzerService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDto> findAllMoviesByReleaseDate() {
        List<Movie> activeMovies = movieRepository.findAllByActiveStatusIsTrue();

        return activeMovies.stream()
                .sorted((Comparator.comparing(Movie::getReleaseDate)))
                .map(MovieConverter::convertMovieEntityToMovieDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAvailableMovieForBuying(Long movieId, Long userId) {
        Movie checkingMovie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        if (!isActiveStatus(checkingMovie)) {
            log.error("Movie with id: {} cannot be bought because it is disabled", movieId);
            return false;
        }
        return checkingMovie.getMovieCustomers().stream()
                .noneMatch(m -> userId.equals(m.getId()));
    }

    @Override
    public List<Long> checkCustomersHaveDeletingMovie(Long movieId) {
        Movie deletingMovie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        Set<Customer> movieCustomers = deletingMovie.getMovieCustomers();
        doNotification(movieCustomers, movieId);

        return movieCustomers.stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
    }

    //TODO write notification application
    private void doNotification(Set<Customer> movieCustomers, Long movieId) {

    }

    private boolean isActiveStatus(Movie checkingMovie) {
        return checkingMovie.getActiveStatus();
    }
}
