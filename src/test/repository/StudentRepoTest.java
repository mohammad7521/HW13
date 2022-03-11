package repository;

import entities.Lesson;
import entities.Student;
import entities.Term;
import org.junit.jupiter.api.*;
import repository.StudentRepo;
import utils.HibernateSingleton;

import java.util.*;

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
        var student=new Student(null,"firstName","lastName","Username","password",
                null);

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
                ()->assertEquals(loadedStudent.getStudentTerm(),student.getStudentTerm())
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
        List<Student> accountList=studentRepo.showAll(Student.class);
        assertEquals(0,accountList.size());
    }

    @Test
    void update() {
        //arrange
        var student=new Student(null,"f","s","u","p",
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



    @AfterEach
    void tearDown() {
        studentRepo.hqlTruncate("Student");
    }





}