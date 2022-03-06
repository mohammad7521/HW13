package repository;

import entities.Staff;
import org.hibernate.SessionFactory;
import utils.HibernateSingleton;

public class StaffRepoImpl implements StaffRepo<Staff> {


    private SessionFactory sessionFactory= HibernateSingleton.getInstance();


    @Override
    public long add(Staff staff) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(staff);
                transaction.commit();
                return staff.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public long remove(Staff staff) {
        var session = sessionFactory.openSession();
        var transaction=session.beginTransaction();

        try{

            session.remove(staff);
            transaction.commit();
            return staff.getId();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public boolean update(Staff staff) {
        return false;
    }


    @Override
    public Staff showInfo(long id) {
        var session=sessionFactory.openSession();
        return session.find(Staff.class,id);
    }
}
