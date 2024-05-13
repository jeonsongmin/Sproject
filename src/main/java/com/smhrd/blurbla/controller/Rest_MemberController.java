package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.model.Board;
import com.smhrd.blurbla.model.File;
import com.smhrd.blurbla.model.Member;
import com.smhrd.blurbla.repository.BoardRepository;
import com.smhrd.blurbla.repository.FileRepository;
import com.smhrd.blurbla.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*") // 서버 XMLHttpRequest 해결용
@RequestMapping("/restApi")
public class Rest_MemberController {

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

        memberRepository.save(new Member(mb_email, mb_pw, "M", new Date()));
        String result="";
        System.out.println("join ▶ RedirectView insertJoin on!");

        result = "Success";
        return result;
    }

    // 회원 로그인하기
    @GetMapping("/Login")
    public Member selectLogin(@RequestParam String mb_email, String mb_pw){
        // 레포지토리에서 email의 회원 정보 가져오기
        Member memberDto = memberRepository.findByMb_Email(mb_email);
        Member react2Data = null; // 로그인 성공시 데이터 넘겨주고 실패하면 안넘겨줌

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




