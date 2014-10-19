package com.bean.bookcorsup;

import java.util.List;
import java.util.Set;

public interface BookcorsupDAOInf {
    /**
     * 传入example查找书本课程安排
     * @param instance example
     * @return 结果LIST
     */
	public List findByExample(Bookcorsup instance);

    /**
     * 返回所有书本课程安排
     * @return 所有书本课程安排LIST
     */
	public List findAll();

    /**
     * 根据传入的属性值和属性名，进行模糊查找书本课程安排信息
     * @param propertyName 属性名
     * @param value 属性值
     * @return 模糊查找结果
     */
	public List findByProperty(String propertyName, Object value);

    /**
     * TODO 未知的方法
     * @param str
     * @return
     */
	public List findbklist(String str);

    /**
     * 根据bookid查找书本课程安排信息
     * @param idbk bookid
     * @return 书本课程安排信息list
     */
	public List findcorlistbyidbk(String idbk);
}
