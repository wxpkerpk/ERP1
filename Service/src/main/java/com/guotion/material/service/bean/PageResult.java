package com.guotion.material.service.bean;

import java.util.List;

/**
 * Created by liujun on 2015/1/19.
 */
public class PageResult<T>{
    /**
     * 符合条件的总记录数
     */
    private long totalRecord = 0;

    /**
     * 数据
     */
    private List<T> list;

    /**
     * 每页条数
     */
    private int eachPageCount;

    /**
     * 总页数
     */
    private int totalPageCount;

    /**
     * 当前页
     */
    private int currentPage;

    public long getTotalRecord() {
        return totalRecord;
    }

    public List<T> getList() {
        return list;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getEachPageCount() {
        return eachPageCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setEachPageCount(int eachPageCount) {
        this.eachPageCount = eachPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
