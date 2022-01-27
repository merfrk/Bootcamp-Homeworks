package com.patika.bootcamp.patikabootcamp.service.movie;

import com.patika.bootcamp.patikabootcamp.repository.actor.ActorDao;
import com.patika.bootcamp.patikabootcamp.repository.actor.ActorEntity;
import com.patika.bootcamp.patikabootcamp.repository.matching.MatchingDao;
import com.patika.bootcamp.patikabootcamp.repository.matching.MatchingEntity;
import com.patika.bootcamp.patikabootcamp.repository.movie.MovieDao;
import com.patika.bootcamp.patikabootcamp.repository.movie.MovieEntity;
import com.patika.bootcamp.patikabootcamp.service.actor.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;
    private final ActorDao actorDao;
    private final MatchingDao matchingDao;

    @Override
    public Long create(Movie movie, List<Actor> actors, List<Long> actorIds) {
        List<ActorEntity> existingActors = retrieveExistingActors(actorIds);
        List<ActorEntity> createdActors = createActors(actors);
        MovieEntity createdMovie = movieDao.save(movie.convertToMovieEntity());

        ArrayList<ActorEntity> actorEntities = new ArrayList<>();
        actorEntities.addAll(existingActors);
        actorEntities.addAll(createdActors);

        List<MatchingEntity> matchingEntities = actorEntities.stream()
                .map(actorEntity -> {
                    MatchingEntity matchingEntity = new MatchingEntity();
                    matchingEntity.setMovie(createdMovie);
                    matchingEntity.setActor(actorEntity);
                    return matchingEntity;
                }).collect(Collectors.toList());

        matchingDao.create(matchingEntities);
        return createdMovie.getId();
    }

    private List<ActorEntity> createActors(List<Actor> actors) {
        if (!CollectionUtils.isEmpty(actors)) {
            List<ActorEntity> actorEntities = actors.stream()
                    .map(Actor::convertToActorEntity)
                    .collect(Collectors.toList());

            return actorDao.create(actorEntities);
        }

        return new ArrayList<>();
    }

    private List<ActorEntity> retrieveExistingActors(List<Long> actorIds) {
        if (!CollectionUtils.isEmpty(actorIds)) {
            List<ActorEntity> retrievedActors = actorDao.retrieve(actorIds);

            if (retrievedActors.size() < actorIds.size()) {
                throw new RuntimeException("Verilen actor id db'de bulunamamıştır");
            }

            return retrievedActors;
        }

        return new ArrayList<>();
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
