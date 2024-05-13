package com.example.bo2.service;

import com.example.bo2.dto.BoardImgDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardImgService {
    public void register(Long bno, List<MultipartFile> boardImgFileList);

    public List<BoardImgDTO> imglist(Long bno);

    public void modify(Long[] ino);
}
