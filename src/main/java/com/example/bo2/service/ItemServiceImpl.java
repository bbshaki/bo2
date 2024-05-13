package com.example.bo2.service;

import com.example.bo2.dto.ItemDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.entity.Item;
import com.example.bo2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.With;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
@Transactional
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    @Override
    public Long register(ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO, Item.class);

        Long ino = itemRepository.save(item).getIno();

        return ino;
    }

    @Override
    public PageResponseDTO<ItemDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("ino");
        Page<Item> itemPage = itemRepository.searchAll(types, keyword, pageable);
        List<ItemDTO> itemDTOList = itemPage.getContent().stream().map(item -> modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<ItemDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(itemDTOList)
                .total((int)itemPage.getTotalElements())
                .build();
    }

    @Override
    public ItemDTO detailed(Long ino) {
        Optional<Item> item = itemRepository.findById(ino);

        if(item.isEmpty()){
            return null;
        }
        ItemDTO itemDTO = modelMapper.map(item.get(), ItemDTO.class);
        return itemDTO;
    }

    @Override
    public void modify(ItemDTO itemDTO) {
        Optional<Item> result = itemRepository.findById(itemDTO.getIno());
        Item item = result.orElseThrow();
        item.change(itemDTO.getItemName(),itemDTO.getItemEx()
                ,itemDTO.getPrice(),itemDTO.getItemQty());
        itemRepository.save(item);
    }

    @Override
    public void delete(Long ino) {
        itemRepository.deleteById(ino);
    }
}
