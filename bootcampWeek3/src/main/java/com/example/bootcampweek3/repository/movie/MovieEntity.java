package com.example.bootcampweek3.repository.movie;

import com.example.bootcampweek3.Genre;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity(name = "movie")
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Integer releaseYear;
    @Column(nullable = false)
    private String director;

}
