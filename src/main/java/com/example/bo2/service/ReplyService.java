package com.example.bo2.service;

import com.example.bo2.dto.*;
import com.example.bo2.entity.Board;

import java.util.List;

public interface ReplyService {

    public Long register(ReplyDTO replyDTO);

    public PageResponseDTO<ReplyDTO> getListofBoard(Long bno, PageRequestDTO pageRequestDTO);
//    public List<Board> select();

    public ReplyDTO read(Long rno);

    public void modify(ReplyDTO replyDTO);

    public void remove(Long rno);

}
