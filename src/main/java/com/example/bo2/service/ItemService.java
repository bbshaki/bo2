package com.example.bo2.service;

import com.example.bo2.dto.ItemDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.entity.Item;

import java.util.List;

public interface ItemService {

    // 상품 등록
    public Long register(ItemDTO itemDTO);
    // 상품 목록
    public PageResponseDTO<ItemDTO> list(PageRequestDTO pageRequestDTO);
    // 상품 상세페이지
    public ItemDTO detailed(Long ino);
    // 상품 수정
    public void modify(ItemDTO itemDTO);
    // 상품 삭제
    public void delete(Long ino);

}
