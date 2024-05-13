package com.example.bo2.dto;

import com.example.bo2.entity.Board;
import com.example.bo2.entity.BoardImg;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardImgDTO {

    private Long ino;
    private String imgName; // 이미지 파일명 - 중복을 제거하기 위해 uuid를 사용하면 새로운 파일명이 됨
    private String originImgName; // 기존 파일명
    private String repimgYn; // 대표이미지 지정

    private LocalDate regDate;
    private LocalDate modDate;
    private static ModelMapper modelMapper = new ModelMapper();

    public static ModelMapper getMapper(){

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;

    }

    public static BoardImgDTO of(BoardImg boardImg){
        return getMapper().map(boardImg, BoardImgDTO.class);
    }
}
