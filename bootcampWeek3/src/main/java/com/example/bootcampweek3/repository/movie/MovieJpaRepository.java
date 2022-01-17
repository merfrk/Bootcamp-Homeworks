package com.example.bootcampweek3.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {
}
