package services;

import entities.Teacher;
import repository.TeacherRepo;

public class TeacherRepoServices implements BaseServices<Teacher>{


    private static TeacherRepo teacherRepo=new TeacherRepo();

    @Override
    public Teacher add(Teacher teacher) {
        return teacherRepo.add(teacher);
    }

    @Override
    public Teacher remove(long id) {

        Teacher returnedTeacher=teacherRepo.showInfo(id,Teacher.class);
        return teacherRepo.remove(returnedTeacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherRepo.update(teacher);
    }

    @Override
    public Teacher showInfo(long id) {
        return teacherRepo.showInfo(id,Teacher.class);
    }

}
