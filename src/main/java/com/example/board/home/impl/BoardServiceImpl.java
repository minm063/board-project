package com.example.board.home.impl;

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
    public List<BoardVO> listBoard() {
        return boardMapper.listBoard();
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
