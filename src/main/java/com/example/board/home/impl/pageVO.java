package com.example.board.home.impl;

public class pageVO {
    private int pageSize;  // 한 페이지에 보여줄 게시글 수
    private int pageBlock; //페이징 블록 사이즈
    private int pageNo;  // 페이지 번호
    int startRowNo; //조회 시작 row 번호
    int endRowNo; //조회 마지막 now 번호
    private int firstPageNo; // 첫 번째 페이지 번호
    private int finalPageNo; // 마지막 페이지 번호
    private int prevPageNo; // 이전 페이지 번호
    private int nextPageNo; // 다음 페이지 번호
    private int startPageNo; // 시작 페이지 (페이징 네비 기준)
    private int endPageNo; // 끝 페이지 (페이징 네비 기준)
    private int totalCount; // 게시 글 전체 수
    String norm;
    String searchInput;

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageBlock() {
        return pageBlock;
    }

    public void setPageBlock(int pageBlock) {
        this.pageBlock = pageBlock;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStartRowNo() {
        return startRowNo;
    }

    public void setStartRowNo(int startRowNo) {
        this.startRowNo = startRowNo;
    }

    public int getEndRowNo() {
        return endRowNo;
    }

    public void setEndRowNo(int endRowNo) {
        this.endRowNo = endRowNo;
    }

    public int getFirstPageNo() {
        return firstPageNo;
    }

    public void setFirstPageNo(int firstPageNo) {
        this.firstPageNo = firstPageNo;
    }

    public int getFinalPageNo() {
        return finalPageNo;
    }

    public void setFinalPageNo(int finalPageNo) {
        this.finalPageNo = finalPageNo;
    }

    public int getPrevPageNo() {
        return prevPageNo;
    }

    public void setPrevPageNo(int prevPageNo) {
        this.prevPageNo = prevPageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public int getStartPageNo() {
        return startPageNo;
    }

    public void setStartPageNo(int startPageNo) {
        this.startPageNo = startPageNo;
    }

    public int getEndPageNo() {
        return endPageNo;
    }

    public void setEndPageNo(int endPageNo) {
        this.endPageNo = endPageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount, int pageNo) {
        this.totalCount = totalCount;
        this.pageNo = pageNo;
        this.paging();
    }

    private void paging() {
        if (this.totalCount == 0) return;
        if (this.pageNo == 0) this.setPageNo(1);
        if (this.pageSize == 0) this.setPageSize(10);
        if (this.pageBlock == 0 ) this.setPageBlock(10);

        // 첫 페이지, 마지막 페이지
        int finalPage = (totalCount + (pageSize - 1)) / pageSize; // 마지막 페이지
        this.setFirstPageNo(1);   // 첫 번째 페이지 번호
        this.setFinalPageNo(finalPage); // 마지막 페이지 번호

        // 이전, 다음 페이지
        boolean isNowFirst = pageNo == 1;    // 시작 페이지 (전체)
        boolean isNowFinal = pageNo == finalPage;  // 마지막 페이지 (전체)
        if (isNowFirst) {
            this.setPrevPageNo(1); // 이전 페이지 번호
        } else {
            this.setPrevPageNo((Math.max((pageNo - 1), 1))); // 이전 페이지 번호
        }
        if (isNowFinal) {
            this.setNextPageNo(finalPage); // 다음 페이지 번호
        } else {
            this.setNextPageNo((Math.min((pageNo + 1), finalPage))); // 다음 페이지 번호
        }

        // 페이징 블록
        int startPage = ((pageNo - 1) / pageBlock) * pageBlock + 1; // 시작 페이지 (페이징 네비 기준)
        int endPage = startPage + pageBlock - 1;      // 끝 페이지 (페이징 네비 기준)


        if (endPage > finalPage) { // 마지막 페이지 (페이징 네비 기준) > 마지막 페이지
            endPage = finalPage;
        }
        this.setStartPageNo(startPage); // 시작 페이지 (페이징 네비 기준)
        this.setEndPageNo(endPage);  // 끝 페이지 (페이징 네비 기준)

        // 조회 시작 row, 조회 마지막 row 계산
        int startRowNo = ((pageNo-1) * pageSize ) + 1;
        int endRowNo = pageNo * pageSize;
        setStartRowNo(startRowNo);
        setEndRowNo(endRowNo);
    }
    public void setPage(int pageNo) {
        // 조회 시작 row, 조회 마지막 row 계산
        int startRowNo = ((pageNo-1) * pageSize ) + 1;
        int endRowNo = pageNo * pageSize;
        setStartRowNo(startRowNo);
        setEndRowNo(endRowNo);
    }
}
