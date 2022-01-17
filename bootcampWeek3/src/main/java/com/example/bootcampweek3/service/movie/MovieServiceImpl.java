package com.example.bootcampweek3.service.movie;

import com.example.bootcampweek3.repository.movie.MovieDao;
import com.example.bootcampweek3.repository.movie.MovieEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;


    @Override
    public Long create(Movie movie) {
        MovieEntity createdMovie = movieDao.save(movie.convertToMovieEntity());
        return createdMovie.getId();
    }

    @Override
    public Movie retrieve(Long id) {
        MovieEntity entity = movieDao.retrieve(id);
        return Movie.convertFrom(entity);
    }

    @Override
    public void delete(Long Id) {
         movieDao.delete(Id);
    }

}
