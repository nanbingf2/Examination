package com.rogercw.util;

/**
 * Created by 1 on 2018/5/28.
 */
public class Page {

    private int currentPage=1;   //当前页,默认为1
    private int totalPage;       //总页数
    private int pageSize=5;        //每页显示的数量
    private int upPageNo;        //上一页
    private int nextPageNo;      //下一页
    private int toPageNo=0;      //每页显示的第一条数据的编号,默认为0

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage != 1) {
            this.upPageNo = currentPage - 1;
        }
        this.nextPageNo = currentPage + 1;

        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalNum) {
        this.totalPage = (totalNum+pageSize-1)/pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getUpPageNo() {
        return upPageNo;
    }

    public void setUpPageNo(int upPageNo) {
        this.upPageNo = upPageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo=nextPageNo;
    }

    public int getToPageNo() {
        return toPageNo;
    }

    public void setToPageNo(int toPageNo) {
        //新一页
        this.toPageNo = (toPageNo-1) * pageSize ;
        //设置跳转后当前的页码
        this.setCurrentPage(toPageNo);
    }
}
