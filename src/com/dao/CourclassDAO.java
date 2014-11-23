package com.dao;

import java.util.List;

public interface CourclassDAO {
    /**
     * todo
     * @param idcor 科目id
     * @param semester 学期
     * @return ？
     */
    public List findByCorSem(String idcor, String semester);
}
