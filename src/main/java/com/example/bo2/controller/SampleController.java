package com.example.bo2.controller;

import com.example.bo2.dto.BoardDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/sample/")
@RequiredArgsConstructor
public class SampleController {

    private final BoardService boardService;

    @PostMapping("/register")
    public Long register(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("타이틀인데요")
                .content("내용인데요")
                .writer("작성자인데요")
                .build();
        log.info(boardDTO);
        return 100L;
    }

    @GetMapping("/read")
    public BoardDTO read(){
        return BoardDTO.builder()
                .bno(111L)
                .title("타이틀이요")
                .content("콘텐츠!")
                .writer("나는 작성자")
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<BoardDTO> modify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("수정한 타이틀")
                .build();
        log.info(boardDTO);
        if (true) {
            return new ResponseEntity<>(boardDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(boardDTO, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/a")
    public String a() {
        return "신짱아";
    }

    @GetMapping("/b")
    public Board b() {
        return Board.builder()
                .bno(11L)
                .title("제목")
                .build();
    }

    @GetMapping("/c")
    public List<Board> c() {
        List<Board> boardList = new ArrayList<>();
        for (long i = 1; i < 100; i++) {
            Board board = Board.builder()
                    .bno(i)
                    .title("title" + i)
                    .build();
            boardList.add(board);

        }
        return boardList;
    }

    @GetMapping("/d")
    public String d(Board board) {
        log.info(board.getTitle());
        return "환영합니다";
    }

    @PostMapping("/e")
    public String e(Board board) {
        log.info(board.getTitle());
        return "포스트 전송입니다~";
    }

    @GetMapping("/f")
    public String f(@RequestBody Board board) {
        log.info(board.getTitle());
        return "포스트 전송입니다~";
    }

    @GetMapping("/h")
    public ResponseEntity<Board> h() {
        Board board = Board.builder()
                .bno(1L)
                .title("title")
                .build();
        if (true) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(board, HttpStatus.NOT_FOUND);
        }
    }

}
