package com.example.bo2.controller;

import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.dto.ReplyDTO;
import com.example.bo2.dto.SampleDTO;
import com.example.bo2.repository.ReplyRepository;
import com.example.bo2.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/replies/")
public class ReplyController {

    private final ReplyRepository replyRepository;
    private final ReplyService replyService;

//    @PostMapping(value = "/sin", produces = "application/json; charset = UTF-8",
//            consumes = "application/json; charset = UTF-8")
//    public SampleDTO sin(@RequestBody SampleDTO sampleDTO){
//        log.info(sampleDTO);
//        return sampleDTO;
//    }

    @PostMapping(value = "/new")
    public Map<String, Long> sin(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        Long rno = replyService.register(replyDTO);
        Map<String, Long> result = new HashMap<>();
        result.put("rno", rno);
        return result;
    }

    //읽기
    @GetMapping(value = "/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno,
                                             PageRequestDTO pageRequestDTO){
        log.info("현재 본문은 : " + bno);
        log.info(pageRequestDTO);
        PageResponseDTO<ReplyDTO> pageResponseDTO =
                replyService.getListofBoard(bno, pageRequestDTO);
        return pageResponseDTO;
    }

    @GetMapping("/get/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno){

        return replyService.read(rno);
    }

    @PutMapping("/modify/{rno}")
    public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO){
        log.info("여기까지 오십니까?");
        replyDTO.setRno(rno);
        replyService.modify(replyDTO);
        Map<String, Long> map = new HashMap<>();
        map.put("rno", rno);

        return map;
    }

    @DeleteMapping("/remove/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno){
        replyService.remove(rno);
        Map<String, Long> map = new HashMap<>();
        map.put("rno", rno);

        return map;
    }
}
