package com.example.bootcampweek3.controller.movie;

import com.example.bootcampweek3.service.movie.Movie;
import com.example.bootcampweek3.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieCreateResponse create(@RequestBody @Valid MovieRequest request){
        Movie movie = request.convertToMovie();

        Long Id = movieService.create(movie);
        return MovieCreateResponse.convertToMovieResponse(Id);
    }

    @GetMapping("/movies/{Id}")
    public MovieResponse retrieve(@PathVariable Long Id){
        Movie movie = movieService.retrieve(Id);
        return MovieResponse.convertFrom(movie);
    }

    @DeleteMapping("/movies/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long Id){
         movieService.delete(Id);
    }

}
