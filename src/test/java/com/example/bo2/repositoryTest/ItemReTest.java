package com.example.bo2.repositoryTest;

import com.example.bo2.entity.Item;
import com.example.bo2.repository.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class ItemReTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void register(){
        Item item = Item.builder()
                .itemName("레이")
                .itemEx("경차 중에 제일 귀여움")
                .price(12000000L)
                .itemQty(10L)
                .seller("죠르디")
                .build();
        itemRepository.save(item);
        log.info(itemRepository.findByIno(1L));
    }

    @Test
    public void searchAll(){
        String[] strs = {"n", "e", "s"};
        String keyword = "코카콜라";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("ino").descending());
        Page<Item> itemPage = itemRepository.searchAll(strs, keyword, pageable);

        itemPage.forEach(item -> log.info(item));
    }
}
