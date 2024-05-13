package com.example.bo2.repository.search;

import com.example.bo2.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemSearch {

    Page<Item> searchAll(String[] types, String keyword, Pageable pageable);

}
