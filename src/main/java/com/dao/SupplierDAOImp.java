package com.dao;

import com.bean.supplier.Supplier;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.supplier.Supplier
 */
@Repository("supplierDAO")
public class SupplierDAOImp extends BaseDaoImp<Supplier> implements SupplierDAO {
    private static final Log log = LogFactory.getLog(SupplierDAOImp.class);
    private static final String SUPPLIER = "supplier";
    private static final String PUBLISHER = "publisher";
    @Autowired
    HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public List likeFindByPub(String publisher) {
        return findByPropertyF(PUBLISHER, publisher);
    }

    public List findBySubPub(String supplier, String publisher) {
        String hql = "from Supplier where supplier='" + supplier
                + "' and publisher like ?";
        return getHibernateTemplate().find(hql, "%" + publisher + "%");
    }

    public List accfindBySubPub(String supplier, String publisher) {
        log.debug("accfindBySubPub");
        try {
            String hql = "from Supplier where supplier='" + supplier;
            hql += "' and publisher = '" + publisher + "'";
            return getHibernateTemplate().find(hql);
        } catch (RuntimeException re) {
            log.error("accfindBySubPub failed", re);
            throw re;
        }
    }


    public Supplier findById(java.lang.String id) {
        log.debug("getting Supplier instance with id: " + id);
        Supplier sup = null;
        try {
            sup = (Supplier) getHibernateTemplate().get(
                    "com.bean.supplier.Supplier", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
        }
        return sup;
    }

    public List findBySupplier(String supplier) {
        return findByPropertyA(SUPPLIER, supplier);
    }

    public List findByPublisher(String publisher) {
        return findByPropertyA(PUBLISHER, publisher);
    }


    public List findAllSupplier() {
        log.debug("finding all Supplier instances");
        List list = null;
        try {
            String queryString = "select distinct s.supplier from Supplier s order by convert_gbk(s.supplier) asc";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    public List findAllOrderByPublisher() {
        log.debug("finding all Supplier instances");
        List list = null;
        try {
            String queryString = "from Supplier s order by convert_gbk(s.publisher) asc";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }

    public List findAllOrderByIdsp() {
        log.debug("finding all Supplier instances");
        List list = null;
        try {
            String queryString = "from Supplier s order by s.idsp";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
    }
}