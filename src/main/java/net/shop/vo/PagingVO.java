package net.shop.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PagingVO {

    private int requestPage;
    private int totalPageCount;
    private int firstRow;
    private int endRow;
    private int beginPage;
    private int endPage;

    public PagingVO(int requestPage, int totalPageCount, int firstRow, int endRow, int beginPage, int endPage) {
        this.requestPage = requestPage;
        this.totalPageCount = totalPageCount;
        this.firstRow = firstRow;
        this.endRow = endRow;
        this.beginPage = beginPage;
        this.endPage = endPage;
    }

    public int getRequestPage() {
        return requestPage;
    }

    public void setRequestPage(int requestPage) {
        this.requestPage = requestPage;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}
