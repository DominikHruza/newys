package com.assignment.Newys.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ArticleDtoList {
    List<ArticleDtoList> dtoList;

    public ArticleDtoList(List<ArticleDtoList> dtoList) {
        this.dtoList = dtoList;
    }
}
