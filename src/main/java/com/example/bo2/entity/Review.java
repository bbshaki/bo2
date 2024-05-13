package com.example.bo2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
    private String reviewText;
    private String reviewer;

    public void changeRText(String text){
        this.reviewText = text;
    }

}
