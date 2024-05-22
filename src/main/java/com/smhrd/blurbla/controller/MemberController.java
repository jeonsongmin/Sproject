package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.model.FileDTO;
import com.smhrd.blurbla.model.MemberDTO;
import com.smhrd.blurbla.model.PaymentDTO;
import com.smhrd.blurbla.repository.MemberRepository;
import com.smhrd.blurbla.service.KakaoPayService;
import com.smhrd.blurbla.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/MemApi")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 회원가입하기
    @PostMapping("/join")
    public String insertJoin(@RequestBody HashMap<String, Object> joinData){
        System.out.println("*** MemberController >>> insertJoin on! ");
        memberService.memberInsert(joinData);
        String result = "Success";
        return result;
    }

    // 회원 로그인하기
    @PostMapping("/login")
    public MemberDTO selectLogin(@RequestBody HashMap<String, Object> loginData){
        // 레포지토리에서 email의 회원 정보 가져오기

        String mb_email = (String) loginData.get("mb_email");
        String mb_pw = (String) loginData.get("mb_pw");

        MemberDTO memberDto = memberService.seleteLogin(mb_email);  // 해당 회원의 모든정보 가져오기
        MemberDTO react2Data = null; // 로그인 성공시 데이터 넘겨주고 실패하면 안넘겨줌

        try {
            // 회원 정보를 기반으로 비교 (널과 비번)
            if (mb_pw.equals(memberDto.getMb_pw())) {    // 성공시
                System.out.println("============================");
                System.out.println("DB data pw : " + memberDto.getMb_pw());
                System.out.println("Inpurt  pw : " + mb_pw);
                System.out.println("Login Success");
                react2Data = memberDto;
            } else {    // 실패시
                System.out.println("============================");
                System.out.println("DB data pw : " + memberDto.getMb_pw());
                System.out.println("Inpurt  pw : " + mb_pw);
                System.out.println("Login Fail");
            }
        }catch (Exception e){
            System.out.println("Login DB Fail Err !! " + e);
        }
        return react2Data;
    }

    // [로그아웃] 세션 정보 지우기
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session= request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

    // [마이페이지] 작업내역 리스트
    @PostMapping("/Mypage")
    public List<FileDTO> myPage(@RequestBody HashMap<?, ?> resultMap){
        String mb_email = (String) resultMap.get("mb_email");
        System.out.println("*** MemberController >>> myPage on! : " + mb_email);
        return memberService.myPageSelectAll(mb_email);
    }

    // [마이페이지] 결재내역
    @PostMapping("/MypagePay")
    public List<PaymentDTO> MypagePay(@RequestBody HashMap<?,?> reslutMap){
        String mb_email = (String) reslutMap.get("mb_email");
        System.out.println("*** MemberController >>> MypagePay on! : " + mb_email);
        return memberService.paymentList(mb_email);
    }

}




