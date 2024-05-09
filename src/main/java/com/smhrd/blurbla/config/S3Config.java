package com.smhrd.blurbla.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;   // 어드레스 발급 키 번호
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;   // 어드레스 발급 키 비밀번호
    @Value("${cloud.aws.region.static}")
    private String region; // 연결 지역번호(내 경우 : 미국 버지니아)

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCredentials= new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
