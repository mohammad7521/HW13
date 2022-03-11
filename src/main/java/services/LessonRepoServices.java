package services;

import entities.Lesson;
import repository.LessonRepo;

import java.util.List;

public class LessonRepoServices implements BaseServices<Lesson> {

    private static LessonRepo lessonRepo=new LessonRepo();

    public Lesson add(Lesson lesson){
        return lessonRepo.add(lesson);
    }

    @Override
    public Lesson remove(int id) {

        Lesson returnedLesson=lessonRepo.showInfo(id,Lesson.class);
        return lessonRepo.remove(returnedLesson);
    }


    public void update(Lesson lesson){
        lessonRepo.update(lesson);
    }

    @Override
    public Lesson showInfo(int id) {
        return lessonRepo.showInfo(id,Lesson.class);
    }

    @Override
    public List<Lesson> showAll() {
        return lessonRepo.showAll(Lesson.class);
    }

}
