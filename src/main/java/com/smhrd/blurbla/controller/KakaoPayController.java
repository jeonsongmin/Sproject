package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log
public class KakaoPayController {

    @Setter(onMethod_ = @Autowired)
    private KakaoPayService kakaoPay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(){
        log.info("kakaoPay post.....................");
        System.out.println("*** KakaoPayController >>> kakaoPay on!");

        return kakaoPay.kakaoPayReady();
    }

    @GetMapping("/kakaoPaySuccess")
    public RedirectView kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model) {
        System.out.println("*** KakaoPayController >>> kakaoPaySuccess on!");
        // 추후 DB에 저장되는 쿼리 제작할 것.
        /*
        *   결제 내역 DB 쿼리 추가
        *   회원등급 변경 쿼리 추가
        *
        * */
        log.info("kakaoPay Success get................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        // 추후 로그인 상태 유지 > 마이페이지 > 결제 내역으로 이동
        return new RedirectView("http://localhost:3000/");
    }
}