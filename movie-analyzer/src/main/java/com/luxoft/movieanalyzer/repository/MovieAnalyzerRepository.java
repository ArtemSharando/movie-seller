package com.luxoft.movieanalyzer.repository;

import com.lufoxt.movieseller.entity.Movie;
import com.lufoxt.movieseller.repository.MovieRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieAnalyzerRepository extends MovieRepository {

    @EntityGraph(attributePaths = "movieCustomers")
    List<Movie> findAllByActiveStatusIsTrue();

}
