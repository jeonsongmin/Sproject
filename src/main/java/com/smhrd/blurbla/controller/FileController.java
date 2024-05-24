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

    // [모자이크] Auto 적용하기 눌렀을때 React에서 받아오는 데이터
    // 작업 흐름 : React > S3(저장) > Spring > Flask > S3(불러오기) > AI > S3(저장) > Spring > React(S3 데이터 불러오기)
    @PostMapping("/uploadFileInfo")
    public Map<String, Object> uploadFileInfo(@RequestBody HashMap<String, Object> reactMap) throws JsonProcessingException {
        System.out.println("▶ React -> Spring : FileController >> uploadFileInfo !");

        // area_ List의 길이 계산
        // (11 = Auto 값 + original_)
        // (6 = area_ ... 의 값들)
        // int result = (reactMap.size()-11) / 5;

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

            // original_ ... 로 시작하는 데이터 담기
            if (key.startsWith("original_")) {
                originalMap.put(key, value);
            }

            // area_ ... 로 시작하는 List 데이터 담기
            if (key.startsWith("area_")) {
                areaListMap.put(key, value);
            }
        }

        String mb_email = (String) originalMap.get("original_mb_email");

        // 데이터 활용
//        System.out.println("result : " + result);

        System.out.println("Data:"); // 전송되는 데이터 콘솔 확인용
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nOriginal Data:"); // 전송되는 데이터 콘솔 확인용
        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nArea Data:"); // 전송되는 데이터 콘솔 확인용
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
        responseMap.put("mb_email", mb_email);  // 회원 id 넣어주기!

        System.out.println("///////////////////////// 저장 데이터 ////////////////////////");
        System.out.println("file_name : " + responseMap.get("file_name"));
        System.out.println("file_rename : " + responseMap.get("file_rename"));
        System.out.println("file_size : " + responseMap.get("file_size"));
        System.out.println("file_type : " + responseMap.get("file_type"));
        System.out.println("mb_email : " + responseMap.get("mb_email"));

        flaskService.fileInsert(responseMap);

        return responseMap;
    }

    // 모자이크한 작업 파일을 사진/영상 업로드
    @PostMapping("/mosaicUploadFileInfo")
    public HashMap<String, Object> mosaicUploadFileInfo(@RequestBody HashMap<String, Object> reactMap)  {
        System.out.println("▶ React -> Spring : FileController >> mosaicUploadFileInfo !");
        // 해당 파일정보 DB에 저장
        flaskService.fileInsert(reactMap);
        return reactMap;
    }
}
