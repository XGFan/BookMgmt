package com.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Courplan entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.corplan.Courplan
 */

public class CourplanDAO extends HibernateDaoSupport {
    public static final String IDCOR = "idcor";
    private static final Log log = LogFactory.getLog(CourplanDAO.class);
    private static final String SEMESTER = "semester";

    // property constants

    protected void initDao() {
        // do nothing
    }

}