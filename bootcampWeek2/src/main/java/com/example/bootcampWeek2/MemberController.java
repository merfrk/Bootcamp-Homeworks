package com.example.bootcampWeek2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MemberController {

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public Member createMember(@RequestBody Member member) {
        return Member.builder()
                .Id(member.getId())
                .name(member.getName())
                .build();
    }

    @GetMapping("/members/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public Member getMemberById(@PathVariable Long memberId) {
        return Member.builder()
                .Id(memberId)
                .name("Ahmet")
                .build();
    }

    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> getAllMembers() {
        return List.of(
                Member.builder()
                        .Id(3L)
                        .name("Ahmet")
                        .build(),

                Member.builder()
                        .Id(2L)
                        .name("Emirhan")
                        .build()

        );
    }

    @PostMapping("/members/{memberId}/rateMovie")
    public Movie rateMovie(@PathVariable Long memberId, @RequestParam Long movieId, @RequestParam Integer point) {
        return Movie.builder()
                .Id(movieId)
                .name("Interstellar")
                .genre(Genre.SCI_FI)
                .releaseYear(2014)
                .director("Christopher Nolan")
                .cast(List.of(new String[]{"Matthew McConaughey", "Ellen Burstyn", "Mackenzie Foy", "John Lithgow", "Anne Hathaway"}))
                .point(point)
                .build();
    }

    @PostMapping("/members/{memberId}/createWatchList")
    @ResponseStatus(HttpStatus.CREATED)
    public WatchList createWatchList(@PathVariable Long memberId, @RequestBody  WatchList watchList){
        return WatchList.builder()
                .Id(1L)
                .name(watchList.getName())
                .build();
    }

    @PostMapping("/members/{memberId}/watchList/{watchListId}/movie/{movieId}")
    public WatchList addMovieToWatchList(@PathVariable Long memberId, @PathVariable Long watchListId, @PathVariable Long movieId){
        return WatchList.builder()
                .Id(watchListId)
                .name("MyWatchList")
                .movies(List.of(new Movie[]{
                        Movie.builder()
                                .Id(movieId)
                                .name("Interstellar")
                                .genre(Genre.SCI_FI)
                                .director("Christopher Nolan")
                                .releaseYear(2014)
                                .build()
                }))
                .build();
    }

}
