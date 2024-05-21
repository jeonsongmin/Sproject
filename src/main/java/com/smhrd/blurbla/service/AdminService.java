package com.smhrd.blurbla.service;

import com.smhrd.blurbla.model.AnswerDTO;
import com.smhrd.blurbla.model.QstnsDTO;
import com.smhrd.blurbla.repository.AnswerRepository;
import com.smhrd.blurbla.repository.MemberRepository;
import com.smhrd.blurbla.repository.QstnsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final QstnsRepository qstnsRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    // [문의사항] 모든 리스트
    public List<QstnsDTO> qsntsList(){
        System.out.println("AdminService  >>>  qsntsList");
        return qstnsRepository.findAll();
    }

    ////////////////////////////////////////////////////////////////

    // [문의사항] 오늘 생성된 문의 카운트
    public int createQsntsCount(){
        System.out.println("AdminService  >>>  createQsntsCount");
        return qstnsRepository.createQsntsCount();
    }

    // [문의사항] 답변 대기 카운터
    public int standQsntsCount(){
        System.out.println("AdminService  >>>  standQsntsCount");
        return qstnsRepository.standQsntsCount();
    }

    // [문의사항] 답변 완료 카운터
    public int answerQsntsCount(){
        System.out.println("AdminService  >>>  answerQsntsCount");
        return qstnsRepository.answerQsntsCount();
    }

    ////////////////////////////////////////////////////////////////

    // [문의사항] 답변 select
    public List<AnswerDTO> qsntsToAnswer(String qstn_idx) {
        System.out.println("AdminService  >>>  qsntsToAnswer");
        return answerRepository.selectQstns_Idx(qstn_idx);
    }

    // [문의사항] 답변 insert
    public AnswerDTO answerToQsnts(HashMap<String, Object> answerData) {
        System.out.println("AdminService  >>>  answerToQsnts");

        String qstn_idx = (String) answerData.get("qstn_idx");  // 문의사항 고유번호
        String ans_content = (String) answerData.get("ans_content");    // 문의 답변내용
        String admin_id = (String) answerData.get("admin_id");  // 답변 관리자 아이디

        return answerRepository.save(new AnswerDTO(null, qstn_idx, ans_content, null, new Date(), admin_id));
    }

    // [문의사항]
    // 답변 적용 성공시 해당 문의사항 상태를 답변완료로 변경
    public void qsntnsUpdate(HashMap<String, Object> answerData) {
        System.out.println("AdminService  >>>  qsntnsUpdate");
        String qstn_idx = (String) answerData.get("qstn_idx");  // 문의사항 고유번호
        qstnsRepository.qsntnsUpdate(qstn_idx);
    }


    // 회원 등급 임의변경
    public void memberRoleUpdate(HashMap<String, Object> memberData){
        System.out.println("AdminService  >>>  memberRoleUpdate");

        String mb_role = (String) memberData.get("mb_role");
        String mb_email = (String) memberData.get("mb_email");
        System.out.println("mb_role  : " + mb_role);
        System.out.println("mb_email : " + mb_email);

        memberRepository.memberRoleUpdate(mb_role, mb_email);
    }





}
