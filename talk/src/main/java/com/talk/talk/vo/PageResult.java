package com.talk.talk.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PageResult<T> {
    private int totalPage;
    private List<T> data;
}
