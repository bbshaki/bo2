package com.example.bo2.controller;


import com.example.bo2.dto.*;
import com.example.bo2.service.BoardImgService;
import com.example.bo2.service.BoardService;
import com.example.bo2.service.ReplyService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final BoardImgService boardImgService;


    @GetMapping("/register")
    public void register(){

    }
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("boardImgFile") List<MultipartFile> boardImgFileList){
        // 기존 실습한 multipartfile 받는 내용을 service에 만들어서 넘겨준다

        if(bindingResult.hasErrors()){
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute
                    ("errors", bindingResult.getAllErrors());
            log.info("오류발생!!!!!!!!!" + bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        Long bno = boardService.register(boardDTO);
        boardImgService.register(bno, boardImgFileList);
        redirectAttributes.addFlashAttribute("result", bno);
        return "redirect:/board/list";
    }
    @GetMapping("/list")
    public void list(Model model, PageRequestDTO pageRequestDTO){
        PageResponseDTO<BoardListReplyCountDTO> pageResponseDTO = boardService.listWithReplyCount(pageRequestDTO);
//        List<Board> boardList = boardService.select();
//        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("list", pageResponseDTO);
//        boardList.forEach(board -> log.info(board));

    }
    @GetMapping({"/read", "/modify"})
    public void read(Long bno, Model model, PageRequestDTO pageRequestDTO){
        model.addAttribute("dto", boardService.read(bno));
        model.addAttribute("imgdto", boardImgService.imglist(bno));
//        model.addAttribute("rDto", replyService.read(150L));
    }
    @PostMapping("/modify")
    public String modify(@Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         PageRequestDTO pageRequestDTO, Long[] ino,
                         @RequestParam("boardImgFile") List<MultipartFile> boardImgFileList){
        log.info("여기까지 들어옴?" + Arrays.toString(ino));

        if(bindingResult.hasErrors()){
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute
                    ("errors", bindingResult.getAllErrors());
            log.info("오류발생!!!!!!!!!" + bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?"+pageRequestDTO.getLink();
        }

        boardService.modify(boardDTO);
        boardImgService.modify(ino);
        boardImgService.register(boardDTO.getBno(), boardImgFileList);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read?"+pageRequestDTO.getLink();
    }
    @PostMapping("/remove")
    public String remove(Long bno){

        boardService.remove(bno);
        return "redirect:/board/list";
    }

    @GetMapping("/sample")
    public void sample(){

    }

    @ResponseBody
    @GetMapping("/sam")
    public String sam(){
        return "redirect:/board/list";
    }

    @GetMapping("/board")
    public void board(){
    }

    @GetMapping("/file")
    public void file(){

    }
// 파일업로드
    @PostMapping("/file")
    public String files(BoardDTO boardDTO,@RequestParam("file") MultipartFile[] file){
        if(file != null && file.length > 0){
            for (int i = 0; i < file.length; i++){
                log.info(file[i].getContentType());
                log.info(file[i].getOriginalFilename());
                String path = "c:\\aaa\\" + file[i].getOriginalFilename();
                File save = new File(path);
                try {
                    file[i].transferTo(save);
                } catch (IOException e){

                }
            }
        }
//        log.info(boardDTO);
//        log.info(file);
//        String contentType = file[0].getContentType();
//        Long size = file.getSize();
//        String origin = file.getOriginalFilename();
//        log.info("파일타입 : " + contentType);
//        log.info("사이즈 : " + size);
//        log.info("파일명 : " + origin);
//        File save = new File("c:\\aaa.png");
//        try {
//            file.transferTo(save);
//        } catch (IOException e){
//
//        }
        return null;
    }



}
