package com.dao;

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
@SuppressWarnings("unused")
@Repository("baseDao")
public class BaseDaoImp<T> implements BaseDao<T> {
    public Class entityClass;

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
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

    public boolean add(T obj) {
        boolean result = true;
        try {
            this.getCurrentSession().save(obj);
        } catch (RuntimeException re) {
            result = false;
        }
        return result;
    }

    public boolean del(T obj) {
        boolean result = true;
        try {
            this.getCurrentSession().delete(obj);
        } catch (RuntimeException re) {
            result = false;
        }
        return result;
    }

    public boolean update(T obj) {
        boolean result = true;
        try {
            this.getCurrentSession().update(obj);
        } catch (RuntimeException re) {
            result = false;
        }
        return result;
    }

    public List findByHql(String hql) {
        List list;
        try {
            list = this.getCurrentSession().createQuery(hql).list();
        } catch (RuntimeException re) {
            return null;
        }
        return list;
    }

    public T findById(Serializable id) {
        T res;
        try {
            res = (T)this.getCurrentSession().get(entityClass, id);
        } catch (RuntimeException re) {
            res = null;
        }
        return res;
    }

    public List<T> findAll() {
        String hql = "from " + getEntityClass().getName();
        return findByHql(hql);
    }


    public List<T> findByPropertyA(String propertyName, String value) {
        String hql = "from " + entityClass.getName() + " where " + propertyName + " = '" + value + "'";
        return findByHql(hql);
    }

    public List<T> findByPropertyF(String propertyName, String value) {
        String hql = "from " + entityClass.getName() + " where " + propertyName + " LIKE '%" + value + "%'";
        return findByHql(hql);
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
