package repository;

import entities.*;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepo extends BasicCrudImpl<Teacher> {


    public void assignLessons(Teacher teacher, List<LessonTerm> lessonList, TeacherTerm tt, Term masterTerm) {

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {

                tt.setTermTeacherLesson(lessonList);
                tt.setMasterTeacherTerm(masterTerm);
                session.save(tt);
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
                for (LessonTerm l : lessonList) {
                    l.setTeacherTermLesson(tt);
                    session.save(l);
                }
                transaction.commit();
                session.close();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }


    public List<LessonTerm> showUnSubmittedLessons(int teacherId) {

        List<LessonTerm> lessonList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {

            var trx = session.beginTransaction();

            try {
                String query = "select a from LessonTerm a inner join TeacherTerm b on a.teacherTermLesson.id=b.id where b.termTeacher.id=:teacherId and a.grade=0";
                var hql = session.createQuery(query);
                hql.setParameter("teacherId", teacherId);
                lessonList = hql.getResultList();
            } catch (Exception e) {
                trx.rollback();
            }
        }
        return lessonList;
    }


    public TeacherTerm lastTerm(int teacherId) {

        List<TeacherTerm> teacherTerms = null;
        try (var session = sessionFactory.openSession()) {

            var trx = session.beginTransaction();

            try {
                String query = "select a from TeacherTerm a where a.termTeacher.id=:teacherId";
                var hql = session.createQuery(query);
                hql.setParameter("teacherId", teacherId);
                teacherTerms = hql.getResultList();
            } catch (Exception e) {
                trx.rollback();
            }
        }

        if (teacherTerms.size() < 1) return null;
        else
            return teacherTerms.get(teacherTerms.size() - 1);
    }


    public List<LessonTerm> lastTermLessons(int lastTermId){

        List<LessonTerm> lessonList=new ArrayList<>();
        try(var session=sessionFactory.openSession()){

            var trx=session.beginTransaction();

            try{
                String query="select a from LessonTerm a inner join TeacherTerm b on a.teacherTermLesson.id=b.id where b.id=:lastTermId";
                var hql=session.createQuery(query);
                hql.setParameter("lastTermId",lastTermId);
                lessonList=hql.getResultList();
            }catch (Exception e){
                trx.rollback();
            }
        }
        return lessonList;
    }
}
