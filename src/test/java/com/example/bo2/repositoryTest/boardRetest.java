package com.example.bo2.repositoryTest;

import com.example.bo2.dto.BoardDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.repository.BoardRepository;
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
public class boardRetest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void searchTest(){
        Pageable pageable = PageRequest.of(0, 10);
        boardRepository.search1(pageable);
    }

    @Test
    public void savetest(){

        for(int i = 0; i< 500; i++){
            Board board = Board.builder()
                    .title("제목" + i)
                    .content("내용"+ i)
                    .writer("작성자" + i)
                    .build();
            boardRepository.save(board);
        }

    }

    @Test
    public void searchAll(){
        //String[] types, String keyword, Pageable pageable

        String[] strs  = {"t", "c"};

        String keyword = "용3";
        Pageable pageable = PageRequest.of(0, 7, Sort.by("bno").descending());

        Page<Board> boardPage =  boardRepository.searchAll(strs, keyword, pageable);



        boardPage.forEach(board -> System.out.println(board));
        System.out.println("총 페이지" + boardPage.getTotalPages());
        System.out.println("사이즈" + boardPage.getSize());
        System.out.println("페이지 번호" +  boardPage.getNumber());
        System.out.println("시작페이지 " + boardPage.isFirst());
        System.out.println("다음페이지 " + boardPage.hasNext());

    }






}
