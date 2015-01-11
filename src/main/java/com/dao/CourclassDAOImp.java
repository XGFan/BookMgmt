package com.dao;

import com.bean.courclass.Courclass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.courclass.Courclass
 */
@Repository("courclassDAO")
public class CourclassDAOImp extends BaseDaoImp<Courclass> implements
        CourclassDAO {
    private static final Log log = LogFactory.getLog(CourclassDAOImp.class);

    public List<Courclass> findByCorSem(String idcor, String semester) {
        log.debug("finding all Courclass instances");
        List res = null;
        try {
            String queryString = "from Courclass where idcor='" + idcor
                    + "' and semester='" + semester + "'";
            res = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return res;
    }
}