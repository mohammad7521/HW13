package services;


import entities.*;
import exceptionHandler.*;
import repository.StudentRepo;

import java.util.ArrayList;
import java.util.List;

public class StudentRepoServices implements BaseServices<Student> {

    private static StudentRepo studentRepo = new StudentRepo();
    private static MasterTermServices masterTermServices = new MasterTermServices();
    private static LessonTermServices lts=new LessonTermServices();

    @Override
    public Student add(Student student) {

        if (checkUsername(student.getUsername())) {
            throw new DuplicateUser("username already exists!");
        }
        return studentRepo.add(student);
    }

    @Override
    public Student remove(int id) {

        if (!checkId(id)) {
            throw new NoSuchId();
        }
        Student returnedStudent = studentRepo.showInfo(Student.class, id);
        return studentRepo.remove(returnedStudent);
    }

    @Override
    public void update(Student student) {
        if (!checkId(student.getStudentId())) {
            throw new NoSuchId();
        }
        studentRepo.update(student);
    }

    @Override
    public Student showInfo(int id) {
        if (!checkId(id)) {
            throw new NoSuchId();
        }
        return studentRepo.showInfo(Student.class, id);
    }

    @Override
    public List<Student> showAll() {
        return studentRepo.showAll(Student.class);
    }

    @Override
    public Student showInfo(String username) {

        if (!checkUsername(username)) {
            throw new NoSuchId();
        }
        return studentRepo.showInfo(Student.class, username);
    }


    //student log in
    public boolean logIn(String username, String password) {

        boolean logInCheck = false;
        Student student = showInfo(username);

        if (student.getPassword().equals(password)) {
            logInCheck = true;
        }
        return logInCheck;
    }


    public boolean checkUsername(String username) {

        boolean flag = false;
        List<Student> studentList = showAll();

        for (Student a : studentList) {
            if (username.equals(a.getUsername())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //checking if the id exists in order to handle null pointer exception
//of nat having an entity with such id
    public boolean checkId(int Id) {

        boolean flag = false;
        List<Student> studentList = showAll();

        for (Student s : studentList) {
            if (s.getStudentId() == Id) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    //show lessons from previous terms
    public List<LessonTerm> showAllLessons(int studentId) {
        return studentRepo.showAllLessons(studentId);
    }


    public StudentTerm lastTerm(int studentId) {
        return studentRepo.lastTerm(studentId);
    }


    public List<LessonTerm> lastTermLessons(StudentTerm st) {

        if (st!=null)
        return studentRepo.lastTermLessons(st.getId());
        else return null;

    }


    public int calcAverage(List<LessonTerm> lessonList) {
        int gradeSum = 0;

        for (LessonTerm l : lessonList) {

            if (l.getGrade() > 0) {
                gradeSum += l.getGrade();
            } else throw new GradeNotSet();
        }
        int average = gradeSum / lessonList.size();

        return average;
    }


    public boolean eligible(int studentId) {

        List<LessonTerm> lastTermLessons=lastTermLessons(lastTerm(studentId));
        boolean eligible;
        if (lastTerm(studentId)==null ||lastTermLessons==null) {
            eligible = true;
        } else if (calcAverage(lastTermLessons(lastTerm(studentId))) >= 18) {
            eligible = true;
        } else eligible = false;

        return eligible;
    }


    public void assignLessons(int studentId, List<LessonTerm> lessonList) {


        if (lessonList.stream().distinct().count() < lessonList.size()) {
            throw new DuplicateLessons();
        }
        for (LessonTerm ls : lessonList) {
            if (ls.isPassed() == true) {
                throw new LessonPassed();
            }
        }

        for (LessonTerm lt:lessonList){

        }


        Student student = showInfo(studentId);
        StudentTerm st = new StudentTerm(null, student, null, null);
        Term lastMasterTerm = masterTermServices.getLastTerm();
        studentRepo.assignLessons(lessonList, st, lastMasterTerm);
        masterTermServices.assignStudentTerm(st);
    }

    public List<LessonTerm> showUnpassed(){

        List<LessonTerm> lt=lts.showAll();
        List<LessonTerm>unpassedLessonTerm=new ArrayList<>();
        for (LessonTerm l:lt){
            if (l.getStudentTermLesson()==null)
                unpassedLessonTerm.add(l);
        }
        if (unpassedLessonTerm.size()==0)
            throw new NoLessonRemaining();
        else return unpassedLessonTerm;

    }
}
