package com.example.bootcampweek3.service.movie;

import com.example.bootcampweek3.Genre;
import com.example.bootcampweek3.repository.movie.MovieEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Movie {
    private String name;
    private Genre genre;
    private Integer releaseYear;
    private String director;

    MovieEntity convertToMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setName(getName());
        movieEntity.setDirector(getDirector());
        movieEntity.setReleaseYear(getReleaseYear());
        movieEntity.setGenre(getGenre());
        return movieEntity;
    }

    public static Movie convertFrom(MovieEntity entity){
        return Movie.builder()
                .name(entity.getName())
                .genre(entity.getGenre())
                .releaseYear(entity.getReleaseYear())
                .director(entity.getDirector())
                .build();
    }
}
