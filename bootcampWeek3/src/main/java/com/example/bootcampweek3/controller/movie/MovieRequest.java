package com.example.bootcampweek3.controller.movie;

import com.example.bootcampweek3.Genre;
import com.example.bootcampweek3.service.movie.Movie;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class MovieRequest {
    @NotBlank
    private String name;

    @NotNull
    private Genre genre;

    @NotNull
    private Integer releaseYear;

    @NotBlank
    private String director;

    public Movie convertToMovie(){
        return Movie.builder()
                .name(name)
                .genre(genre)
                .releaseYear(releaseYear)
                .director(director)
                .build();
    }
}
