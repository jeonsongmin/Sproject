package com.smhrd.blurbla.service;

import com.smhrd.blurbla.model.QstnsDTO;
import com.smhrd.blurbla.repository.QstnsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private QstnsRepository qstnsRepository;    // 작성글 전용 JPA

    // [문의사항] 모든 리스트
    public List<QstnsDTO> qsntsList(){
        return  qstnsRepository.findAll();
    }

    // [문의사항] 오늘 생성된 문의 카운트
    public int createQsntsCount(){
        return qstnsRepository.createQsntsCount();
    }

    // [문의사항] 답변 대기 카운터\
    public int standQsntsCount(){
        return qstnsRepository.standQsntsCount();
    }

    // [문의사항] 답변 완료 카운터
    public int answerQsntsCount(){
        return qstnsRepository.answerQsntsCount();
    }
}
