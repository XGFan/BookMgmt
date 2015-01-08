package com.dao;

import com.bean.corbkview.Corbkview;
import com.bean.corbook.Corbookview;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.corbook.Corbookview
 */
@Repository("corbookviewDAO")
public class CorbookviewDAO extends BaseDaoImp<Corbookview> {
    private static final Log log = LogFactory.getLog(CorbookviewDAO.class);

    /**
     * 通过idcor精确查找
     *
     * @param Idcor 科目id
     * @return Corbookview obj list
     */
    public List findByIdcor(String Idcor) {
        return findByPropertyA("idcor", Idcor);
    }

    /**
     * 通过idbk精确查找
     *
     * @param Idbk 教材id
     * @return Corbookview obj list
     */
    public List findByIdbk(String Idbk) {
        return findByPropertyA("idbk", Idbk);
    }


    /**
     * 通过corname模糊查找
     *
     * @param corname 科目名称
     * @return CourseBookView obj LIST
     */
    public List findCourseByCorname(String corname) {
        return findByPropertyF("corname", corname);
    }

}