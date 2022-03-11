package services;


import entities.Teacher;
import utils.DuplicateUser;
import repository.TeacherRepo;

import java.util.List;

public class TeacherRepoServices implements BaseServices<Teacher>{


    private static TeacherRepo teacherRepo=new TeacherRepo();

    @Override
    public Teacher add(Teacher teacher) {
        return teacherRepo.add(teacher);
    }

    @Override
    public Teacher remove(int id) {

        Teacher returnedTeacher=teacherRepo.showInfo(id,Teacher.class);
        return teacherRepo.remove(returnedTeacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherRepo.update(teacher);
    }

    @Override
    public Teacher showInfo(int id) {
        return teacherRepo.showInfo(id,Teacher.class);
    }

    @Override
    public List<Teacher> showAll() {
        return teacherRepo.showAll(Teacher.class);
    }


    public Teacher showInfo(String username) {
        return null;
    }

}
