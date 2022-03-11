package services;


import entities.Lesson;
import entities.Student;
import entities.Term;
import utils.DuplicateUser;
import repository.StudentRepo;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentRepoServices implements BaseServices<Student>{

    private static StudentRepo studentRepo=new StudentRepo();

    @Override
    public Student add(Student student) {
        if (studentRepo.showInfo(student.getUsername(), Student.class)!=null) {
            return studentRepo.add(student);
        }
        else throw new DuplicateUser("username already exists");
    }

    @Override
    public Student remove(int id) {

        Student returnedStudent=studentRepo.showInfo(id,Student.class);
        return studentRepo.remove(returnedStudent);
    }

    @Override
    public void update(Student student) {
        studentRepo.update(student);
    }

    @Override
    public Student showInfo(int id) {
        return studentRepo.showInfo(id,Student.class);
    }

    @Override
    public List<Student> showAll() {
        return studentRepo.showAll(Student.class);
    }


    public Student showInfo(String username) {
        return studentRepo.showInfo(username,Student.class);
    }


    //student log in
    public boolean logIn(String username,String password){

        boolean logInCheck=false;
        Student student=null;

        student.setUsername(showInfo(username).getUsername());
        student.setPassword(showInfo(username).getPassword());

        if (student.getUsername().equals(username)) {
            if (student.getPassword().equals(password)) {
                logInCheck = true;
            }
        } else logInCheck = false;

        return logInCheck;
    }


    //getting the current term of the student
    public static Term lastTerm (int studentID){
        return studentRepo.getLastTerm(studentID);
    }


    //checking if the student is eligible to select 24 units of lessons.
    public boolean eligible(int studentID) {

        double average;
        Term lastTerm = lastTerm(studentID);
        average = calculateLastTermLessons(studentID);
        if (lastTerm.getTermId() > 1 && average>18 || lastTerm.getTermId()<2) {

                return true;
            } else return false;
    }


    //calculating the average grades of lessons from last term
    public static double calculateLastTermLessons ( int studentID){
        Student student = studentRepo.showInfo(studentID, Student.class);

        //getting last term
        Term lastTerm = lastTerm(studentID);

        //calculating average of lessons of last term
        IntSummaryStatistics stats = lastTerm.getStudent_term_lesson().stream()
                .map(Lesson::getGrade).mapToInt(Integer::intValue).summaryStatistics();

        return stats.getAverage();
    }



        //assign lessons to a term
    public void assignLessons(List<Lesson> lessonList,int studentId){
        studentRepo.assignLessons(lessonList,studentId);
    }
}
