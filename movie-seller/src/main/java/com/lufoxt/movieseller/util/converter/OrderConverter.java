package com.lufoxt.movieseller.util.converter;

import com.lufoxt.movieseller.entity.Movie;
import com.lufoxt.movieseller.entity.Order;
import com.lufoxt.movieseller.entity.Customer;
import com.lufoxt.movieseller.entity.dto.OrderDto;

public class OrderConverter {
    public static Order convertOrderDtoToOrderEntity(OrderDto orderDto, Movie movie, Customer customer) {
        return new Order(
                orderDto.getOrderId(),
                movie,
                customer
        );
    }
}
