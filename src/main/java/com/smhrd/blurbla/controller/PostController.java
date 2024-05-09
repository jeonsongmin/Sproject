package com.smhrd.blurbla.controller;

import com.smhrd.blurbla.model.Board;
import com.smhrd.blurbla.model.File;
import com.smhrd.blurbla.repository.BoardRepository;
import com.smhrd.blurbla.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@RestController
@RequestMapping("/restApi")
public class PostController {

    @Autowired
    private BoardRepository boardRepository;    // 작성글 전용 JPA

    @Autowired
    private FileRepository fileRepository;      // 파일 전용 JPA

    // 파일업로드 객체선언
    @Autowired
    private FileController fileUploadController;

    // 게시글 작성하기
    @GetMapping("/insert")
    public RedirectView insert(@RequestParam String title, String context){
        boardRepository.save(new Board(title, context, new Date()));
        System.out.println("insert => RedirectView on!");
        return new RedirectView("/flaskRestApi");
    }

    // s3의 파일 업로드하기
    @PostMapping("/fileUpload")
    public RedirectView fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        System.out.println("🎃 fileUploadController method on!");

        // s3에 업로드
        fileUploadController.uploadFileToS3(multipartFile);

        // 파일정보 가져오기
        String file_name = multipartFile.getOriginalFilename();
        String file_rename = multipartFile.getOriginalFilename();
        String file_type = multipartFile.getContentType();
        Integer file_size = Math.toIntExact(multipartFile.getSize());
        System.out.println("--> multipartFile : " + multipartFile);
        System.out.println("--> file_name : " + file_name);
        System.out.println("--> file_rename : " + file_rename);
        System.out.println("--> file_type : " + file_type);
        System.out.println("--> file_size : " + file_size);

        // RDS에 파일정보 insert
        fileRepository.save(new File(file_name, file_rename, file_type, file_size, new Date()));
        System.out.println("s3 upload Success !");
        System.out.println("rds insert Success !");

        return new RedirectView("/");
    }

}




