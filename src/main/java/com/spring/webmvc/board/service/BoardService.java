package com.spring.webmvc.board.service;

import com.spring.webmvc.board.domain.Board;
import com.spring.webmvc.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 역할: 컨트롤러와 레파지토리 사이 중간에서 잡다한 처리를 담당
@RequiredArgsConstructor // final필드 초기화 생성자를 만듦
@Service
public class BoardService {

    private final BoardRepository repository;

    // 전체 조회 중간처리
    public List<Board> getList() {
        List<Board> boardList = repository.findAll();
        processBoardList(boardList);
        return boardList;
    }

    private void processBoardList(List<Board> boardList) {
        for (Board board : boardList) {
            subStringTitle(board);
            convertDateFormat(board);
            isNewArticle(board);
        }
    }

    //신규 게시물 new 마크 처리(3시간 이내 작성된 게시물)
    private void isNewArticle(Board board) {
        long regDate = board.getRegDate().getTime();//게시물 작성시간(밀리초)
        long nowDate = System.currentTimeMillis();//현재시간(밀리초)
        long diff = nowDate-regDate; //작성후 지난 시간(밀리초)
        long limit= 3*60*60*1000; //3시간을 밀리초로 변환
        if(diff<=limit){
            board.setNewArticle(true);
        }
    }

    //날짜 포맷팅 처리
    private void convertDateFormat(Board board) {
        Date regDate = board.getRegDate();
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("yy-MM-dd a hh:mm");
        board.setPrettierDate(simpleDateFormat.format(regDate));
    }


    //게시물 제목 줄임 처리
    private void subStringTitle(Board board) {
        //만약에 글제목이 6글자 이상이면 6글자 까지만 보여주고 뒤에 ...처리
        String title = board.getTitle();
        if(title.length()>6){
            String shortTitle = title.substring(0,6)+"...";
            board.setShortTitle(shortTitle);
        }else{
            board.setShortTitle(title);
        }
    }

    // 상세 조회 중간처리
    public Board getDetail(Long boardNo) {
        Board board = repository.findOne(boardNo);
        return board;
    }
    // 게시물 저장 중간처리
    public boolean insert(Board board) {
        boolean flag = repository.save(board);
        return flag;
    }

    // 게시물 수정 중간처리
    public boolean update(Board board) {
        boolean flag = repository.modify(board);
        return flag;
    }

    // 게시물 삭제 중간처리
    public boolean delete(Long boardNo) {
        boolean flag = repository.remove(boardNo);
        return flag;
    }

}