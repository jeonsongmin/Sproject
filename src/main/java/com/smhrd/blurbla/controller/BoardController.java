package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.model.BoardDTO;
import com.smhrd.blurbla.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Customer")

public class BoardController {
    @Autowired
    private BoardRepository boardRepository;    // 작성글 전용 JPA

    // 고객센터의 문의 리스트 전체보기
    @PostMapping("/BoardList")
    public List<BoardDTO> list(Model model){
        System.out.println("Rest_BoardController !!");
        List<BoardDTO> boards = boardRepository.findAll();   // tb_
        System.out.println("[사용자] 문의사항 리스트 : " + boards);
        return boards;
    }
}
