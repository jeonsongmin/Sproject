package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.model.MemberDTO;
import com.smhrd.blurbla.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;    // 작성글 전용 JPA

    // 회원가입하기
    @PostMapping("/join")
    public String insertJoin(@RequestBody HashMap<String, Object> joinData){

        String mb_email = (String) joinData.get("mb_email");
        String mb_pw = (String) joinData.get("mb_pw");

        System.out.println("============================");
        System.out.println("join ▶ mb_email    : "+ mb_email);
        System.out.println("join ▶ mb_pw       : "+ mb_pw);

        memberRepository.save(new MemberDTO(mb_email, mb_pw, "M", new Date()));
        String result="";
        System.out.println("join ▶ RedirectView insertJoin on!");

        result = "Success";
        return result;
    }

    // 회원 로그인하기
    @GetMapping("/Login")
    public MemberDTO selectLogin(@RequestParam String mb_email, String mb_pw){
        // 레포지토리에서 email의 회원 정보 가져오기
        MemberDTO memberDto = memberRepository.findByMb_Email(mb_email);
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

    // 세션 정보 지우기
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session= request.getSession();
        session.invalidate();
        return "redirect:/login";
    }


}




