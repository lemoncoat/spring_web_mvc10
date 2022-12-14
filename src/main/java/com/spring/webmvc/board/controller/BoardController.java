package com.spring.webmvc.board.controller;

import com.spring.webmvc.board.domain.Board;
import com.spring.webmvc.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") //공통 url 진입점 설정
@Log4j2
public class BoardController {

    private final BoardService boardService;

    //게시물 목록 조회 요청 처리
    @GetMapping("/list")
    public String getList(Model model) {
        int a = 10;
        List<Board> boardList = boardService.getList();
        /*
            trace - 잡다한 자잘한 로그
            debug - 개발단계의 디버깅
            info - 정보
            warn - 경고
            error - 심각한 에러

         */
        log.info("/board/list GET! 요청 발생! - {}", a);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    //게시물 상세 조회 요청 처리
    @GetMapping("/content/{bno}")
    public String content(@PathVariable("bno") Long boardNo,Model model) {
        log.info("/board/content/{} GET!!", boardNo);
        model.addAttribute("b", boardService.getDetail(boardNo));
        return "board/detail";
    }

    //게시물 쓰기 화면 요청
    @GetMapping("/write")
    public String write(){
        log.info("/board/write GET!");
        return"board/write";
    }


    //게시물 등록 요청
    @PostMapping("/write")
    public String write(Board board, RedirectAttributes ra){
        log.info("/board/write Post! - {}",board);
        boolean flag = boardService.insert(board);
        ra.addFlashAttribute("msg","insert-success");
        return flag ? "redirect:/board/list":"redirect:/";
    }

}
