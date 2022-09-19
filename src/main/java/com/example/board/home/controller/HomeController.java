package com.example.board.home.controller;

import com.example.board.home.impl.BoardServiceImpl;
import com.example.board.home.impl.BoardVO;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {
    private final BoardServiceImpl boardService;

    public HomeController(BoardServiceImpl boardService) {
        this.boardService = boardService;
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
        final String hashedPw = Hashing.sha256()
                .hashString(vo.getPw(), StandardCharsets.UTF_8)
                .toString();
        vo.setPw(hashedPw);
        String name = boardService.loginUser(vo);
        System.out.println("name" + name);
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
    @GetMapping(value = "/home")
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        List<BoardVO> boardList = boardService.listBoard();
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 로그인 되어있는 계정 name
            mv.addObject("user", session.getAttribute("id"));
        }
        mv.setViewName("home/index");
        mv.addObject("boardList", boardList);

        return mv;
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
    public String insertBoard(BoardVO vo) {
        boardService.createBoard(vo);
        return "redirect:/home";
    }

    // 상세 페이지
    @GetMapping("/home/readBoard")
    public ModelAndView readBoard(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int boardNo = Integer.parseInt(request.getParameter("boardNo")); // ok
        String user = "";
        List<BoardVO> boardRead = boardService.readBoard(boardNo);
        String boardName = request.getParameter("boardName");


        HttpSession session = request.getSession(false);
        if (session == null) {
            mv.setViewName("redirect:/home");
        } else {
            user = session.getAttribute("id").toString();
            mv.addObject("user", user);
        }
        if (!user.equals(boardName)) {
            boardService.readBoardHits(boardNo);
            System.out.println("user"+user+" boardName"+boardName);
        }
        mv.addObject("boardNo", boardNo); // boardNo 저장
        mv.addObject("boardRead", boardRead); // 글 내용
        mv.setViewName("/home/readBoard");
        return mv;
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
        return "redirect:/home";
    }

    @GetMapping("/home/deleteBoard")
    public String deleteBoard(@RequestParam("boardNo") int boardNo) {
        boardService.deleteBoard(boardNo);
        return "redirect:/home";
    }
}
