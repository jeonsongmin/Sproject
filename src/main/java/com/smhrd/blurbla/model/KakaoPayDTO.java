package com.smhrd.blurbla.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;
/*
 *   해당 DTO는 카카오 페이 결제에 대한 컬럼
 * */
@Getter
@Setter
@ToString
public class KakaoPayDTO {
    private String tid; // 결제 고유 번호
    private String next_redirect_pc_url; // web - 받는 결제 페이지
    private Date created_at;
}
