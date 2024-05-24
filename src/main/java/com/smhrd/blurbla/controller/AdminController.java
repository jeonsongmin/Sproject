package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.model.AllMemberDTO;
import com.smhrd.blurbla.model.AnswerDTO;
import com.smhrd.blurbla.model.MemberDTO;
import com.smhrd.blurbla.model.QstnsDTO;
import com.smhrd.blurbla.service.AdminService;
import com.smhrd.blurbla.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/AdmApi")
public class AdminController {

    // Service
    private final AdminService adminService;
    private final MemberService memberService;

    // [관리자 페이지] 메인
    @PostMapping("/adminMain")
    public Map<String, Object> adminMain(){
        System.out.println("AdminController  >>>  adminMain !!");


        // 관리자 페이지의 항목들 받아오기
        long startTime = System.currentTimeMillis();    // 속도 측정 변수 (시작 시간)
        List<QstnsDTO> aQstnsList = adminService.qsntsList();   // 문의사항 리스트
        long endTime = System.currentTimeMillis();      // 속도 측정 변수 (끝난 시간)
        long duration = endTime - startTime;            // 속도 측정 변수 (계산 시간)
        System.out.println("Execution time 1: " + duration + " milliseconds"); // 측정속도 출력

        List<AllMemberDTO> aMemberList = memberService.adminMainMemberList();   // 회원정보 리스트
        List<MemberDTO> aPayMemberList = memberService.adminPayMainMemberList();   // 결제한 회원정보 리스트

        System.out.println("AdminController  >>>  adminMain !!");

        // 모델에 해당 항목들 보내주기 위한 세팅(model에 모두 담아 전송, 키:벨류 형식)
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("aQstnsList", aQstnsList);
        resMap.put("aMemberList", aMemberList);
        resMap.put("aPayMemberList", aPayMemberList);

        return resMap;
    }

    // [관리자 페이지] 문의사항
    @PostMapping("/adminInquiry")
    public Map<String, Object> adminInquiry(){
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

    // [관리자 페이지] 문의사항 답변
    @PostMapping("/adminAnswer")
    public List<AnswerDTO> adminAnswer(@RequestBody HashMap<String, Object> answerData){
        String qstn_idx = (String) answerData.get("qstn_idx");
        System.out.println("AdminController  >>>  adminAnswer !! 문의사항 고유번호 : " + qstn_idx);
        return adminService.qsntsToAnswer(qstn_idx);
    }

    // [관리자 페이지] 문의사항 답변 등록
    @PostMapping("/adminToAnswer")
    public String adminToAnswer(@RequestBody HashMap<String, Object> answerData){
        System.out.println("AdminController  >>>  adminToAnswer !!");

        String result = null;
        if (adminService.answerToQsnts(answerData) != null){
            adminService.qsntnsUpdate(answerData);  // 적용 성공시 해당 문의사항 상태를 답변완료로 변경
            result = "답변등록 성공";
        } else {
            result = "답변등록 실패";
        }
        return result;
    }


    // [관리자 페이지] 회원 관리 리스트
    @PostMapping("/adminUser")
    public List<AllMemberDTO> adminUser(){
        System.out.println("AdminController  >>>  AdminUser !!");
        // 관리자 페이지의 항목들 받아오기
        List<AllMemberDTO> aMemberList = memberService.adminMainMemberList();   // 회원정보 리스트
        // 모델에 해당 항목들 보내주기 위한 세팅(model에 모두 담아 전송, 키:벨류 형식)
        return aMemberList;
    }

    // [관리자 페이지] 회원 등급 임의변경
    @PostMapping("/memberRoleUpdate")
    public void memberRoleUpdate(@RequestBody HashMap<String, Object> memberData){
        System.out.println("AdminController  >>>  memberRoleUpdate !!");
        adminService.memberRoleUpdate(memberData);
    }

    // [관리자 페이지] 문의사항 List 삭제
    @PostMapping("/adminQsntsDelete")
    public String adminQsntsDelete(@RequestBody HashMap<String,String> qsntsDataList){
        System.out.println("AdminController  >>>  adminQsntsDelete !!");
        try {
            // 맵에서 리스트 형식으로 넣어줘야함.
            String result = qsntsDataList.get("qstns_idx");
            List reList = List.of(result.split(","));

            // 리스트에 담긴 번호를 하나씩 삭제하기
            for (int i = 0; i < reList.size(); i++){
                String qstn_idx = (String) reList.get(i);
                System.out.println(i+"번째 : " + qstn_idx);

                adminService.adminQsntsDelete(qstn_idx);
            }
        } catch (Exception e) {
            System.out.println("삭제 실패 : " + e);
            return "Fail";
        }
        return "Success";
    }

}
