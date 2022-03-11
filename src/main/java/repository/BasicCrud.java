package repository;

import org.hibernate.SessionFactory;
import utils.HibernateSingleton;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class BasicCrud<T> {


    protected SessionFactory sessionFactory= HibernateSingleton.getInstance();



    public T add(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(t);
                transaction.commit();
                return t;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }


    public T remove(T t) {
        var session = sessionFactory.openSession();
        var transaction=session.beginTransaction();

        try{

            session.remove(t);
            transaction.commit();
            return t;
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }


    public void update(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    //based on id
    public T showInfo(Integer ID,Class <T> tClass) {
        var session=sessionFactory.openSession();
        return  session.find(tClass,ID);

    }


    //based on username
    public T showInfo(String username,Class <T> tClass) {
        var session=sessionFactory.openSession();
        return session.find(tClass,username);
    }


    public int hqlTruncate(String tableName) {
        int returnQueryInt;
        var session = sessionFactory.openSession();
        var transaction=session.beginTransaction();
        try {
            String hql = String.format("delete from %s",tableName);
            Query query = session.createQuery(hql);
            returnQueryInt=query.executeUpdate();
            transaction.commit();

        }catch (Exception e){
            transaction.rollback();
            throw e;
        }

        return returnQueryInt;
    }



    public List<T> showAll(Class <T> tClass){
        var session=sessionFactory.openSession();
        var transaction=session.beginTransaction();

        List<T> list;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(tClass);
            criteria.from(tClass);
            list = session.createQuery(criteria).getResultList();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
        return list;
    }
}
