package com.spring.webmvc.board;

import com.spring.webmvc.board.domain.Board;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    void builderTest(){
        Board board = new Board.Builder()
                .title("제목")
                .boardNo(20L)
                .content("하하호호")
                .build();
    }

}