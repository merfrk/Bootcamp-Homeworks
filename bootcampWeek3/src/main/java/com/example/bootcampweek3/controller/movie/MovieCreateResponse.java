package com.example.bootcampweek3.controller.movie;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieCreateResponse {
    private Long Id;

    public static MovieCreateResponse convertToMovieResponse(Long Id){
        return MovieCreateResponse.builder()
                .Id(Id)
                .build();
    }
}
