package com.smhrd.blurbla.service;

import com.smhrd.blurbla.model.MemberDTO;
import com.smhrd.blurbla.model.QstnsDTO;
import com.smhrd.blurbla.repository.QstnsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QstnsService {

    @Autowired
    private QstnsRepository qstnsRepository;    // 작성글 전용 JPA

    // [문의사항] 모든 문의 SELECT
    public List<QstnsDTO> qstnsList() {
        return qstnsRepository.qstnsSelectList();
    }

    // [문의사항] 문의사항 작성
    public String insert(String qstn_title, String qstn_content, String qstn_open, String qstn_category, String mb_email) {
        qstnsRepository.save(new QstnsDTO(null, qstn_title, qstn_content, new Date(), qstn_open, qstn_category, "N", mb_email));
        return "Success";
    }

}
