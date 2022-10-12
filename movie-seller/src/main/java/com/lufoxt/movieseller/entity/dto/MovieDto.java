package com.lufoxt.movieseller.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Long movieId;
    private String title;
    private String movieGenre;
    private String description;
    private List<Long> movieCustomerIds;
    private Boolean activeStatus;
    private Double rating;
    private Date releaseDate;
}
