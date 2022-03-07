package repository;

import entities.Student;
import org.junit.jupiter.api.*;
import utils.HibernateSingleton;
import java.util.List;

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
        var student=new Student(null,"firstName","firstName","Username","password");

        //act
        studentRepo.add(student);

        //assert
        Student loadedStudent=studentRepo.showInfo(student.getId(),student);
        assertAll(
                ()->assertNotNull(loadedStudent),
                ()->assertEquals(loadedStudent.getId(),student.getId()),
                ()->assertEquals(loadedStudent.getFirstName(),student.getFirstName()),
                ()->assertEquals(loadedStudent.getFirstName(),student.getLastName()),
                ()->assertEquals(loadedStudent.getUsername(),student.getUsername()),
                ()->assertEquals(loadedStudent.getPassword(),student.getPassword())
        );
    }

    @Test
    void remove() {

        //arrange
        var student=new Student(null,"firstName","firstName","Username","password");


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
        var student=new Student(null,"firstName","firstName","Username","password");



        //act
        studentRepo.add(student);


        //assert
        student.setFirstName("reza");
        student.setLastName("mohammadi");
        studentRepo.update(student);

        Student loadedStudent=studentRepo.showInfo(student.getId(),student);
        assertAll(
                ()->assertNotNull(loadedStudent),
                ()->assertEquals(loadedStudent.getId(),student.getId()),
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