package com.lufoxt.movieseller.entity.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDto {

    private Long orderId;
    private Long customerId;
    private Long purchasedMovie;

}
