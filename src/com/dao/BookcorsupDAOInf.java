package com.dao;

import java.util.List;

public interface BookcorsupDAOInf {

    /**
     * TODO 未知的方法
     */
    public List findbklist(String str);

    /**
     * 根据bookid查找书本课程安排信息
     *
     * @param idbk bookid
     * @return 书本课程安排信息list
     */
    public List findcorlistbyidbk(String idbk);
}
