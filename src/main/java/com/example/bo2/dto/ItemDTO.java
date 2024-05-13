package com.example.bo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private Long ino;
    @NotBlank
    @Size(min = 3, max = 20)
    private String itemName;
    @NotBlank
    private String itemEx;
    @NotNull
    private Long price;
    @NotNull
    private Long itemQty;
    @NotBlank
    private String seller;
    private LocalDate regDate;
    private LocalDate modDate;
}
