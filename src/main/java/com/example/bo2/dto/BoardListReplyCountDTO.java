package com.example.bo2.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BoardListReplyCountDTO {

    private Long bno;
    private String title;
    private String writer;
    private LocalDate regDate;
    private LocalDate modDate;
    private Long replyCount;
}
