package com.smhrd.blurbla.controller;

/*
        삭제는 하지 말 것!
       멘토링 숙제용 컨트롤러

@Controller
public class StartController {

    @Autowired
    private BoardRepository boardRepository;    // 테스트용 게시판 JPA
    @Autowired
    private FileRepository fileRepository;      // 파일 전용 JPA

    @RequestMapping("/")
    public String list(Model model){
        System.out.println("StartController !!");
        List<Board> list = boardRepository.findAll();   // tb_
        List<File> fileList = fileRepository.findAll();
//        List<Board> list = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "created_at"));
        model.addAttribute("list", list);
        model.addAttribute("fileList", fileList);
        return "index.html";
    }

}
*/