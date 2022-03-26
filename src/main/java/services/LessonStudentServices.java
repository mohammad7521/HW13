package services;

import entities.LessonTerm;
import repository.LessonStudentRepo;

import java.util.List;

public class LessonStudentServices implements BaseServices<LessonTerm> {


    private LessonStudentRepo lessonStudentRepo=new LessonStudentRepo();


    @Override
    public LessonTerm add(LessonTerm lessonStudent) {
        return lessonStudentRepo.add(lessonStudent);
    }

    @Override
    public LessonTerm remove(int id) {
        return null;
    }

    @Override
    public void update(LessonTerm lessonStudent) {

    }

    @Override
    public LessonTerm showInfo(int id) {
        return lessonStudentRepo.showInfo(LessonTerm.class,id);
    }

    @Override
    public LessonTerm showInfo(String username) {
        return null;
    }

    @Override
    public List<LessonTerm> showAll() {
        return null;
    }
}
