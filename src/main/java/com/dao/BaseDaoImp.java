package com.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:18:35
 * Created by guofan on 2015/1/6
 */
@SuppressWarnings({"unused","unchecked"})
@Repository("baseDao")
public class BaseDaoImp<T> implements BaseDao<T> {
    
    public Class entityClass;
    private SessionFactory sessionFactory;
    private static final Log log = LogFactory.getLog(BaseDaoImp.class);
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public BaseDaoImp(){
        entityClass = ReflectUtils.getClassGenricType(getClass());
    }

    public boolean save(T obj) {
        log.debug("BaseDAO Save " + entityClass.getName());
        boolean result = true;
        try {
            this.getCurrentSession().save(obj);
        } catch (HibernateException hex) {
            log.error("Save "+entityClass.getName()+" Failed",hex);
            result = false;
        }
        return result;
    }

    public boolean delete(T obj) {
        log.debug("BaseDAO Delete " + entityClass.getName());
        boolean result = true;
        try {
            this.getCurrentSession().delete(obj);
        } catch (HibernateException hex) {
            log.error("Delete "+entityClass.getName()+" Failed",hex);
            result = false;
        }
        return result;
    }

    public boolean update(T obj) {
        log.debug("BaseDAO Update " + entityClass.getName());
        boolean result = true;
        try {
            this.getCurrentSession().update(obj);
        } catch (HibernateException hex) {
            log.error("Update "+entityClass.getName()+" Failed",hex);
            result = false;
        }
        return result;
    }

    public List findByHql(String hql) {
        log.debug("BaseDAO Query : " + hql);
        List list;
        try {
            list = this.getCurrentSession().createQuery(hql).list();
        } catch (HibernateException hex) {
            log.error("Query : "+hql+" Failed",hex);
            return null;
        }
        return list;
    }

    public T findById(Serializable id) {
        log.debug("BaseDAO Find "+entityClass.getName()+" By Id "+id);
        T res;
        try {
            res = (T)this.getCurrentSession().get(entityClass, id);
        } catch (HibernateException hex) {
            log.debug("BaseDAO Find " + entityClass.getName() + " By Id " + id + " Failed",hex);
            res = null;
        }
        return res;
    }

    public List<T> getAll() {
        log.debug("BaseDAO Get All "+entityClass.getName());
        String hql = "from " + getEntityClass().getName();
        List<T> res;
        try{
            res = this.getCurrentSession().createQuery(hql).list();
        }catch (HibernateException hex){
            log.error("BaseDAO Get All "+entityClass.getName()+" Failed",hex);
            res = null;
        }
        return res;
    }


    public List<T> findByPropertyA(String propertyName, String value) {
        log.debug("BaseDAO Find(A) "+propertyName+" From "+entityClass.getName());
        List<T> res;
        String hql = "from " + entityClass.getName() + " where " + propertyName + " = '" + value + "'";
        try {
            res = this.getCurrentSession().createQuery(hql).list();
        }catch (HibernateException hex){
            log.error("BaseDAO Find(A) "+propertyName+" From "+entityClass.getName()+" Failed",hex);
            res = null;
        }
        return res;
    }

    public List<T> findByPropertyF(String propertyName, String value) {
        log.debug("BaseDAO Find(F) "+propertyName+" From "+entityClass.getName());
        List<T> res;
        String hql = "from " + entityClass.getName() + " where " + propertyName + " LIKE '%" + value + "%'";
        try {
            res = this.getCurrentSession().createQuery(hql).list();
        }catch (HibernateException hex){
            log.error("BaseDAO Find(F) "+propertyName+" From "+entityClass.getName()+" Failed",hex);
            res = null;
        }
        return res;
    }


    /**
     * DATE:2014/12/6
     * TIME:16:28
     * Created by guofan on 2014/12/6
     */
    public static class ReflectUtils {
        /**
         * 获得超类的参数类型，取第一个参数类型
         * @param <T> 类型参数
         * @param clazz 超类类型
         */

        public static <T> Class<T> getClassGenricType(final Class clazz) {
            return getClassGenricType(clazz, 0);
        }

        /**
         * 根据索引获得超类的参数类型
         * @param clazz 超类类型
         * @param index 索引
         */
        public static Class getClassGenricType(final Class clazz, final int index) {
            Type genType = clazz.getGenericSuperclass();
            if (!(genType instanceof ParameterizedType)) {
                return Object.class;
            }
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if (index >= params.length || index < 0) {
                return Object.class;
            }
            if (!(params[index] instanceof Class)) {
                return Object.class;
            }
            return (Class) params[index];
        }
    }
}
