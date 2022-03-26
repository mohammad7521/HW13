//package repository;
//
//import entities.Teacher;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import utils.HibernateSingleton;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TeacherRepoTest {
//
//    private TeacherRepo teacherRepo=new TeacherRepo();
//
//
//    @BeforeAll
//    static void setup(){
//        var sessionFactory= HibernateSingleton.getInstance();
//    }
//
//
//
//    //add and showInfo
//    @Test
//    void addAndShowInfo() {
//
//        //arrange
//        var teacher=new Teacher(null,"firstName","lastName","username",
//                "password",24224,false,null);
//
//        //act
//        teacherRepo.add(teacher);
//
//        //assert
//        Teacher loadedTeacher=teacherRepo.showInfo(teacher.getTeacherID(),Teacher.class);
//        assertAll(
//                ()->assertNotNull(loadedTeacher),
//                ()->assertEquals(loadedTeacher.getTeacherID(),teacher.getTeacherID()),
//                ()->assertEquals(loadedTeacher.getFirstName(),teacher.getFirstName()),
//                ()->assertEquals(loadedTeacher.getLastName(),teacher.getLastName()),
//                ()->assertEquals(loadedTeacher.getUserName(),teacher.getUserName()),
//                ()->assertEquals(loadedTeacher.getPassword(),teacher.getPassword())
//        );
//    }
//
//    @Test
//    void remove() {
//
//        //arrange
//        var teacher=new Teacher(null,"firstName","lastName","username","password",23423,
//                true,null);
//
//
//        //act
//        teacherRepo.add(teacher);
//
//
//        //assert
//        teacherRepo.remove(teacher);
//        List<Teacher> accountList=teacherRepo.showAll(Teacher.class);
//        assertEquals(0,accountList.size());
//    }
//
//    @Test
//    void update() {
//        //arrange
//        var teacher=new Teacher(null,"firstName","lastName","username","password",23423,
//                true,null);
//
//
//        //act
//        teacherRepo.add(teacher);
//
//
//        //assert
//        teacher.setFirstName("reza");
//        teacher.setLastName("mohammadi");
//        teacher.setBoardMember(true);
//        teacher.setSalary(45000000);
//        teacherRepo.update(teacher);
//
//        Teacher loadedTeacher=teacherRepo.showInfo(teacher.getTeacherID(),Teacher.class);
//        assertAll(
//                ()->assertNotNull(loadedTeacher),
//                ()->assertEquals(loadedTeacher.getTeacherID(),teacher.getTeacherID()),
//                ()->assertEquals(loadedTeacher.getUserName(),teacher.getUserName()),
//                ()->assertEquals(loadedTeacher.getFirstName(),teacher.getFirstName()),
//                ()->assertEquals(loadedTeacher.getLastName(),teacher.getLastName()),
//                ()->assertEquals(loadedTeacher.getPassword(),teacher.getPassword()),
//                ()->assertEquals(loadedTeacher.getSalary(),teacher.getSalary())
//        );
//    }
//
//
//    @AfterEach
//    void tearDown() {
//        teacherRepo.hqlTruncate("Teacher");
//    }
//
//
//}