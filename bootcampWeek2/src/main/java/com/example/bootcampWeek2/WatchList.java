package com.example.bootcampWeek2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WatchList {
    private Long Id;
    private String name;
    private List<Movie> movies;

     void addMovieToList(Movie movie){
         movies.add(movie);
     }
}
