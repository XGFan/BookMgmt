package com.util;

/**
 * 用来分页的类
 */
public class Pagination {
    /*当前页开始（初始）位置*/
    private int start = 1;
    /*一次取得的数量*/
    private int size = 6;
    /*当前页码*/
    private int currentPage = 1;
    /*总的记录页数*/
    private int totalPage = 0;
    /*总的记录条数*/
    private int totalRecord = 0;

    /**
     * 构造方法，根据size来确定每页条目数量
     *
     * @param size 每页条目数量
     */
    public Pagination(int size) {
        super();
        this.size = size;
    }

    /**
     * 获取当前页开始（初始）位置
     *
     * @return int 开始（初始）位置
     */
    public int getStart() {
        this.start = (currentPage - 1) * size;
        return start;
    }

    /**
     * 设置开始（初始）位置
     *
     * @param start 开始（初始）位置
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * 获取每页的数量
     *
     * @return 每页记录数量
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置每页的条目数量
     *
     * @param size 每页记录数量
     */
    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前页的页码
     *
     * @param currentPage 页码
     */
    public void setCurrentPage(int currentPage) {
        if (currentPage < 1)
            this.currentPage = 1;
        if (currentPage >= this.totalPage)
            this.currentPage = this.totalPage;
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    void setTotalPage() {
        this.totalPage = totalRecord % size == 0 ? totalRecord / size : totalRecord / size + 1;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    /**
     * 设置所有记录，并计算出所有页码
     *
     * @param totalRecord 设置所有记录数量
     */
    public void setTotalRecord(int totalRecord) {
        if (totalRecord < 1)
            totalRecord = 0;
        this.totalRecord = totalRecord;
        //获取页数
        this.totalPage = totalRecord % size == 0 ? totalRecord / size : totalRecord / size + 1;
        setTotalPage();
    }

    @Override
    public String toString() {
        return "Pagination [start=" + start + ", size=" + size
                + ", currentPage=" + currentPage + ", totalPage=" + totalPage
                + ", totalRecord=" + totalRecord + "]";
    }

}
