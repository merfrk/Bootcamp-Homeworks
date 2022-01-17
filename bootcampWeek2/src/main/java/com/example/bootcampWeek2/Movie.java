package com.example.bootcampWeek2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@Setter
@Builder
public class Movie {

    private Long Id;
    @NotBlank
    private String name;
    private Genre genre;
    private Integer releaseYear;
    private String director;
    @NotEmpty
    private List<String> cast;
    private Integer point;
}
