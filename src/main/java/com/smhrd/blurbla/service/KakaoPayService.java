package com.smhrd.blurbla.service;

import com.smhrd.blurbla.model.KakaoPayDTO;
import com.smhrd.blurbla.model.PaymentDTO;
import com.smhrd.blurbla.repository.MemberRepository;
import com.smhrd.blurbla.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;


@Service
@RequiredArgsConstructor
@Transactional
@Log
public class KakaoPayService {
    private static final String Host = "https://kapi.kakao.com";
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;

    @Value("${pay.adminKey}")
    private String kakaoAdminKey;

    private KakaoPayDTO kakaoPayDTO;
    private PaymentDTO paymentDTO;

    public String kakaoPayReady(String mb_email) {
        System.out.println("    [kakaoPayReady] kakaoAdminKey : " + kakaoAdminKey);
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory()); // 정확한 에러 파악을 위해 생성

        // Server Request Header : 서버 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoAdminKey); // 어드민 키
        headers.add("Accept", "application/json");
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // Server Request Body : 서버 요청 본문
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("cid", "TC0ONETIME"); // 가맹점 코드 - 테스트용
        params.add("partner_order_id", "1001"); // 주문 번호
        params.add("partner_user_id", mb_email); // 회원 아이디
        params.add("item_name", "blurbla Premium"); // 상품 명
        params.add("quantity", "1"); // 상품 수량
        params.add("total_amount", "3300"); // 상품 가격
        params.add("tax_free_amount", "100"); // 상품 비과세 금액
        params.add("approval_url", "http://localhost:8083/api/kakaoPaySuccess"+"/"+mb_email); // 성공시 url
        params.add("cancel_url", "http://localhost:8083/kakaoPayCancle"); // 중단시 url
        params.add("fail_url", "http://localhost:8083/kakaoPayFail");   // 실패시 url

        // 헤더와 바디 붙이기
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayDTO = restTemplate.postForObject(new URI(Host + "/v1/payment/ready"), body, KakaoPayDTO.class);

            log.info(""+ kakaoPayDTO);
            return kakaoPayDTO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("    [kakaoPayReady] return : /pay");
        return "/pay";
    }

    public void paymentInsert(String mb_email) {
        //kakaoPaySuccess일 경우 결제내역 저장
        paymentRepository.save(new PaymentDTO(null, mb_email, "pre", "3300", "kakaopay" ,new Date()));
        // 회원 등급 u 변경
        memberRepository.memberRolePremium(mb_email);
    }
}