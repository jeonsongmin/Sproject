package com.smhrd.blurbla.service;

import com.smhrd.blurbla.model.MemberDTO;
import com.smhrd.blurbla.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 모든 회원 정보
    public List<MemberDTO> adminMainMemberList() {
      return memberRepository.findAll();
    }

    // 신규 결제 회원 보기
    public List<MemberDTO> adminPayMainMemberList() {
      return memberRepository.findMemberAllDate();
    }
}
