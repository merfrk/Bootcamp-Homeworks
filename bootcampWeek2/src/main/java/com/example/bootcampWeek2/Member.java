package com.example.bootcampWeek2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Member {
    private Long Id;
    private String name;
    private List<WatchList> watchLists;

    void addList(WatchList watchList){
        this.watchLists.add(watchList);
    }
}
