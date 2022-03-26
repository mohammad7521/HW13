package repository;

import entities.StudentTerm;
import entities.TeacherTerm;
import entities.Term;

import java.util.List;

public class MasterTermRepo extends BasicCrudImpl<Term>{

    //get the last available term set by education staff
    //in order to assign student term and teacher term to in

    public Term getLastTerm(){

        List<Term> termList;
        var session=sessionFactory.openSession();
        String query="select a from Term a";
        var hql=session.createQuery(query);
        termList=hql.getResultList();
        session.close();

        if (termList.size()<1)
            return null;
        else
        return termList.get(termList.size()-1);

    }



    //assign student term entity to the master therm
    public void assignStudentTerm(StudentTerm st){

        Term lastTerm=getLastTerm();

        try(var session=sessionFactory.openSession()){

            var trx=session.beginTransaction();

            try{
                lastTerm.addStudentTerm(st);
                session.update(lastTerm);
                session.close();
                trx.commit();
            }catch (Exception e){
                trx.rollback();
            }
        }
    }




    //assign teacher term entity to the master term
    public void assignTeacherTerm(TeacherTerm tt){

        Term lastTerm=getLastTerm();

        try(var session=sessionFactory.openSession()){

            var trx=session.beginTransaction();

            try{
                lastTerm.addTeacherTerm(tt);
                session.update(lastTerm);
                session.close();
                trx.commit();
            }catch (Exception e){
                trx.rollback();
            }
        }
    }

}
