package com.hl.utils;

import com.hl.pojo.Books;

import java.util.List;


public class PageUtils {
    private int index;//首页
    private int end;//尾页
    private int crrentPage;//当前页
    private int startIndex;//开始索引
    private int pageSize;//每页显示多少条
    private int totalNumber;//总条数
    private int totalPage;//多少页

    public PageUtils(int crrentPage, int pageSize, int totalNumber) {
        this.index=1;
        this.pageSize = pageSize;
        this.totalNumber = totalNumber;
        if(totalNumber%pageSize==0){
            this.totalPage=totalNumber/pageSize;
        }else {
            this.totalPage=totalNumber/pageSize+1;
        }
        this.end=totalPage;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCrrentPage() {
        return crrentPage;
    }

    public void setCrrentPage(int crrentPage) {
        if(crrentPage<=0){
            this.crrentPage = 1;
        }else if(crrentPage>=totalPage){
            this.crrentPage=totalPage;
        }else{
            this.crrentPage = crrentPage;
        }
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int CrrentPage) {
        if(CrrentPage <= 0){
            this.startIndex=1;
        }else if(CrrentPage>=totalPage){
            this.startIndex=(totalPage-1)*pageSize;
        }else {
            this.startIndex = (1-CrrentPage)*pageSize;
        }

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    @Override
    public String toString() {
        return "PageUtils{" +
                "index=" + index +
                ", end=" + end +
                ", crrentPage=" + crrentPage +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                ", totalNumber=" + totalNumber +
                ", totalPage=" + totalPage +
                '}';
    }
}
