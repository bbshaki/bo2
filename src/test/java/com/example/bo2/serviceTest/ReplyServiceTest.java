package com.example.bo2.serviceTest;

import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.dto.ReplyDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.service.ReplyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {

    @Autowired
    ReplyService replyService;

//    @Test
//    public void registerTest(){
//        ReplyDTO replyDTO = ReplyDTO.builder()
//                .replyText("댓글이다")
//                .replyer("작성자다")
//                .board(Board.builder()
//                        .bno(4L)
//                        .build())
//                .build();
//
//        replyService.register(replyDTO);
//    }

    @Test
    public void readTest(){
        log.info(replyService.read(605L));
    }

    @Test
    public void modify(){
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(605L)
                .replyText("수정한 댓글입니다")
                .build();
        replyService.modify(replyDTO);
        log.info(replyService.read(605L));
    }

    @Test
    public void removeTest(){
        replyService.remove(605L);
//        log.info(replyService.read(605L));
    }

    @Test
    public void getListOfBoardTest(){
        // rno, pagerequest
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResponseDTO<ReplyDTO> getListofBoard =
        replyService.getListofBoard(10L, pageRequestDTO);

        getListofBoard.getDtoList().forEach(replyDTO -> log.info(replyDTO));
    }






}
