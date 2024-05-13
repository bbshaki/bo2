package com.example.bo2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "board_img")
@ToString(exclude = "board")
@Getter
@Setter
public class BoardImg extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;
    private String imgName; // 이미지 파일명 - 중복을 제거하기 위해 uuid를 사용하면 새로운 파일명이 됨
    private String originImgName; // 기존 파일명
    private String repimgYn; // 대표이미지 지정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_bno")
    private Board board; // board 테이블 참조
}
