package services;

import entities.Lesson;
import entities.LessonTerm;
import entities.Student;
import exceptionHandler.NoSuchId;
import repository.LessonTermRepo;

import java.util.List;

public class LessonTermServices implements BaseServices<LessonTerm> {

    LessonTermRepo lessonTermRepo=new LessonTermRepo();
    @Override
    public LessonTerm add(LessonTerm lessonTerm) {
        return lessonTermRepo.add(lessonTerm);
    }

    @Override
    public LessonTerm remove(int id) {
        return null;
    }

    @Override
    public void update(LessonTerm lessonTerm) {
        lessonTermRepo.update(lessonTerm);
    }

    @Override
    public LessonTerm showInfo(int id) {

        if (!checkId(id)){
            throw new NoSuchId();
        }
        return lessonTermRepo.showInfo(LessonTerm.class,id);
    }

    @Override
    public LessonTerm showInfo(String username) {
        return null;
    }

    @Override
    public List<LessonTerm> showAll() {
        return lessonTermRepo.showAll(LessonTerm.class);
    }

    public boolean checkId(int Id) {

        boolean flag = false;
        List<LessonTerm> lessonTerms = showAll();

        for (LessonTerm lt : lessonTerms) {
            if (lt.getLessonId() == Id) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
