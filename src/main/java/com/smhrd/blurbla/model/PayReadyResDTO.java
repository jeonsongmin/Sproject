package com.smhrd.blurbla.model;

import lombok.Getter;

@Getter
public class PayReadyResDTO {
    private String tid; // 결제 고유 번호
    private String next_redirect_pc_url; // pc 웹일 경우 받는 결제 페이지
    private String created_at;

}
