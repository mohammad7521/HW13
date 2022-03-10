package services;

import entities.Lesson;
import repository.LessonRepo;

public class LessonRepoServices implements BaseServices<Lesson> {

    private static LessonRepo lessonRepo=new LessonRepo();

    public Lesson add(Lesson lesson){
        return lessonRepo.add(lesson);
    }

    @Override
    public Lesson remove(long id) {

        Lesson returnedLesson=lessonRepo.showInfo(id,Lesson.class);
        return lessonRepo.remove(returnedLesson);
    }


    public void update(Lesson lesson){
        lessonRepo.update(lesson);
    }

    @Override
    public Lesson showInfo(long id) {
        return lessonRepo.showInfo(id,Lesson.class);
    }

}
