package com.smhrd.blurbla.controller;

/*

   삭제하지 마세요/!!!

    멘토링 숙제용으로 작성한 컨트롤러
    - 타임리트 형식으로 구현된 컨트롤러
    1. s3의 파일 업로드하는 기능이 있음 추후 중요하니 참고할 수 있게 삭제 x



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
    public RedirectView insert(@RequestParam int idx, String title, String context){
        boardRepository.save( new Board(idx, title, context, new Date(), 'N') );
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
*/



