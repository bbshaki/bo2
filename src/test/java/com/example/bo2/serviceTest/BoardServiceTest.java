package com.example.bo2.serviceTest;


import com.example.bo2.dto.BoardDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTest {

    @Autowired
    BoardService boardService;



    @Test
    public void registertest(){
        for(int i = 0; i < 100; i++){
        BoardDTO boardDTO  = BoardDTO.builder()
                .title("제목이요" + i)
                .content("내용이요" + i)
                .writer("작성자" + i)
                .build();

        Long bno =  boardService.register(boardDTO);

        log.info(bno);}
    }

    @Test
    public void readTest(){
        BoardDTO boardDTO = boardService.read(1L);

        log.info(boardDTO);
    }

    @Test
    public void modifyTest(){
        boardService.modify(BoardDTO.builder()
                        .bno(1L)
                        .title("서비스에서 수정한 제목")
                        .content("수정된 내용")
                .build());

        log.info(boardService.read(1L));
    }

    @Test
    public void deleteTest(){
        boardService.remove(2L);
        log.info(boardService.read(2L));

    }

    @Test
    public void listTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("9")
                .page(1)
                .build();

        log.info(boardService.list(pageRequestDTO));
    }
}
