package com.example.bootcampweek3.repository.movie;

import java.util.List;

public interface MovieDao {
    MovieEntity save(MovieEntity entity);
    MovieEntity retrieve(Long Id);
    void delete(Long Id);
}
