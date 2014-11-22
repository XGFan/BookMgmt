package com.bean.corbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Corbook entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.bean.corbook.Corbook
 */

public class CorbookDAO extends HibernateDaoSupport {
    private static final Log log = LogFactory.getLog(CorbookDAO.class);

    // property constants

    protected void initDao() {
        // do nothing
    }

}