package com.example.bo2.repositoryTest;

import com.example.bo2.entity.Item;
import com.example.bo2.entity.Review;
import com.example.bo2.repository.ReviewRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void register(){

        Item item = Item.builder()
                .ino(1L)
                .build();

        Review review = Review.builder()
                .item(item)
                .reviewText("아반떼가 인생의 진리다")
                .reviewer("아반떼 오너")
                .build();

        log.info(reviewRepository.save(review));
    }

    @Test
    @Transactional
    public void reviewList() {
        List<Review> reviewList = reviewRepository.listReviewFromIno(1L);
        reviewList.forEach(review -> log.info(review));
    }



}
