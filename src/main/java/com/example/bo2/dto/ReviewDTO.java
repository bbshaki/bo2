package com.example.bo2.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDTO {
    private Long vno;
    private Long ino;
    private String reviewText;
    private String reviewer;
    private LocalDate regDate;
    private LocalDate modDate;
}
