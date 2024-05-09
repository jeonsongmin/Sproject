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
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*") // 서버 XMLHttpRequest 해결용
@RequestMapping("/restApi")
public class Rest_MemberController {

    @Autowired
    private MemberRepository memberRepository;    // 작성글 전용 JPA

    // 회원가입하기
    @GetMapping("/join")
    public String insertJoin(@RequestParam String email, String pw){
        memberRepository.save(new Member(email, pw, "M", new Date()));
        String result="";
        System.out.println("join ▶ RedirectView insertJoin on!");

        result = "Success";
        return result;
    }

    // 회원 로그인하기
    @GetMapping("/Login")
    public String selectLogin(@RequestParam String email, String pw, Model model){
        // 레포지토리에서 email의 회원 정보 가져오기
        Optional<Member> userData = memberRepository.findByEmail(email);

        String result=""; // 성공,실패 여부로 res(리액트) 데이터 판별기준

            // 회원 정보를 기반으로 비교 (널과 비번)
            if (pw.equals(userData.get().getPw())) {
                System.out.println("============================");
                System.out.println("DB data pw : " + userData.get().getPw());
                System.out.println("Inpurt  pw : " + pw);
                System.out.println("Login Success");
                result = "Success";
            } else {
                System.out.println("DB data pw : " + userData.get().getPw());
                System.out.println("Inpurt  pw : " + pw);
                System.out.println("Login Fail");
                result = "Fail";
            }
        return model.addAttribute("userData", userData).toString();
    }

    // 세션 정보 지우기
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session= request.getSession();
        session.invalidate();
        return "redirect:/login";
    }


}




