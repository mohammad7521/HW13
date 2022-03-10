package services;

import entities.Student;
import entities.Student;
import repository.StudentRepo;

public class StudentRepoServices implements BaseServices<Student>{

    private static StudentRepo studentRepo=new StudentRepo();

    @Override
    public Student add(Student student) {
        return studentRepo.add(student);
    }

    @Override
    public Student remove(long id) {

        Student returnedStudent=studentRepo.showInfo(id,Student.class);
        return studentRepo.remove(returnedStudent);
    }

    @Override
    public void update(Student student) {
        studentRepo.update(student);
    }

    @Override
    public Student showInfo(long id) {
        return studentRepo.showInfo(id,Student.class);
    }
}
