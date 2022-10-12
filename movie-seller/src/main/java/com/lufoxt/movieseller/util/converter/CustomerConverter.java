package com.lufoxt.movieseller.util.converter;

import com.lufoxt.movieseller.entity.Customer;
import com.lufoxt.movieseller.entity.Movie;
import com.lufoxt.movieseller.entity.Order;
import com.lufoxt.movieseller.entity.dto.CustomerDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerConverter {

    public static Customer convertCustomerDtoToCustomerEntity(CustomerDto customerDto, Set<Order> orders, Set<Movie> movies) {
        return new Customer(
                customerDto.getCustomerId(),
                customerDto.getUsername(),
                customerDto.getEmail(),
                customerDto.getPassword(),
                orders,
                movies
        );
    }

    public static CustomerDto convertCustomerEntityToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getId())
                .username(customer.getUsername())
                .email(customer.getEmail())
                .password(customer.getPassword())

                .executedOrderIds(customer.getOrders().stream()
                        .map(Order::getId)
                        .collect(Collectors.toList()))

                .purchasedMovieIds(customer.getPurchasedMovies().stream()
                        .filter(user -> user.getActiveStatus().equals(Boolean.TRUE))
                        .map(Movie::getId)
                        .collect(Collectors.toList()))

                .build();
    }
}
