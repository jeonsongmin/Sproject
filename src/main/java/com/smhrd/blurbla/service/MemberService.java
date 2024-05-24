package com.smhrd.blurbla.service;

import com.smhrd.blurbla.model.AllMemberDTO;
import com.smhrd.blurbla.model.FileDTO;
import com.smhrd.blurbla.model.MemberDTO;
import com.smhrd.blurbla.model.PaymentDTO;
import com.smhrd.blurbla.repository.AllMemberRepository;
import com.smhrd.blurbla.repository.FileRepository;
import com.smhrd.blurbla.repository.MemberRepository;
import com.smhrd.blurbla.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final AllMemberRepository allMemberRepository;
    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;
    private final FileRepository fileRepository;

    // 회원 가입
    public MemberDTO memberInsert(HashMap<String, Object> joinData) {

        String mb_email = (String) joinData.get("mb_email");
        String mb_pw = (String) joinData.get("mb_pw");

        System.out.println("============================");
        System.out.println("join ▶ mb_email    : "+ mb_email);
        System.out.println("join ▶ mb_pw       : "+ mb_pw);

      return memberRepository.save(new MemberDTO(mb_email, mb_pw, "M", new Date()));
    }

    // 회원 로그인
    public MemberDTO seleteLogin(String mb_email){
        // 해당 회원의 모든 정보 가져오기
        return memberRepository.findByMb_Email(mb_email);
    }


    // 모든 회원 정보
    public List<AllMemberDTO> adminMainMemberList() {
        return allMemberRepository.memberSelectAll();
    }

    // 신규 결제 회원 보기
    public List<MemberDTO> adminPayMainMemberList() {
      return memberRepository.findMemberAllDate();
    }

    // 마이페이지 > 결제 내역 리스트
    public List<PaymentDTO> paymentList(String mb_email) { return paymentRepository.selectMember(mb_email);
    }

    // 마이페이지 > 작업내역
    public List<FileDTO> myPageSelectAll(String mb_email) {return fileRepository.myPageSelect(mb_email);
    }
}
