package com.example.board.home.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("BoardService")
public class BoardServiceImpl implements BoardMapper{

    @Resource(name = "BoardMapper")
    private BoardMapper boardMapper;


    @Override
    public String loginUser(BoardVO vo) {
        return boardMapper.loginUser(vo);
    }
    @Override
    public void registerUser(BoardVO vo){
        boardMapper.registerUser(vo);
    }

    @Override
    public int idCheck(String id) {
        return boardMapper.idCheck(id);
    }

    @Override
    public void createBoard(BoardVO vo) {
        boardMapper.createBoard(vo);
    }

    @Override
    public List<BoardVO> listBoard(@Param("startRowNo") int startRowNo, @Param("endRowNo") int endRowNo, @Param("norm") String norm, @Param("searchInput") String searchInput) {
        return boardMapper.listBoard(startRowNo, endRowNo, norm, searchInput);
    }

    @Override
    public List<BoardVO> readBoard(int boardNo) {
        return boardMapper.readBoard(boardNo);
    }

    @Override
    public void readBoardHits(int boardNo) {
        boardMapper.readBoardHits(boardNo);
    }

    @Override
    public void updateBoard(BoardVO vo) {
        boardMapper.updateBoard(vo);
    }

    @Override
    public void deleteBoard(int boardNo) {
        boardMapper.deleteBoard(boardNo);
    }

    @Override
    public int listCount(BoardVO vo, @Param("norm") String norm, @Param("searchInput") String searchInput) {return boardMapper.listCount(vo, norm, searchInput);}

    @Override
    public String getFileName(int boardNo) {
        return boardMapper.getFileName(boardNo);
    }
}
