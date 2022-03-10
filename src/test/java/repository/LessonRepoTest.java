package repository;

import entities.Lesson;
import org.junit.jupiter.api.*;
import utils.HibernateSingleton;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LessonRepoTest {
    private LessonRepo lessonRepo=new LessonRepo();


    @BeforeAll
    static void setup(){
        var sessionFactory= HibernateSingleton.getInstance();
    }



    //add and showInfo
    @Test
    void addAndShowInfo() {

        //arrange
        var lesson=new Lesson(null,"physics",3,18,null,null,null);

        //act
        lessonRepo.add(lesson);

        //assert
        Lesson loadedLesson=lessonRepo.showInfo(lesson.getLessonId(),Lesson.class);
        assertAll(
                ()->assertNotNull(loadedLesson),
                ()->assertEquals(loadedLesson.getLessonId(),lesson.getLessonId()),
                ()->assertEquals(loadedLesson.getName(),lesson.getName()),
                ()->assertEquals(loadedLesson.getUnit(),lesson.getUnit()),
                ()->assertEquals(loadedLesson.getGrade(),lesson.getGrade())
        );
    }

    @Test
    void remove() {

        //arrange
        var lesson=new Lesson(null,"physics",3,18,null,null,null);


        //act
        lessonRepo.add(lesson);


        //assert
        lessonRepo.remove(lesson);
        List<Lesson> accountList=lessonRepo.showAll(lesson);
        assertEquals(0,accountList.size());
    }

    @Test
    void update() {
        //arrange
        var lesson=new Lesson(null,"physics",3,18,null,null,null);



        //act
        lessonRepo.add(lesson);


        //assert
        lesson.setGrade(12);
        lesson.setUnit(2);
        lessonRepo.update(lesson);

        Lesson loadedLesson=lessonRepo.showInfo(lesson.getLessonId(),Lesson.class);
        assertAll(
                ()->assertNotNull(loadedLesson),
                ()->assertEquals(loadedLesson.getLessonId(),lesson.getLessonId()),
                ()->assertEquals(loadedLesson.getGrade(),lesson.getGrade()),
                ()->assertEquals(loadedLesson.getName(),lesson.getName()),
                ()->assertEquals(loadedLesson.getUnit(),lesson.getUnit())
        );
    }


    @AfterEach
    void tearDown() {
        lessonRepo.hqlTruncate("Lesson");
    }





}