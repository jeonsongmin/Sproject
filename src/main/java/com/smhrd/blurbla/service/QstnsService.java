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

    public List<QstnsDTO> findAll() {
        return qstnsRepository.findAll();
    }

    public String insert(String qstnTitle, String qstnContent, String qstnOpen, String qstnCategory, String mbEmail) {
        qstnsRepository.save(new QstnsDTO(null, qstnTitle, qstnContent, new Date(), qstnOpen, qstnCategory, "N", mbEmail));
        return "Success";
    }

}
