package com.example.board.home.controller;

import com.example.board.home.impl.BoardServiceImpl;
import com.example.board.home.impl.BoardVO;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {
    @Resource(name = "uploadPath")
    String uploadPath;
    private final BoardServiceImpl boardService;

    public HomeController(BoardServiceImpl boardService) {
        this.boardService = boardService;
    }

    private Path getPath(HttpServletRequest request, String name) {
        Path serverPath = Paths.get(request.getSession().getServletContext().getRealPath(File.separator) + File.separator
                + "crud" + File.separator + name);
        return serverPath;
    }


    /*
    회원가입, 로그인
    0907~0916
     */
    @RequestMapping("/")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session == null) {
            mv.setViewName("home/login");
        } else {
            mv.setViewName("redirect:/home");
        }
        return mv;
    }

    @PostMapping("/applyLogin")
    public ModelAndView applyLogin(HttpServletRequest request,
                                   BoardVO vo) {
        ModelAndView mv = new ModelAndView();

        // hashedPw
        final String hashedPw = Hashing.sha256()
                .hashString(vo.getPw(), StandardCharsets.UTF_8)
                .toString();
        vo.setPw(hashedPw);

        // signin
        String name = boardService.loginUser(vo);
        if (name == null) {
            mv.setViewName("redirect:/");
        } else {
            // 로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("id", name);
            session.setAttribute("isLogin", true);
            mv.setViewName("redirect:/home");
        }

        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        session.invalidate();
        mv.setViewName("redirect:/");
        return mv;
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/register");
        return mv;
    }

    @PostMapping("/applyRegister")
    public ModelAndView applyRegister(BoardVO vo) {
        ModelAndView mv = new ModelAndView();
        final String hashedPw = Hashing.sha256()
                .hashString(vo.getPw(), StandardCharsets.UTF_8)
                .toString();
        vo.setPw(hashedPw);
        boardService.registerUser(vo);
        mv.setViewName("redirect:/");
        return mv;
    }

    @PostMapping("/idCheck")
    @ResponseBody // json
    public int idCheck(@RequestParam("id") String id) {
        return boardService.idCheck(id);
    }

    /*
    CRUD 게시판
    0901~0907
     */
    @RequestMapping(value = "/home")
    public ModelAndView home(HttpServletRequest request, BoardVO vo) {
        ModelAndView mv = new ModelAndView();
//            select 문에서 WHERE regDate>=date_add(now(), interval -1 day))인 boardNo
//         뿌릴 때 if 문
//         인자 넘겨서 ${not empty regDateMessage} =>
        // signin
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 로그인 되어있는 계정 name
            mv.addObject("user", session.getAttribute("id"));
        }
        // page
        int pageNo;
        try {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
            System.out.println("try:"+pageNo);
        } catch (Exception e) {
            pageNo = 1;
        }
        int totalCount = boardService.listCount(vo);
        vo.setTotalCount(totalCount, pageNo);
        mv.addObject("page", vo);

        List<BoardVO> boardList = boardService.listBoard(vo.getStartRowNo(), vo.getEndRowNo());
        mv.addObject("boardList", boardList);

        mv.setViewName("home/index");
        return mv;
    }

    @PostMapping("/home/pageSubmit")
    public List<BoardVO> pageSubmit(@RequestParam("data") int page, BoardVO vo) {
        vo.setPage(page);
        return boardService.listBoard(vo.getStartRowNo(), vo.getEndRowNo());
    }

    // 작성 페이지
    @RequestMapping("/home/writeBoard")
    public String createBoard(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/";
        } else {
            String user = session.getAttribute("id").toString();
            model.addAttribute("user", user); // 글 작성자 이름
            return "home/writeBoard";
        }
    }

    // 작성 확인
    @PostMapping("/home/applyInsert")
    public ModelAndView insertBoard(BoardVO vo, @RequestParam("uploadFile") MultipartFile file, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String name = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path serverPath = getPath(request, name);
        System.out.println(serverPath);

        if (!Files.exists(serverPath)) {
            try {
                Files.createDirectories(serverPath.getParent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.copy(file.getInputStream(), serverPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // boostCourse
//        try (
//            FileOutputStream stream = new FileOutputStream(request.getSession().getServletContext().getRealPath(File.separator) + File.separator + "crud" + File.separator + file.getOriginalFilename());
//            InputStream inputStream = file.getInputStream();
//        ) {
//            int readCount = 0;
//            byte[] buffer = new byte[1024];
//            while((readCount = inputStream.read(buffer)) != -1) {
//                stream.write(buffer, 0, readCount);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        vo.setFileName(name);
        boardService.createBoard(vo);
        mv.setViewName("redirect:/home");

        return mv;
    }

    // 상세 페이지
    @GetMapping("/home/readBoard")
    public ModelAndView readBoard(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int boardNo = Integer.parseInt(request.getParameter("boardNo")); // ok
        String boardName = request.getParameter("boardName");
        List<BoardVO> boardRead = boardService.readBoard(boardNo);
        String user = "";

        HttpSession session = request.getSession(false);
        if (session == null) {
            mv.setViewName("redirect:/home");
        } else {
            user = session.getAttribute("id").toString();
            mv.addObject("user", user);
        }
        if (!user.equals(boardName)) {
            boardService.readBoardHits(boardNo);
        }
        mv.addObject("boardNo", boardNo); // boardNo 저장
        mv.addObject("boardRead", boardRead); // 글 내용
        mv.setViewName("/home/readBoard");

        return mv;
    }

    @RequestMapping("/home/fileDownload")
    public void fileDownload(HttpServletResponse response, HttpServletRequest request) {

//        try {
//            String originName = URLDecoder.decode(fileName, "UTF-8");
//            FileSystemResource file = new FileSystemResource(request.getSession().getServletContext().getRealPath(File.separator) + File.separator
//                    + "crud" + File.separator + originName);
//            System.out.println("1: " + originName);
//            if (!file.exists()) {
//                // NOT_FOUND
//            }
//            System.out.println("2 : "+originName.substring(originName.lastIndexOf("_"))+1);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        String fileName = request.getParameter("fileName");
        System.out.println(fileName);
        String path = getPath(request, fileName).toString();
        File file = new File(path);
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

        FileInputStream fileInputStream = null; // 파일 읽어오기
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        OutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int read = 0;
        byte[] buffer = new byte[1024];
        while (true) {
            try {
                if (fileInputStream != null && (read = fileInputStream.read(buffer)) == -1) break;
            } catch (IOException ex) {
                ex.printStackTrace();
            } // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
            try {
                if (out != null) {
                    out.write(buffer, 0, read);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    @GetMapping("/home/updateBoard")
    public ModelAndView updateBoard(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        List<BoardVO> boardList = boardService.readBoard(boardNo);
        boardService.readBoardHits(boardNo);

        HttpSession session = request.getSession(false);
        System.out.println(session);
        if (session == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("boardList", boardList);
            mv.addObject("boardNo", boardNo);
            mv.setViewName("/home/updateBoard");
        }
        return mv;
    }

    //    @RequestMapping(value="/applyUpdate/{boardNo}", method=RequestMethod.PUT)
//    public String applyUpdate(BoardVO vo, @PathVariable(name="boardNo") int boardNo, Model model) {
//        boardService.updateBoard(vo, boardNo);
//        model.addAttribute(boardNo);
//
//        return "redirect:/home";
//    }
    @PostMapping("/applyUpdate")
    public String applyUpdate(@RequestParam("boardNo") int boardNo, BoardVO vo) {
        vo.setBoardNo(boardNo);
        boardService.updateBoard(vo);

        // file
        MultipartFile multipartFile = vo.getUploadFile();
        if (!multipartFile.isEmpty()) {
            String fileName = multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/home";
    }

    @GetMapping("/home/deleteBoard")
    public String deleteBoard(@RequestParam("boardNo") int boardNo) {
        boardService.deleteBoard(boardNo);
        return "redirect:/home";
    }
}
