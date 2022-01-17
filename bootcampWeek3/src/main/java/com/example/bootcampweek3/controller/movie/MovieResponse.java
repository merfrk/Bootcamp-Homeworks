package com.example.bootcampweek3.controller.movie;

import com.example.bootcampweek3.Genre;
import com.example.bootcampweek3.service.movie.Movie;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MovieResponse {
    private String name;
    private Genre genre;
    private Integer releaseYear;
    private String director;
    private List<String> castList;

    public static MovieResponse convertFrom(Movie movie) {
        return MovieResponse.builder()
                .name(movie.getName())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .director(movie.getDirector())
                .build();
    }
}
