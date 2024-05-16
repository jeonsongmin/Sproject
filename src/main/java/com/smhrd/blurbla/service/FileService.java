package com.smhrd.blurbla.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
*   파일 업로드 하는 Service
*
* */


@Service
@RequiredArgsConstructor
//public class FileController {
public class FileService {

    private final AmazonS3 amazonS3;
    private final AmazonS3 s3Client; // S3 이미지 정보를 가져오기 위한 필드 변수

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFileToS3(MultipartFile multipartFile)  {
        System.out.println("on! uploadFileToS3 multipartFile : " + multipartFile);
        String originalFilename = multipartFile.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();

        try {
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        } catch (IOException e) {
            System.out.println("uploadFileToS3 Err : " + e);
        }
        System.out.println("uploadFileToS3 upload : " + originalFilename);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }
}