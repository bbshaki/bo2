package com.example.bo2.service;

import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.dto.ReplyDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.entity.Reply;
import com.example.bo2.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Board board = Board.builder()
                .bno(replyDTO.getBno())
                .build();
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        reply.setBoard(board);

        return replyRepository.save(reply).getRno();
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListofBoard(Long bno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0 ? 0: pageRequestDTO.getPage() -1,
                        pageRequestDTO.getSize(),
                        Sort.by("rno").descending());
        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
        List<ReplyDTO> replyDTOList = result.getContent().stream()
                .map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .collect(Collectors.toList());
        result.getTotalElements();
        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(replyDTOList)
                .total((int) result.getTotalElements()).build();
    }

    @Override
    public ReplyDTO read(Long rno) {

        return modelMapper.map(replyRepository.findById(rno).get(), ReplyDTO.class);
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = replyRepository.findById(replyDTO.getRno()).get();
        reply.changeText(replyDTO.getReplyText()); // 댓글 수정
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}
