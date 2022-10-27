package com.spring.webmvc.board.domain;

import lombok.*;

import java.util.Date;

//DB 엔터티(테이블)과 1:1 매칭되는 Value Object
@Setter @Getter @ToString//toString
@NoArgsConstructor  //기본 생성자
@AllArgsConstructor //모든 파라미티 생성자
//@Builder//빌더 패턴
public class Board {
    private Long boardNo;
    private String writer;
    private String title;
    private String content;
    private int viewCnt;
    private Date regDate;

    private Board(Builder builder){

        this.boardNo= builder.boardNo;
        this.writer= builder.writer;
        this.title= builder.title;
        this.content= builder.content;
        this.viewCnt= builder.viewCnt;
        this.regDate= builder.regDate;
    }

    public static class Builder{
        private Long boardNo;
        private String writer;
        private String title;
        private String content;
        private int viewCnt;
        private Date regDate;
        public Builder boardNo(Long boardNo){
            this.boardNo=boardNo;
            return this;
        }
        public Builder writer(String writer){
            this.writer=writer;
            return this;
        }

        public Builder title(String title){
            this.title=title;
            return this;
        }
        public Builder content(String content){
            this.content=content;
            return this;
        }
        public Builder viewCnt(int viewCnt){
            this.viewCnt=viewCnt;
            return this;
        }
        public Builder regDate(Date regDate){
            this.regDate = regDate;
            return this;
        }
        public Board build(){
            return new Board(this);
        }

    }
}
