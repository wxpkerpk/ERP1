package com.guotion.material.service.dao.impl;

import com.guotion.material.service.bean.QueryResult;
import com.guotion.material.service.dao.DBCommonOperate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/1/6 0006.
 */
@Repository
public class AbstractDBCommonOperate implements DBCommonOperate {
    @Autowired
    protected HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public <T> void add(Class<T> entityClass, Object entity) {
        hibernateTemplate.save(getEntityName(entityClass),entity);
    }

    @Override
    public void delete(Object entity) {
        hibernateTemplate.delete(entity);
    }

    @Override
    public <T> void update(Class<T> entityClass, Object entity) {
        hibernateTemplate.update(getEntityName(entityClass), entity);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object entityId) {
        List<T> list = (List<T>) hibernateTemplate.find("from "+getEntityName(entityClass)+" o where o.id=?",entityId);
        if(list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public <T> List<T> find(Class<T> entityClass,String whereHQL,Object[] params) {
        return find(entityClass,whereHQL,params,null);
    }

    @Override
    public <T> List<T> find(final Class<T> entityClass,final String whereHQL,final Object[] params,final LinkedHashMap<String, String> order) {
        return (List<T>) hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("from " + getEntityName(entityClass) + " o " + (whereHQL == null ? "" : "where " + whereHQL) + " " + buildOrderBy(order));
                setQueryParams(query, params);
                query.setCacheable(true);
                return query.list();
            }
        });
    }

    @Override
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int startIndex, int resultCount) {
        return getScrollData(entityClass,startIndex,resultCount,null,null,null);
    }

    @Override
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int startIndex, int resultCount, String whereHQL, Object[] params) {
        return getScrollData(entityClass,startIndex,resultCount,whereHQL,params,null);
    }

    @Override
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int startIndex, int resultCount, LinkedHashMap<String, String> order) {
        return getScrollData(entityClass, startIndex, resultCount,null,null,order);
    }

    @Override
    public <T> QueryResult<T> getScrollData(final Class<T> entityClass, final int startIndex, final int resultCount, final String whereHQL, final Object[]params, final LinkedHashMap<String, String> order) {
        final QueryResult<T> queryResult= (QueryResult<T>) hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                QueryResult<T> queryResult = new QueryResult<T>();

                Query query = session.createQuery("from " + getEntityName(entityClass) + " o " + (whereHQL == null ? "" : "where " + whereHQL) + " " + buildOrderBy(order));
                setQueryParams(query, params);
                if (startIndex >= 0) {
                    query.setFirstResult(startIndex);
                    query.setMaxResults(resultCount);
                }
                query.setCacheable(true);
                queryResult.setList(query.list());
                return queryResult;
            }
        });

        hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query qr = session.createQuery("select count(o) from " + getEntityName(entityClass) + " o " + (whereHQL == null ? "" : "where " + whereHQL));
                setQueryParams(qr, params);
                qr.setCacheable(true);
                queryResult.setTotalRecord((Long) qr.uniqueResult());
                return queryResult;
            }
        });

        return queryResult;
    }

    protected void setQueryParams(Query query,Object[] params){
        if (params != null && params.length > 0){
            for (int i = 0; i < params.length;i++){
                query.setParameter(i,params[i]);
            }
        }
    }

    protected String buildOrderBy(LinkedHashMap<String,String> order){
        if(order==null || order.size() == 0) {
            return "";
        }
        StringBuilder orderHQL = new StringBuilder();
        orderHQL.append(" order by ");
        for (String key : order.keySet()){
            orderHQL.append(key).append(" ").append(order.get(key)).append(",");
        }
        orderHQL.deleteCharAt(orderHQL.length() - 1);


        return orderHQL.toString();
    }

    protected  <T> String getEntityName(Class<T> entityClass){
        String entityName = entityClass.getSimpleName();
        Entity entity = entityClass.getAnnotation(Entity.class);
        if(entity.name() != null && !"".equals(entity.name())){
            entityName = entity.name();
        }
        return entityName;
    }



    //    public <T> QueryResult<T> getScrollData(final int startIndex, final int resultCount, final String hql, final Object[]params) {
//        final QueryResult<T> queryResult = (QueryResult<T>) hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                QueryResult<T> queryResult = new QueryResult<T>();
//                Query query = session.createQuery(hql);
//                setQueryParams(query, params);
//                if (startIndex >= 0) {
//                    query.setFirstResult(startIndex);
//                    query.setMaxResults(resultCount);
//                }
//                //query.setCacheable(true);
//                queryResult.setList(query.list());
//                return queryResult;
//            }
//        });
//        hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                Query qr = session.createQuery("select count(o) "+hql);
//                setQueryParams(qr, params);
//                //qr.setCacheable(true);
//                queryResult.setTotalRecord((Long) qr.uniqueResult());
//                return queryResult;
//            }
//        });
//
//        return queryResult;
//    }


    @Override
    public <T> QueryResult<T> getScrollData(final Class<T> entityClass, final int startIndex, final int resultCount, final String whereHQL, final Object[]params,final List paralist, final LinkedHashMap<String, String> order) {
        final QueryResult<T> queryResult= (QueryResult<T>) hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                QueryResult<T> queryResult = new QueryResult<T>();

                Query query = session.createQuery("from " + getEntityName(entityClass) + " o " + (whereHQL == null ? "" : "where " + whereHQL) + " " + buildOrderBy(order));
                setQueryParams(query, params);
                query.setParameterList("paralist",paralist);
                if (startIndex >= 0) {
                    query.setFirstResult(startIndex);
                    query.setMaxResults(resultCount);
                }
                query.setCacheable(true);
                queryResult.setList(query.list());
                return queryResult;
            }
        });

        hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query qr = session.createQuery("select count(o) from " + getEntityName(entityClass) + " o " + (whereHQL == null ? "" : "where " + whereHQL));
                setQueryParams(qr, params);
                qr.setParameterList("paralist",paralist);
                qr.setCacheable(true);
                queryResult.setTotalRecord((Long) qr.uniqueResult());
                return queryResult;
            }
        });

        return queryResult;
    }

    public void bulkDelete(final String hql,  final String[] params) {
       hibernateTemplate.executeWithNativeSession(new HibernateCallback() {
           public Object doInHibernate(Session session) throws HibernateException {
               Query query = session.createQuery(hql);
               setQueryParams(query, params);
               return query.executeUpdate();
           }
       });
    }

    public void bulkDeleteList(final String hql, final String[] params) {
        hibernateTemplate.executeWithNativeSession(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setQueryParams(query, params);
                return query.executeUpdate();
            }
        });
    }

}
