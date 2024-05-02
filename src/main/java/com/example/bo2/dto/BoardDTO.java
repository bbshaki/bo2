package com.example.bo2.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {
    private Long bno;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String writer;

    private LocalDate regDate;

    private LocalDate modDate;

}
