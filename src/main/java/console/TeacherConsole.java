package console;

import entities.Lesson;
import entities.LessonTerm;
import entities.Teacher;
import exceptionHandler.InvalidGrade;
import exceptionHandler.NoSuchId;
import repository.LessonTermRepo;
import services.LessonTermServices;
import services.TeacherRepoServices;

import java.sql.Date;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TeacherConsole {


    public static TeacherRepoServices teacherServices = new TeacherRepoServices();
    public static LessonTermServices lessonTermServices=new LessonTermServices();

    public static void teacherLogInMenu() {

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        try {
            while (flag) {
                System.out.println("1-sign in: ");
                System.out.println("0-exit: ");

                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        System.out.println("enter your username:");
                        String username = scanner.next();
                        System.out.println("enter your password:");
                        String password = scanner.next();
                        if (teacherServices.logIn(username, password)) {
                            System.out.println("log in successful! ");
                            teacherMainMenu(username);
                        } else System.out.println("password is wrong! ");
                        break;

                    case 0:
                        MainConsole.mainMenu();
                }
            }
        }catch (NoSuchId e){
            System.out.println("user id does not exist!");
        }
    }


    public static void teacherMainMenu(String username) {

        while (true) {
            System.out.println("1-show info: ");
            System.out.println("2-submit student scores: ");
            System.out.println("3-show paycheck: ");
            System.out.println("0-exit: ");

            Teacher teacher=teacherServices.showInfo(username);
            Scanner scanner = new Scanner(System.in);

            try {
                int userEnter = scanner.nextInt();
                switch (userEnter) {
                    case 1:
                        System.out.println(teacher);
                        break;

                    case 2:
                        List<LessonTerm> lessonTerms=teacherServices.showUnSubmittedLessons(teacher.getTeacherID());

                        for (LessonTerm lt:lessonTerms){
                            System.out.println(lt);
                        }
                        System.out.println("select the lesson by Id");
                        int lessonId=scanner.nextInt();
                        LessonTerm lt=lessonTermServices.showInfo(lessonId);
                        System.out.println("enter grade (between 1 and 20):");
                        int grade=scanner.nextInt();
                        teacherServices.setGrades(lt,grade);
                        System.out.println("score submitted successfully!");
                        break;

                    case 3:
                        System.out.println("your salary is: ");
                        System.out.println(teacherServices.showInfo(teacher.getTeacherID()).getSalary());
                        break;

                    case 0:
                        teacherLogInMenu();
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("pleas enter a valid number");
            } catch (NoSuchId e){
                System.out.println("id does not exist!");
            } catch (InvalidGrade e){
                System.out.println("please enter a grade between 1 and 20");
            }
        }
    }
}
