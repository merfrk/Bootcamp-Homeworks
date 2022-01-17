package com.example.bootcampWeek2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie addMovie(@RequestBody Movie movie) {
        return Movie.builder()
                .Id(movie.getId())
                .name(movie.getName())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .director(movie.getDirector())
                .cast(movie.getCast())
                .build();
    }

    @GetMapping("/movies/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getMoviesById(@PathVariable Long movieId) {
        return Movie.builder()
                .Id(movieId)
                .name("Interstellar")
                .genre(Genre.SCI_FI)
                .releaseYear(2014)
                .director("Christopher Nolan")
                .point(10)
                .cast(List.of(new String[]{"Matthew McConaughey", "Ellen Burstyn", "Mackenzie Foy", "John Lithgow", "Anne Hathaway"}))
                .build();
    }

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getAllMovies() {
        return List.of(
                Movie.builder()
                        .Id(4L)
                        .name("Interstellar")
                        .genre(Genre.SCI_FI)
                        .releaseYear(2014)
                        .director("Christopher Nolan")
                        .point(9)
                        .cast(List.of(new String[]{"Matthew McConaughey", "Ellen Burstyn", "Mackenzie Foy", "John Lithgow", "Anne Hathaway"}))
                        .build(),
                Movie.builder()
                        .Id(5L)
                        .name("Lost")
                        .genre(Genre.MYSTERY)
                        .releaseYear(2004)
                        .director("Bobby Roth")
                        .point(10)
                        .cast(List.of(new String[]{"Jorge Garcia", "Josh Holloway", "Yunjin Kim", "Evangeline Lilly", "Matthew Fox"}))
                        .build()
        );
    }

    @DeleteMapping("/movies/{movieName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable String movieName) {

    }


}
