package com.smhrd.blurbla.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smhrd.blurbla.model.File;
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
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class FlaskService {

    //데이터를 JSON 객체로 변환하기 위해서 사용
    private final ObjectMapper objectMapper;

    @Transactional
    public ResponseEntity<byte[]> sendToFlask(MultipartFile imageFile) {
        RestTemplate restTemplate = new RestTemplate();

        //헤더를 JSON으로 설정함
        byte[] imageBytes = null;
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", imageFile.getResource());
        //body.add("concent", concentNum);

        //파라미터로 들어온 dto를 JSON 객체로 변환
        //headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        //실제 Flask 서버랑 연결하기 위한 URL
        String url = "http://127.0.0.1:5000/detect";

        System.out.println("sp2py File url : " + url);
        System.out.println("sp2py File entity : " + entity);
        System.out.println("sp2py File body file : " + body.get("file"));
        System.out.println("sp2py File body concent : " + body.get("concent"));

        byte[] res = restTemplate.postForObject(url, entity, byte[].class);
        headers.setContentType(MediaType.IMAGE_JPEG); // Set the MIME type based on your image format

        //Flask 서버로 데이터를 전송하고 받은 응답 값을 return
        //return restTemplate.postForObject(url, entity, String.class);
        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }
}