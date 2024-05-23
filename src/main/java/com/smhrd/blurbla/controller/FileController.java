package com.smhrd.blurbla.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smhrd.blurbla.service.FlaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/FileApi")
public class FileController {

    private final FlaskService flaskService;

    // Flask로 데이터 전송
//    @PostMapping("/springToIamge")
//    public ResponseEntity<byte[]> sendToFlask (
//            @RequestParam("file") MultipartFile image ,
//            @RequestParam HashMap<String, Object> file_subData) {
//
//        ResponseEntity<byte[]> result = null;
//
//        System.out.println("▶ React -> Spring : image OK!");
//        System.out.println("imageFile : " + image);
//        System.out.println("file_type : " + file_subData.get("concent"));
//
//        // Flask에 데이터 전달 → result
//        result = flaskService.sendToFlask(image, file_subData);
//        System.out.println("springToIamge >> sendToFlask  ");
//        // result → React에 데이터 전달
//        return result;
//    }

    // [모자이크] Auto 적용시 받아오는 데이터
    @PostMapping("/uploadFileInfo")
    public Map<String, Object> uploadFileInfo(@RequestBody HashMap<String, Object> reactMap) throws JsonProcessingException {
        System.out.println("▶ React -> Spring : FileController >> uploadFileInfo !");

        int result = (reactMap.size()-11) / 6;

        // HashMap 생성
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> originalMap = new HashMap<>();
        Map<String, Object> areaListMap = new HashMap<>();

        // Auto 값 가져오기
        dataMap.put("face", (String) reactMap.get("face") );                   // 얼굴
        dataMap.put("carNumber", (String) reactMap.get("carNumber") );         // 차 번호
        dataMap.put("cigar" , (String) reactMap.get("cigar") );                // 담배
        dataMap.put("knife" , (String) reactMap.get("knife") );                // 흉기(칼)
        dataMap.put("intensityAuto" , (String) reactMap.get("intensityAuto") ); // 농도

        // 데이터 분류
        for (Map.Entry<?, ?> entry : reactMap.entrySet()) {
            String key = (String) entry.getKey();
            Object value = entry.getValue();

            if (key.startsWith("original_")) {
                originalMap.put(key, value);
            }
            for (int i = 0; i < result; i++){
                if (key.startsWith("area_"+i)) {
                    areaListMap.put(key, value);
                }
            }
        }

        // 데이터 활용
        System.out.println("result : " + result);

        System.out.println("Data:");
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nOriginal Data:");
        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nArea Data:");
        int num = 0;
        for (Map.Entry<String, Object> entry : areaListMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // resultMap에 관련 데이터 모두 담기
        // (모자이크 대상 구분, 제외 대상 선정, 원본 이미지)
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", dataMap);
        resultMap.put("original", originalMap);
        resultMap.put("area", areaListMap);

        // flask로 모자이크 요청(모자이크 대상 구분, 제외 대상 선정, 원본 이미지)
        ResponseEntity<byte[]> resultData = flaskService.sendToFlask(null, resultMap);

        String responseData = new String(resultData.getBody());
        ObjectMapper objectMapper = new ObjectMapper();     Map<String, Object> jsonMap = objectMapper.readValue(responseData, Map.class);
        Map<String, Object> responseMap = (Map<String, Object>) jsonMap;

        System.out.println("///////////////////////// 저장 데이터 ////////////////////////");
        System.out.println("file_name : " + responseMap.get("file_name"));
        System.out.println("file_rename : " + responseMap.get("file_rename"));
        System.out.println("file_size : " + responseMap.get("file_size"));
        System.out.println("file_type : " + responseMap.get("file_type"));

        flaskService.fileInsert(responseMap);

        return responseMap;
    }
}
