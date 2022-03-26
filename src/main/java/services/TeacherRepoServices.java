package services;


import entities.*;
import exceptionHandler.*;
import repository.TeacherRepo;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepoServices implements BaseServices<Teacher>{


    private static TeacherRepo teacherRepo=new TeacherRepo();
    private static MasterTermServices masterTermServices=new MasterTermServices();
    private static LessonTermServices lessonTermServices=new LessonTermServices();

    @Override
    public Teacher add(Teacher teacher) {
        if (checkUsername(teacher.getUsername())){
            throw new DuplicateUser("username already exists!");
        }
        return teacherRepo.add(teacher);
    }

    @Override
    public Teacher remove(int id) {

        if (!checkId(id)){
            throw new NoSuchId();
        }
        Teacher returnedTeacher=showInfo(id);
        return teacherRepo.remove(returnedTeacher);
    }


    @Override
    public void update(Teacher teacher) {

        if (!checkId(teacher.getTeacherID())){
            throw new NoSuchId();
        }
        teacherRepo.update(teacher);
    }

    @Override
    public Teacher showInfo(String username) {

        if (!checkUsername(username)){
            throw new NoSuchId();
        }
        return teacherRepo.showInfo(Teacher.class,username);
    }

    @Override
    public Teacher showInfo(int id) {

        if (!checkId(id)){
            throw new NoSuchId();
        }
        return teacherRepo.showInfo(Teacher.class,id);
    }


    @Override
    public List<Teacher> showAll() {
        return teacherRepo.showAll(Teacher.class);
    }



    public boolean checkUsername(String username){

        boolean flag=false;
        List<Teacher> teacherList=showAll();

        for (Teacher a:teacherList){
            if (username.equals(a.getUsername())){
                flag=true;
                break;
            }
        }
        return flag;
    }

    //checking if the id exists in order to handle null pointer exception
//of nat having an entity with such id
    public boolean checkId(int Id){

        boolean flag=false;
        List<Teacher> teacherList=showAll();

        for (Teacher s:teacherList){
            if (s.getTeacherID()==Id){
                flag=true;
                break;
            }
        }
        return flag;
    }


    public boolean logIn(String username, String password) {

        boolean logInCheck = false;
        Teacher teacher = showInfo(username);

        if (teacher.getPassword().equals(password)) {
            logInCheck = true;
        }
        return logInCheck;
    }




    public void assignLessons(int teacherId, List<Lesson> lessonList) {


        List<LessonTerm> assignedLessons = new ArrayList<>();

        for (int i=1;i<lessonList.size();i++){
            if (lessonList.get(i).getName().equals(lessonList.get(i-1).getName())){
                throw new DuplicateLessons();
            }
        }


        for (Lesson l : lessonList) {
            LessonTerm lt = new LessonTerm();
            lt.setLessonId(l.getLessonId());
            lt.setName(l.getName());
            lt.setUnit(l.getUnit());
            assignedLessons.add(lt);
        }

        Teacher teacher = showInfo(teacherId);
        TeacherTerm tt = new TeacherTerm(null, teacher, null, null);
        Term lastMasterTerm = masterTermServices.getLastTerm();
        teacherRepo.assignLessons(teacher, assignedLessons, tt, lastMasterTerm);
        masterTermServices.assignTeacherTerm(tt);
        setTeachingSalary(teacherId);
    }


    public List<LessonTerm> showUnSubmittedLessons(int teacherId){
        return teacherRepo.showUnSubmittedLessons(teacherId);
    }


    public void setGrades(LessonTerm lt,int grade){

        if (grade>20 || grade<1){
            throw new InvalidGrade();
        }
        lt.setGrade(grade);
        if (grade>=10){
            lt.setPassed(true);
        }
        lessonTermServices.update(lt);
    }


    public TeacherTerm lastTerm(int teacherId){
        return teacherRepo.lastTerm(teacherId);
    }


    public List<LessonTerm> lastTermLessons(TeacherTerm tt){

        return teacherRepo.lastTermLessons(tt.getId());
    }


    public int calcTeachingSalary(List<LessonTerm> lt){
        int unit=0;

        for (LessonTerm l:lt){
            unit+=l.getUnit();
        }
        return unit*1000000;
    }


    public void setTeachingSalary(int teacherId){

        int teachingSalary=calcTeachingSalary(lastTermLessons(lastTerm(teacherId)));
        Teacher teacher=showInfo(teacherId);

        if (teacher.isBoardMember()) {
            teacher.setSalary(teacher.getSalary() + teachingSalary);
            teacherRepo.update(teacher);
        }
    }


}
