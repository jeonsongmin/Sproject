package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.service.FlaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;


@RestController
@RequiredArgsConstructor
@RequestMapping("/FilApi")
public class FileController {

    private final FlaskService flaskService;

    // Flask로 데이터 전송
    @PostMapping("/springToIamge")
    public ResponseEntity<byte[]> sendToFlask (
            @RequestParam("file") MultipartFile image ,
            @RequestParam HashMap<String, Object> file_subData) {

        ResponseEntity<byte[]> result = null;

        System.out.println("▶ React -> Spring : image OK!");
        System.out.println("imageFile : " + image);
        System.out.println("file_type : " + file_subData.get("concent"));

        // Flask에 데이터 전달 → result
        result = flaskService.sendToFlask(image, file_subData);
        System.out.println("springToIamge >> sendToFlask  ");
        // result → React에 데이터 전달
        return result;
    }
}
