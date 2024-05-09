package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.model.Board;
import com.smhrd.blurbla.model.File;
import com.smhrd.blurbla.repository.BoardRepository;
import com.smhrd.blurbla.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StartController {

    @Autowired
    private BoardRepository boardRepository;    // 테스트용 게시판 JPA
    @Autowired
    private FileRepository fileRepository;      // 파일 전용 JPA

    @RequestMapping("/")
    public String list(Model model){
        System.out.println("StartController !!");
        List<Board> list = boardRepository.findAll();   // tb_
        List<File> fileList = fileRepository.findAll();
//        List<Board> list = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "created_at"));
        model.addAttribute("list", list);
        model.addAttribute("fileList", fileList);
        return "index.html";
    }

}
