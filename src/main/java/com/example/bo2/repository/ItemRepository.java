package com.example.bo2.repository;

import com.example.bo2.entity.Item;
import com.example.bo2.repository.search.ItemSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemSearch {

    @Query("select i from Item i where i.itemName like concat('%',:keyword,'%')")
    Page<Item> findKeyword(String keyword, Pageable pageable);

    Item findByIno(Long ino);
}
