package repository;

import entities.*;

import java.util.ArrayList;
import java.util.List;

public class StudentRepo extends BasicCrudImpl<Student> {


    //show all lessons of student from previous term
    public List<LessonTerm> showAllLessons(int studentId){

        List<LessonTerm> lessonList=new ArrayList<>();
        try(var session=sessionFactory.openSession()){

            var trx=session.beginTransaction();

            try{
            String query="select a from LessonTerm a inner join StudentTerm b on a.studentTermLesson.id=b.id where b.termStudent.id=:studentId and a.passed=true";
            var hql=session.createQuery(query);
            hql.setParameter("studentId",studentId);
            lessonList=hql.getResultList();
            }catch (Exception e){
                trx.rollback();
            }
        }
        return lessonList;
    }


    //show last term lessons
    public StudentTerm lastTerm(int studentId){

            List<StudentTerm> studentTerms=null;
            try(var session=sessionFactory.openSession()){

                var trx=session.beginTransaction();

                try{
                    String query="select a from StudentTerm a where a.termStudent.id=:studentId";
                    var hql=session.createQuery(query);
                    hql.setParameter("studentId",studentId);
                    studentTerms=hql.getResultList();
                }catch (Exception e){
                    trx.rollback();
                }
            }

            if (studentTerms.size()<1) return null;
            else
           return studentTerms.get(studentTerms.size()-1);
    }


    //get last term lessons
    public List<LessonTerm> lastTermLessons(int lastTermId){

        List<LessonTerm> lessonList=new ArrayList<>();
        try(var session=sessionFactory.openSession()){

            var trx=session.beginTransaction();

            try{
                String query="select a from LessonTerm a inner join StudentTerm b on a.studentTermLesson.id=b.id where b.id=:lastTermId";
                var hql=session.createQuery(query);
                hql.setParameter("lastTermId",lastTermId);
                lessonList=hql.getResultList();
            }catch (Exception e){
                trx.rollback();
            }
        }
        return lessonList;
    }



    //assign lessons to a term
    public void assignLessons(List<LessonTerm> lessonList, StudentTerm st, Term masterTerm){

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {

                st.setTermStudentLesson(lessonList);
                st.setMasterStudentTerm(masterTerm);
                session.save(st);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                for (LessonTerm l:lessonList){
                    l.setStudentTermLesson(st);
                    session.update(l);
                }
                transaction.commit();
                session.close();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }


}
