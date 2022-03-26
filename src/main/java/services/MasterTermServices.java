package services;

import entities.StudentTerm;
import entities.TeacherTerm;
import entities.Term;
import repository.MasterTermRepo;

public class MasterTermServices {

    private static MasterTermRepo masterTermRepo=new MasterTermRepo();



    public Term getLastTerm(){

        Term lastTerm=masterTermRepo.getLastTerm();

        if (lastTerm==null){
            lastTerm=new Term(null,null,null);
           addTerm(lastTerm);
        }
        return lastTerm;
    }



    public Term addTerm(Term term){
        return masterTermRepo.add(term);
    }


    public void assignStudentTerm(StudentTerm st){
        masterTermRepo.assignStudentTerm(st);
    }



    public void assignTeacherTerm(TeacherTerm tt){
        masterTermRepo.assignTeacherTerm(tt);
    }
}
