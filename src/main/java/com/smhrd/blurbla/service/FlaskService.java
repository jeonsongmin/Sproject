package com.smhrd.blurbla.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smhrd.blurbla.model.FileDTO;
import com.smhrd.blurbla.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FlaskService {
    
    /* 
    * 스프링과 플라스크 통신을 위한 클래스
    * sendToFlask() 메서드는 이미지형식과 텍스트형식 모두 처리가능
    * */

    //데이터를 JSON 객체로 변환하기 위해서 사용
    private final ObjectMapper objectMapper;
    private final FileRepository fileRepository;

    @Transactional
    public ResponseEntity<byte[]> sendToFlask(MultipartFile imageFile, HashMap<String, Object> fileData) throws JsonProcessingException {
        System.out.println("FlaskService  >>>  sendToFlask !!");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("data", fileData.get("data"));
        requestBody.put("original", fileData.get("original"));
        requestBody.put("area", fileData.get("area"));

        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

        String url = "http://127.0.0.1:5000/detect";
        byte[] res = restTemplate.postForObject(url, entity, byte[].class);

        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }


    // s3에 저장된 모자이크 이미지 DB에 저장
    public void fileInsert(Map<String, Object> responseMap) {
        System.out.println("FlaskService  >>>  fileInsert !!");
        String file_name = (String) responseMap.get("file_name");
        String file_rename = (String) responseMap.get("file_rename");
        Integer file_size = (Integer) responseMap.get("file_size");
        String file_type = (String) responseMap.get("file_type");

        fileRepository.save(new FileDTO(file_name, file_rename, file_type, file_size, new Date()));
    }
}