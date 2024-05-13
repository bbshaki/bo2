package com.example.bo2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino; // 상품번호

    private String itemName; // 상품명
    private String itemEx; // 상품상세설명
    private Long price; // 상품가격
    private Long itemQty; // 상품수량
    private String seller; // 판매자

    public void change(String itemName, String itemEx, Long price, Long itemQty) {
        this.itemName = itemName;
        this.itemEx = itemEx;
        this.price = price;
        this.itemQty = itemQty;
    }
}
