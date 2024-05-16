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

    public List<MemberDTO> adminMainMemberList() {
      return memberRepository.findAll();
    }
}
