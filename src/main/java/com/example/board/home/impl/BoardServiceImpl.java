package com.example.board.home.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

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
    public List<BoardVO> listBoard(@Param("startRowNo") int startRowNo, @Param("endRowNo") int endRowNo) {
        return boardMapper.listBoard(startRowNo, endRowNo);
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
    public int listCount(BoardVO vo) {return boardMapper.listCount(vo);}


//    @Autowired
//    BoardDAO boardDAO;
//
//    @Override
//    public void createBoard(BoardVO vo) {
//        boardDAO.createBoard(vo);
//    }
//
//    @Override
//    public List<BoardVO> listBoard() {
//        return boardDAO.listBoard();
//    }
//
//    @Override
//    public void updateBoard(BoardVO vo) {
//        boardDAO.updateBoard(vo);
//    }
//
//    @Override
//    public void deleteBoard(BoardVO vo) {
//        boardDAO.deleteBoard(vo);
//    }
//
//    @Override
//    public BoardVO readBoard(BoardVO vo) {
//        return boardDAO.readBoard();
//    }
}
