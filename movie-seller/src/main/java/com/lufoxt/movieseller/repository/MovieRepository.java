package com.lufoxt.movieseller.repository;

import com.lufoxt.movieseller.entity.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Override
    @EntityGraph(attributePaths = "movieCustomers")
    Optional<Movie> findById(Long id);

    @EntityGraph(attributePaths = "movieCustomers")
    Optional<Movie> findByIdAndActiveStatusTrue(Long id);

    @EntityGraph(attributePaths = "movieCustomers")
    Optional<Movie> findByIdAndActiveStatusFalse(Long id);

    @EntityGraph(attributePaths = "movieCustomers")
    List<Movie> findAllByActiveStatusIsTrue();
}
