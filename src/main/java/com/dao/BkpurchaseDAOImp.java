package com.dao;

import com.bean.bkpurchase.Bkpurchase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教科书购买表DAO
 */
@Repository("bkpurchaseDAO")
public class BkpurchaseDAOImp extends BaseDaoImp<Bkpurchase> implements BkpurchaseDAO {

    public static final String BKNUM = "bknum";
    public static final String CAMPUS = "campus";
    public static final String SUPPLIER = "supplier";
    private static final Log log = LogFactory.getLog(BkpurchaseDAOImp.class);

}