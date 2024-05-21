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

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log
public class KakaoPayController {

    @Setter(onMethod_ = @Autowired)
    private KakaoPayService kakaoPayService;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestBody HashMap<?, ?> resultMap){
        log.info("kakaoPay post.....................");

        String mb_email = (String) resultMap.get("mb_email");
        System.out.println("*** KakaoPayController >>> kakaoPay on! : " + mb_email);

        return kakaoPayService.kakaoPayReady(mb_email);
    }

    @GetMapping("/kakaoPaySuccess/{mb_email}")
    public RedirectView kakaoPaySuccess(@RequestParam("pg_token") String pg_token,
                                        @PathVariable String mb_email) {
        System.out.println("*** KakaoPayController >>> kakaoPaySuccess on!");
        // 추후 DB에 저장되는 쿼리 제작할 것.
        /*
        *   결제 내역 DB 쿼리 추가
        *   회원등급 변경 쿼리 추가
        *
        * */
        log.info("kakaoPay Success get................" );
        log.info("kakaoPay Success pg_token : " + pg_token);
        log.info("kakaoPay Success mb_email : " + mb_email);

        kakaoPayService.paymentInsert(mb_email);

        // 추후 로그인 상태 유지 > 마이페이지 > 결제 내역으로 이동
        return new RedirectView("http://localhost:3000/MypagePay/");
    }

    // 결제 중단시
    @GetMapping("/kakaoPayCancle")
    public RedirectView kakaoPayCancle() {
        System.out.println("*** KakaoPayController >>> kakaoPayCancle on!");
        return new RedirectView("http://localhost:3000/Premium/");
    }

    // 결제 실패시
    @GetMapping("/kakaoPayFail")
    public RedirectView kakaoPayFail() {
        System.out.println("*** KakaoPayController >>> kakaoPayFail on!");
        return new RedirectView("http://localhost:3000/Premium/");
    }
}