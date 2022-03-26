package services;

import entities.Lesson;
import repository.LessonRepo;
import exceptionHandler.NoSuchId;
import exceptionHandler.WrongUnits;

import java.util.List;

public class LessonRepoServices implements BaseServices <Lesson> {

    private static LessonRepo lessonRepo=new LessonRepo();

    public Lesson add(Lesson lesson){

        if (lesson.getUnit()>4 || lesson.getUnit()<1){
            throw new WrongUnits();
        }
        return lessonRepo.add(lesson);
    }

    @Override
    public Lesson remove(int id) {

        if (!checkId(id)){
            throw new NoSuchId();
        }

        Lesson returnedLesson=showInfo(id);

        return lessonRepo.remove(returnedLesson);
    }


    public void update(Lesson lesson){

        if (!checkId(lesson.getLessonId())){
            throw new NoSuchId();
        }
        lessonRepo.update(lesson);
    }

    @Override
    public Lesson showInfo(int id) {

        if (!checkId(id)){
            throw new NoSuchId();
        }
        return lessonRepo.showInfo(Lesson.class,id);
    }


    @Override
    public Lesson showInfo(String username) {
        return null;
    }

    @Override
    public List<Lesson> showAll() {
        return lessonRepo.showAll(Lesson.class);
    }


    //of nat having an entity with such id
    public boolean checkId(int Id) {

        boolean flag = false;
        List<Lesson> lessonList = showAll();

        for (Lesson l : lessonList) {
            if (l.getLessonId()==Id) {
                flag = true;
                break;
            }
        }
        return flag;
    }



}
