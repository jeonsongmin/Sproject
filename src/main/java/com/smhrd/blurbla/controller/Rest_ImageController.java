package com.smhrd.blurbla.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smhrd.blurbla.model.File;
import com.smhrd.blurbla.service.FlaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;


@RestController
@RequiredArgsConstructor
@RequestMapping("/restApi")
public class Rest_ImageController {

    private final FlaskService flaskService;

    // Flask로 데이터 전송
    @PostMapping("/springToIamge")
    public ResponseEntity<byte[]> sendToFlask (
            @RequestParam HashMap<String, Object> file_subData,
            @RequestParam("file") MultipartFile image) {

        ResponseEntity<byte[]> result = null;

        System.out.println("▶ React -> Spring : image OK!");
        System.out.println("imageFile : " + image);
        System.out.println("file_type : " + file_subData.get("file_concent"));

        // Flask에 데이터 전달 → result
        result = flaskService.sendToFlask(image, file_subData);
        // result → React에 데이터 전달
        return result;
    }
}
