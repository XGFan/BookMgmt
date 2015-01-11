package com.dao;

import com.bean.supplier.Supplier;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MyEclipse Persistence Tools
 * @see com.bean.supplier.Supplier
 */
@Repository("supplierDAO")
public class SupplierDAOImp extends BaseDaoImp<Supplier> implements SupplierDAO {
    private static final Log log = LogFactory.getLog(SupplierDAOImp.class);

    public List likeFindByPub(String publisher) {
        return findByPropertyF("supplier", publisher);
    }

    public List findBySubPub(String supplier, String publisher) {
        String hql = "from Supplier where supplier='" + supplier
                + "' and publisher like"+"%" + publisher + "%";
        return getCurrentSession().createQuery(hql).list();
    }

    public List accfindBySubPub(String supplier, String publisher) {
        log.debug("acc findBySubPub");
        try {
            String hql = "from Supplier where supplier='" + supplier;
            hql += "' and publisher = '" + publisher + "'";
            return getCurrentSession().createQuery(hql).list();
        } catch (RuntimeException re) {
            log.error("acc findBySubPub failed", re);
            throw re;
        }
    }


    public List findBySupplier(String supplier) {
        return findByPropertyA("supplier", supplier);
    }

    public List findByPublisher(String publisher) {
        return findByPropertyA("publisher", publisher);
    }


    public List findAllSupplier() {
        log.debug("finding all Supplier instances");
        List list = null;
        try {
            String queryString = "select distinct s.supplier from Supplier s order by s.supplier asc";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all Supplier instances", re);
        }
        return list;
    }

    @Override
    public List findAllublisher() {
        log.debug("finding all Supplier instances");
        List list = null;
        try {
            String queryString = "select distinct s.publisher from Supplier s order by s.supplier asc";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all Supplier instances failed", re);
        }
        return list;
    }

    public List findAllOrderByPublisher() {
        log.debug("finding all Supplier instances");
        List list = null;
        try {
            String queryString = "from Supplier s order by s.publisher asc";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all Supplier instances failed", re);
        }
        return list;
    }

    public List findAllOrderByIdsp() {
        log.debug("finding all Supplier instances");
        List list = null;
        try {
            String queryString = "from Supplier s order by s.idsp";
            list = getCurrentSession().createQuery(queryString).list();
        } catch (RuntimeException re) {
            log.error("find all Supplier instances failed", re);
        }
        return list;
    }
}