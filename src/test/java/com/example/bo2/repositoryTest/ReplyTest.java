package com.example.bo2.repositoryTest;

import com.example.bo2.entity.Board;
import com.example.bo2.entity.Reply;
import com.example.bo2.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Log4j2
public class ReplyTest {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void register(){
        Board board = Board.builder()
                .bno(10L)
                .build();


        Reply reply = Reply.builder()
                .board(board)
                .replyer("유저")
                .replyText("이 댓글을 봐 댓글이야")
                .build();

        log.info(replyRepository.save(reply));
    }

    @Test
    public void registerInsertAll(){
        Board board = Board.builder()
                .bno(12L)
                .build();

        for (int i = 1; i < 200; i++){
            Reply reply = Reply.builder()
                    .board(board)
                    .replyer("유저" + i)
                    .replyText("이 댓글을 봐 댓글이야" + i)
                    .build();
            log.info(replyRepository.save(reply));
        }
    }

    @Test
    public void replyListTest(){
        List<Reply> replyList = replyRepository.listReplyFromBno(11L);
        replyList.forEach(reply -> log.info(reply));
    }
    @Test
    public void replyListTest2(){
        Board board = Board.builder()
                .bno(11L)
                .build();
        replyRepository.findByBoard(board).forEach(reply -> log.info(reply));
    }
    @Test
    public void replyListTest3(){

        replyRepository.findByBoard_Bno(11L).forEach(reply -> log.info(reply));
    }

    @Test
    @Transactional
    public void listOfBoardTest(){
        Pageable pageable = PageRequest.of(0, 5, Sort.by("rno").descending());
        replyRepository.listOfBoard(11L, pageable).forEach(reply -> log.info(reply));
        Page<Reply> a = replyRepository.listOfBoard(10L, pageable);
//        a.getContent();
//        a.getSize();
//        a.getTotalElements();
//        a.getSort();
//        a.getNumber();
    }

    @Test
    public void countTest(){
        log.info(replyRepository.countByBoard_Bno(11L));
    }

    @Test
    @Transactional
    public void findaaaTest(){
        replyRepository.findaaa().forEach(reply -> log.info(reply));
    }
}
