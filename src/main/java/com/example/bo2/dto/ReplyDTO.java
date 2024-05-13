package com.example.bo2.dto;

import com.example.bo2.entity.Board;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class ReplyDTO {
    private Long rno;
    private Long bno;
//    private Board board; // 참조할 부모
    private String replyText; // 댓글 내용
    private String replyer; // 작성자
    private LocalDate regDate;
    private LocalDate modDate;
}
