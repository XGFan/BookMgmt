package com.dao;

import com.bean.courclass.Courclass;

import java.util.List;

public interface CourclassDAO {
    /**
     * 根据科目和学期来查找courclass
     *
     * @see com.bean.courclass.Courclass
     * @param idcor    科目id
     * @param semester 学期
     * @return ？
     */
    public List<Courclass> findByCorSem(String idcor, String semester);
}
