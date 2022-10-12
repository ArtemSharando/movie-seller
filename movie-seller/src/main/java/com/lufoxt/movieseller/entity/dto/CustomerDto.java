package com.lufoxt.movieseller.entity.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CustomerDto {

    private Long customerId;
    private String username;
    private String email;
    private String password;
    private List<Long> purchasedMovieIds;
    private List<Long> executedOrderIds;
}
