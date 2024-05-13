package com.example.bo2.service;

import com.example.bo2.dto.BoardImgDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.entity.BoardImg;
import com.example.bo2.repository.BoardImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardImgServiceImpl implements BoardImgService{
    private final BoardImgRepository boardImgRepository;
    private final ModelMapper modelMapper;

    @Value("${project.uploadpath}")
    private String uploadpath;

    //날짜별로 폴더를 생성하는 method
    public String makeDir(){
        Date date = new Date();
        SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd");
        String now = yyMMdd.format(date);
        String path = uploadpath + "\\" + now; // 풀경로
        // c"/upload/240513
        File file = new File(path);
        if(file.exists() == false){ // exists 이미 파일이 존재한다면? ture, 존재하지 않는다면 false
            file.mkdir(); // make directory 폴더 생성
        }
        return path;
    }


    @Override
    public void register(Long bno, List<MultipartFile> boardImgFileList) {
        log.info("boardimgserviceBno"+bno);
        if(boardImgFileList != null && boardImgFileList.size() > 0){
            for(MultipartFile a : boardImgFileList){
                if(a.getOriginalFilename().length() == 0){
                    continue;
                }
                String origin = a.getOriginalFilename();
                log.info(origin);
                // 파일명에서 마지막 \\ 이전은 잘라냄
                String originName = origin.substring(origin.lastIndexOf("\\") + 1);
                String filePath = makeDir(); // 파일을 저장할 경로 c:/upload/240513(오늘날짜)/imgName
                String uuid = UUID.randomUUID().toString(); // 겹치는 파일명이 업로드 되었을 때 임의로 imgName 지정
                log.info("uuid : " + uuid);
                // db 저장용
                String newName = uuid + "_" + originName;
                // 저장시 사용
                String saveName = filePath + "\\" + uuid + "_" + originName;
                log.info(saveName);

                File save = new File(saveName);
                try {
                    a.transferTo(save);
                } catch (IOException e){

                }
                Board board = Board.builder().bno(bno).build();
                // 저장한 파일의 제반사항을 db에 저장
                BoardImg boardImg = new BoardImg();
                boardImg.setOriginImgName(originName);
                boardImg.setImgName(newName);
                boardImg.setBoard(board);
                boardImgRepository.save(boardImg);
            }
        }
    }

    @Override
    public List<BoardImgDTO> imglist(Long bno) {
        List<BoardImg> boardImgList = boardImgRepository.findByBoard_BnoOrderByIno(bno);
        List<BoardImgDTO> boardImgDTOList = new ArrayList<>();
        for (BoardImg a : boardImgList){
            BoardImgDTO boardImgDTO = new BoardImgDTO();
            boardImgDTO = boardImgDTO.of(a);
            boardImgDTOList.add(boardImgDTO);
            log.info("날짜" + boardImgDTO);
        }
        return boardImgDTOList;
    }

    @Override
    public void modify(Long[] ino) {
        // 반복
        for(int i = 0; i < ino.length; i++) {
            if(ino[i] != null  ){

                BoardImg boardImg = boardImgRepository.findById(ino[i]).get();
                BoardImgDTO boardImgDTO = modelMapper.map(boardImg, BoardImgDTO.class);
                LocalDate regDate = boardImgDTO.getRegDate();
                String now = regDate.format(DateTimeFormatter.ofPattern("yyMMdd"));
                String imgName = boardImgDTO.getImgName();
                String path = uploadpath + now + "\\" + imgName;
                log.info(path);
                File file = new File(path);
                file.delete();
                boardImgRepository.deleteById(ino[i]);
            }


        }



//        File file = new File();
//        file.delete(); // 물리적인 파일 삭제
//        boardImgRepository.deleteById(ino);
    }

//    @Override
//    public void delete(Long bno) {
//        List<BoardImg> boardImgList = boardImgRepository.findByBoard_BnoOrderByIno(bno);
//        if (boardImgList != null && !boardImgList.isEmpty()){
//            for(BoardImg boardImg : boardImgList){
//                String imgName = boardImg.getImgName();
//                Long ino = boardImg.getIno();
//                String filePath = makeDir();
//                File file = new File(filePath + "\\" + imgName);
//
//                boardImgRepository.delete(boardImg);
//            }
//        }
//
//    }


    // File delete = new File(경로);
    // delete.delete();
}
