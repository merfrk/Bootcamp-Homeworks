package com.example.bootcampweek3.repository.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieDaoImpl implements MovieDao {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    public MovieEntity save(MovieEntity entity) {
        return movieJpaRepository.save(entity);
    }

    @Override
    public MovieEntity retrieve(Long Id) {
        Optional<MovieEntity> movieEntityOptional = movieJpaRepository.findById(Id);
        if (movieEntityOptional.isPresent())
            return movieEntityOptional.get();
        else
            throw new RuntimeException();
    }

    @Override
    public void delete(Long Id) {
         movieJpaRepository.deleteById(Id);
    }


}
