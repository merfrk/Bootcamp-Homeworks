package com.patika.bootcamp.patikabootcamp.service.actor;

import com.patika.bootcamp.patikabootcamp.repository.actor.ActorDao;
import com.patika.bootcamp.patikabootcamp.repository.actor.ActorEntity;
import com.patika.bootcamp.patikabootcamp.repository.movie.MovieDao;
import com.patika.bootcamp.patikabootcamp.service.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorDao actorDao;
    private final MovieDao movieDao;

    @Override
    public Long create(Actor actor) {
        ActorEntity entity = actor.convertToActorEntity();
        Long aLong = actorDao.create(entity);
        return aLong;
    }

    @Override
    public List<Movie> retrieveMovies(Long actorId) {
        return movieDao.retrieveByActorId(actorId)
                .stream()
                .map(Movie::convertFrom)
                .collect(Collectors.toList());
    }
}
