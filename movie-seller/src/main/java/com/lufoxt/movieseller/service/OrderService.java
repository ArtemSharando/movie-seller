package com.lufoxt.movieseller.service;

import com.lufoxt.movieseller.entity.dto.OrderDto;

public interface OrderService {

    void createOrder(OrderDto orderDto);

}
