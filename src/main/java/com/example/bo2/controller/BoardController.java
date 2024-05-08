package com.example.bo2.controller;


import com.example.bo2.dto.BoardDTO;
import com.example.bo2.dto.BoardListReplyCountDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.service.BoardService;
import com.example.bo2.service.ReplyService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;


    @GetMapping("/register")
    public void register(){

    }
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute
                    ("errors", bindingResult.getAllErrors());
            log.info("오류발생!!!!!!!!!" + bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        Long bno = boardService.register(boardDTO);
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
//        model.addAttribute("rDto", replyService.read(150L));
    }
    @PostMapping("/modify")
    public String modify(@Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         PageRequestDTO pageRequestDTO){
        log.info("여기까지 들어옴?");

        if(bindingResult.hasErrors()){
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute
                    ("errors", bindingResult.getAllErrors());
            log.info("오류발생!!!!!!!!!" + bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?"+pageRequestDTO.getLink();
        }

        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read?"+pageRequestDTO.getLink();
    }
    @PostMapping("/remove")
    public void remove(Long bno){
        boardService.remove(bno);
    }

    @GetMapping("/sample")
    public void sample(){

    }

    @ResponseBody
    @GetMapping("/sam")
    public String sam(){
        return "redirect:/board/list";
    }



}
