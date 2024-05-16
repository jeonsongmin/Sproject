package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.model.MemberDTO;
import com.smhrd.blurbla.model.QstnsDTO;
import com.smhrd.blurbla.service.AdminService;
import com.smhrd.blurbla.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/AdmApi")
public class AdminController {

    // Service
    private final AdminService adminService;
    private final MemberService memberService;

    // [관리자 페이지] 메인
    @PostMapping("/adminMain")
    public Model adminMain(Model model){
        System.out.println("AdminController  >>>  adminMain !!");
        // 관리자 페이지의 항목들 받아오기
        List<QstnsDTO> aQstnsList = adminService.qsntsList();   // 문의사항 리스트
        List<MemberDTO> aMemberList = memberService.adminMainMemberList();   // 회원정보 리스트

        // 모델에 해당 항목들 보내주기 위한 세팅(model에 모두 담아 전송, 키:벨류 형식)
        model.addAttribute("aQstnsList", aQstnsList);
        model.addAttribute("aMemberList", aMemberList);

        return model;
    }

    // [관리자 페이지] 문의사항
    @PostMapping("/adminInquiry")
    public Map<String, Object> adminInquiry(Model model){
        System.out.println("AdminController  >>>  adminInquiry !!");

        // 관리자 페이지의 항목들 받아오기
        List<QstnsDTO> aQstnsList = adminService.qsntsList();   // 문의사항 리스트
        int createQsntsCount = adminService.createQsntsCount();   // 당일 생성된 문의 카운트
        int standQsntsCount = adminService.standQsntsCount();   // 대기중인 문의 카운트
        int answerQsntsCount = adminService.answerQsntsCount();   // 답변완료 문의 카운트

        // 모델에 해당 항목들 보내주기 위한 세팅(model에 모두 담아 전송, 키:벨류 형식)
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("aQstnsList", aQstnsList);
        resMap.put("createQsntsCount", createQsntsCount);
        resMap.put("standQsntsCount", standQsntsCount);
        resMap.put("answerQsntsCount", answerQsntsCount);

        return resMap;
    }
}
