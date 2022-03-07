package repository;

import entities.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.HibernateSingleton;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherRepoTest {

    private TeacherRepo teacherRepo=new TeacherRepo();


    @BeforeAll
    static void setup(){
        var sessionFactory= HibernateSingleton.getInstance();
    }



    //add and showInfo
    @Test
    void addAndShowInfo() {

        //arrange
        var teacher=new Teacher(null,"firstName","firstName","Username","password",50000000,false);

        //act
        teacherRepo.add(teacher);

        //assert
        Teacher loadedTeacher=teacherRepo.showInfo(teacher.getId(),teacher);
        assertAll(
                ()->assertNotNull(loadedTeacher),
                ()->assertEquals(loadedTeacher.getId(),teacher.getId()),
                ()->assertEquals(loadedTeacher.getFirstName(),teacher.getFirstName()),
                ()->assertEquals(loadedTeacher.getLastName(),teacher.getLastName()),
                ()->assertEquals(loadedTeacher.getUsername(),teacher.getUsername()),
                ()->assertEquals(loadedTeacher.getPassword(),teacher.getPassword())
        );
    }

    @Test
    void remove() {

        //arrange
        var teacher=new Teacher(null,"firstName","firstName","Username","password",50000000,true);


        //act
        teacherRepo.add(teacher);


        //assert
        teacherRepo.remove(teacher);
        List<Teacher> accountList=teacherRepo.showAll(teacher);
        assertEquals(0,accountList.size());
    }

    @Test
    void update() {
        //arrange
        var teacher=new Teacher(null,"firstName","firstName","Username","password",50000000,false);



        //act
        teacherRepo.add(teacher);


        //assert
        teacher.setFirstName("reza");
        teacher.setLastName("mohammadi");
        teacher.setBoardMember(true);
        teacher.setSalary(45000000);
        teacherRepo.update(teacher);

        Teacher loadedTeacher=teacherRepo.showInfo(teacher.getId(),teacher);
        assertAll(
                ()->assertNotNull(loadedTeacher),
                ()->assertEquals(loadedTeacher.getId(),teacher.getId()),
                ()->assertEquals(loadedTeacher.getUsername(),teacher.getUsername()),
                ()->assertEquals(loadedTeacher.getFirstName(),teacher.getFirstName()),
                ()->assertEquals(loadedTeacher.getLastName(),teacher.getLastName()),
                ()->assertEquals(loadedTeacher.getPassword(),teacher.getPassword()),
                ()->assertEquals(loadedTeacher.getSalary(),teacher.getSalary())
        );
    }


    @AfterEach
    void tearDown() {
        teacherRepo.hqlTruncate("Teacher");
    }


}