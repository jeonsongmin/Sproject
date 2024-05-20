package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.model.QstnsDTO;
import com.smhrd.blurbla.model.X_BoardDTO;
import com.smhrd.blurbla.repository.BoardRepository;
import com.smhrd.blurbla.service.AdminService;
import com.smhrd.blurbla.service.MemberService;
import com.smhrd.blurbla.service.QstnsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/QstApi")

public class QstnsController {

    private final QstnsService qstnsService;

    // 고객센터의 문의 리스트 전체보기
    @PostMapping("/qstnsList")
    public List<QstnsDTO> qstnsList(){
        System.out.println("QstnsController >>>  qstnsList !!");
        List<QstnsDTO> qstnsList = qstnsService.findAll();   // tb_
        return qstnsList;
    }

    //
    @PostMapping("/qstnsInsert")
    public String qstnsInsert(@RequestBody HashMap<String, Object> insertData){

        String qstn_title = (String) insertData.get("qstn_title");
        String qstn_content = (String) insertData.get("qstn_content");
        String qstn_open = (String) insertData.get("qstn_open");
        String qstn_category = (String) insertData.get("qstn_category");
        String mb_email = (String) insertData.get("mb_email");

        System.out.println("============================");
        System.out.println("qstnsInsert ▶ qstn_title    : "+ qstn_title);
        System.out.println("qstnsInsert ▶ qstn_content  : "+ qstn_content);
        System.out.println("qstnsInsert ▶ qstn_open     : "+ qstn_open);
        System.out.println("qstnsInsert ▶ qstn_category : "+ qstn_category);
        System.out.println("qstnsInsert ▶ mb_email      : "+ mb_email);

        System.out.println("QstnsController >>>  qstnsInsert !!");

        return qstnsService.insert(qstn_title, qstn_content, qstn_open, qstn_category, mb_email);
    }

}
