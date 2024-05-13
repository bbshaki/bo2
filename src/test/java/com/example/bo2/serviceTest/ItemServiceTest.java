package com.example.bo2.serviceTest;

import com.example.bo2.dto.ItemDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;

@SpringBootTest
@Log4j2
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    public void registerTest(){
        ItemDTO itemDTO = ItemDTO.builder()
                .itemName("삭제될 아이템")
                .itemEx("슬퍼하지 말라!")
                .price(1500L)
                .itemQty(200L)
                .seller("윤아영")
                .build();

        Long ino = itemService.register(itemDTO);
    }

    @Test
    public void selectAllTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("n")
                .keyword("제로")
                .page(1)
                .build();

        log.info(itemService.list(pageRequestDTO));
    }

    @Test
    public void exPageTest(){
        ItemDTO itemDTO = itemService.detailed(1L);
        log.info(itemDTO);
    }

    @Test
    public void modify(){
        itemService.modify(ItemDTO.builder()
                        .ino(1L)
                        .itemName("Ray")
                        .itemEx("다시 봐도 제일 귀엽지~!")
                        .price(15000000L)
                        .itemQty(8L)
                .build());
        log.info(itemService.detailed(1L));
    }

    @Test
    public void deleteTest(){
        itemService.delete(4L);
    }


}

