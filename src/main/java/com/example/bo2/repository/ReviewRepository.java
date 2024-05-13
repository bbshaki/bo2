package com.example.bo2.repository;

import com.example.bo2.entity.Item;
import com.example.bo2.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select v from Review v where v.item.ino = :ino")
    List<Review> listReviewFromIno(Long ino);

    List<Review> findByItem(Item item);

    List<Review> findByItem_Ino(Long ino);

    @Query("select v from Review v where v.item.ino = :ino")
    Page<Review> listOfItem(Long ino, Pageable pageable);

    Long countByItem_Ino(Long ino);

    @Query(value = "select * from Review", nativeQuery = true)
    List<Review> findAllR();
}
