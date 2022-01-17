package com.example.bootcampweek3.service.movie;

public interface MovieService {
    Long create(Movie movie);

    Movie retrieve(Long id);

    void delete(Long Id);
}
