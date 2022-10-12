package com.lufoxt.movieseller.repository;

import com.lufoxt.movieseller.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Override
    @EntityGraph(attributePaths = {"orders", "purchasedMovies"})
    List<Customer> findAll();

    @Override
    @EntityGraph(attributePaths = {"orders", "purchasedMovies"})
    Optional<Customer> findById(Long aLong);
}
