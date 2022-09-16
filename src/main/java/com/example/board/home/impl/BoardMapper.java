package com.example.board.home.impl;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="BoardMapper")
public interface BoardMapper {

    void createBoard(BoardVO vo);
    List<BoardVO> listBoard();
    void updateBoard(BoardVO vo);
    void deleteBoard(int boardNo);
    List<BoardVO> readBoard(int boardNo);
    void readBoardHits(int boardNo);

    String loginUser(BoardVO vo);
    void registerUser(BoardVO vo);
    int idCheck(String id);
}
