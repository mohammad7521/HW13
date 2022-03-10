package repository;

import entities.Lesson;
import entities.Student;
import org.junit.jupiter.api.*;
import utils.HibernateSingleton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepoTest {


    private StudentRepo studentRepo=new StudentRepo();


    @BeforeAll
    static void setup(){
        var sessionFactory= HibernateSingleton.getInstance();
    }



    //add and showInfo
    @Test
    void addAndShowInfo() {

        //arrange
        Set<Lesson> lessonList=new HashSet<>();
        Lesson l1=new Lesson(null,"Physics",2,3,null,null,null);
        Lesson l2=new Lesson(null,"math",4,12,null,null,null);

        lessonList.add(l1);
        lessonList.add(l2);
        var student=new Student(null,"firstName","lastName","Username","password",
                lessonList);

        student.setStudentLessonList(lessonList);

        //act
        studentRepo.add(student);

        //assert
        Student loadedStudent=studentRepo.showInfo(student.getStudentId(),Student.class);
        assertAll(
                ()->assertNotNull(loadedStudent),
                ()->assertEquals(loadedStudent.getStudentId(),student.getStudentId()),
                ()->assertEquals(loadedStudent.getFirstName(),student.getFirstName()),
                ()->assertEquals(loadedStudent.getLastName(),student.getLastName()),
                ()->assertEquals(loadedStudent.getUsername(),student.getUsername()),
                ()->assertEquals(loadedStudent.getPassword(),student.getPassword()),
                ()->assertEquals(loadedStudent.getStudentLessonList(),student.getStudentLessonList())
        );
    }

    @Test
    void remove() {

        //arrange
        var student=new Student(null,"firstName","firstName","Username","password",
                null);


        //act
        studentRepo.add(student);


        //assert
        studentRepo.remove(student);
        List<Student> accountList=studentRepo.showAll(student);
        assertEquals(0,accountList.size());
    }

    @Test
    void update() {
        //arrange
        var student=new Student(null,"firstName","firstName","Username","password",
                null);



        //act
        studentRepo.add(student);


        //assert
        student.setFirstName("reza");
        student.setLastName("mohammadi");
        studentRepo.update(student);

        Student loadedStudent=studentRepo.showInfo(student.getStudentId(),Student.class);
        assertAll(
                ()->assertNotNull(loadedStudent),
                ()->assertEquals(loadedStudent.getStudentId(),student.getStudentId()),
                ()->assertEquals(loadedStudent.getUsername(),student.getUsername()),
                ()->assertEquals(loadedStudent.getFirstName(),student.getFirstName()),
                ()->assertEquals(loadedStudent.getLastName(),student.getLastName()),
                ()->assertEquals(loadedStudent.getPassword(),student.getPassword())
        );
    }


//    @AfterEach
//    void tearDown() {
//        studentRepo.hqlTruncate("Student");
//    }
//




}