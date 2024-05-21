package com.smhrd.blurbla.model;

/*
 *   해당 DTO는 결제 내역에 대한 테이블
 * */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_payment")
public class PaymentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pay_idx;
    
    private String mb_email; // 참조키
    private String pay_item;
    private String pay_amount;
    private String pay_method;
    private Date payed_at;
}
