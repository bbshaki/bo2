package com.example.bo2.service;

import com.example.bo2.dto.BoardDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.entity.Board;

import java.util.List;

public interface BoardService {

    public Long register(BoardDTO boardDTO);

    public List<Board> select();

    // 페이징처리, 검색처리
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    public BoardDTO read(Long bno);

    public void modify(BoardDTO boardDTO);

    public void remove(Long bno);

}
