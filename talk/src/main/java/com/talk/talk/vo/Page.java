package com.talk.talk.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Page {
    private int currentPage;
    private int perPage;

    public int getPage() {
        return currentPage <= 1 ? 0 : (currentPage - 1);
    }
}
