package repository;

import entities.Lesson;
import entities.Student;
import entities.Term;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentRepo extends BasicCrud<Student> {

    //get the last term of a student
    public Term getLastTerm(int studentID) {

        Student student=showInfo(studentID,Student.class);
        Set<Term>studentTerms=student.getStudentTerm();
        Term lastTerm=studentTerms.stream().reduce((x,y)->y).orElse(null);
        return lastTerm;
    }


    //assigning lessons to a term of a student
    public void assignLessons(List<Lesson> lessonList,int studentID){

        Student student=showInfo(studentID,Student.class);

        Term lastTerm=getLastTerm(studentID);
        Term newTerm=new Term(lastTerm.getTermId()+1,null,
                null,null,null);

        for(Lesson l:lessonList){
            newTerm.addStudentLessons(l);
        }

        student.addTerm(newTerm);

    }


}
