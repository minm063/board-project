package com.example.board.home.impl;

public class BoardVO {

    /*
    crud board
     */
    int boardNo;
    String title;
    String content;
    String regdate;
    int hits;
    String boardName;

    /*
    user info
     */
    String id;
    String pw;
    String name;
    String birth;
    String gender;
    String email;

    public BoardVO() {}

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getRegdate() {
        return regdate;
    }

    public int getHits() {
        return hits;
    }
}
