package com.example.board.home.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="BoardMapper")
public interface BoardMapper {
    void createBoard(BoardVO vo);
    List<BoardVO> listBoard(@Param("startRowNo") int startRowNo, @Param("endRowNo") int endRowNo);
    void updateBoard(BoardVO vo);
    void deleteBoard(int boardNo);
    List<BoardVO> readBoard(int boardNo);
    void readBoardHits(int boardNo);

    String loginUser(BoardVO vo);
    void registerUser(BoardVO vo);
    int idCheck(String id);

    int listCount(BoardVO vo);
}
