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
public class SupplierDAOImp implements SupplierDAO {
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

    @Override
    public List findByPropertyAccurate(String propertyName, String value) {
        log.debug("accurate finding Supplier instance with property: " + propertyName
                + ", value: " + value);
        List list = null;
        try {
            String hql = "from Supplier s where s." + propertyName + " = ?";
            list = getHibernateTemplate().find(hql, value);
        } catch (RuntimeException re) {
            log.error("accurate find by " + propertyName + " failed", re);
        }
        return list;
    }

    @Override
    public List findByPropertyFuzzy(String propertyName, String value) {
        log.debug("fuzzy finding Supplier instance with property: " + propertyName
                + ", value: " + value);
        List list = null;
        try {
            String hql = "from Supplier b where b." + propertyName + " like '%" + value + "%'";
            list = getHibernateTemplate().find(hql);
        } catch (RuntimeException re) {
            log.error("fuzzy find by " + propertyName + " failed", re);
        }
        return list;
    }


    public List likeFindByPub(String publisher) {
        return findByPropertyFuzzy(PUBLISHER, publisher);
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

    public boolean updateSup(Supplier sup) {
        boolean tag = true;
        try {
            getHibernateTemplate().saveOrUpdate(sup);
            log.debug("updateSup successful");
        } catch (RuntimeException re) {
            log.error("updateSup failed", re);
            tag = false;
        }
        return tag;
    }

    public boolean save(Supplier transientInstance) {
        log.debug("saving Supplier instance");
        boolean tag = true;
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            tag = false;
        }
        return tag;
    }

    public boolean delete(Supplier persistentInstance) {
        log.debug("deleting Supplier instance");
        boolean tag = true;
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            tag = false;
        }
        return tag;
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
        return findByPropertyAccurate(SUPPLIER, supplier);
    }

    public List findByPublisher(String publisher) {
        return findByPropertyAccurate(PUBLISHER, publisher);
    }


    public List findAll() {
        log.debug("finding all publisher instances");
        List list = null;
        try {
            String queryString = "from Supplier s order by convert_gbk(s.publisher) asc";
            list = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
        }
        return list;
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