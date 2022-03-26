package repository;

import org.hibernate.SessionFactory;
import utils.HibernateSingleton;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BasicCrudImpl<T> {


    protected SessionFactory sessionFactory= HibernateSingleton.getInstance();



    public T add(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(t);
                transaction.commit();
                session.close();
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
            session.close();
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
                session.close();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }



    //based on username
    public T showInfo(Class<T> tClass,String username) {

        List<T> result=new ArrayList<>();

        try(var session=sessionFactory.openSession()) {
            var trx=session.beginTransaction();

            try {
                var cb = session.getCriteriaBuilder();
                var cq = cb.createQuery(tClass);
                Root<T> root = cq.from(tClass);
                cq.select(root).where(cb.equal(root.get("username"), username));
                Query query = session.createQuery(cq);
                result = query.getResultList();
                trx.commit();
                session.close();

            }catch (Exception e){
                trx.rollback();
            }

        }
        return result.get(0);
    }


    public T showInfo(Class<T> tClass,int Id){
        T t = null;
        try (var session = sessionFactory.openSession()) {
            var trx = session.beginTransaction();

            try {
                t = session.find(tClass, Id);
                trx.commit();
                session.close();

            } catch (Exception e) {
                trx.rollback();
            }
        }
        return t;
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
