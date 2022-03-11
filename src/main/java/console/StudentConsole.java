package console;


import entities.Lesson;
import entities.Student;
import services.LessonRepoServices;
import services.StudentRepoServices;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentConsole {

    public static StudentRepoServices studentService=new StudentRepoServices();
    public static LessonRepoServices lessonRepoServices=new LessonRepoServices();

    public static void studentLogInMenu() {


        boolean flag = true;
        while (flag) {
            System.out.println("1-Register: ");
            System.out.println("2-Sign in: ");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        System.out.println("enter a username: ");
                        String username=scanner.next();
                        System.out.println("enter a password: ");
                        String password=scanner.next();
                        System.out.println("enter your first name: ");
                        String firstName=scanner.next();
                        System.out.println("enter your last name: ");
                        String lastName=scanner.next();

                        Student student=new Student(null,firstName,lastName,username,password,
                                null);
                        try {
                            studentService.add(student);
                        }catch (utils.DuplicateUser e){
                            System.out.println("username already exists! ");
                        }
                        break;
                    case 2:
                        System.out.println("please enter your username: ");
                        username=scanner.next();
                        System.out.println("please enter your password: ");
                        password=scanner.next();
                        try {
                            if (studentService.logIn(username, password)) {
                                System.out.println("log in successful! ");
                                studentMainMenu(username);
                                break;
                            } else System.out.println("password is wrong! ");
                            break;
                        }catch (NullPointerException e){
                            System.out.println("username does not exist! ");
                        }
                    case 0:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a valid number ! ");
            }
        }
    }



//    student main menu
    public static void studentMainMenu(String username) {

        while (true) {
        System.out.println("1-show info: ");
        System.out.println("2-select lessons: ");

        Scanner scanner = new Scanner(System.in);
            try {
                Student student = studentService.showInfo(username);
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        student.toString();
                        break;

                    case 2:

                        int maxUnits=0;
                        if (studentService.eligible(student.getStudentId())) {
                            maxUnits=24;
                            System.out.println("you are eligible to select up to 24 units of lessons!");
                        }else maxUnits=20;
                            System.out.println("you are eligible to only select up to 20 units of lessons!");

                        List<Lesson> lessonList = lessonRepoServices.showAll();
                        lessonList.toString();

                        try {

                            int units=0;
                            List<Lesson> selectedLessons=new ArrayList<>();

                            System.out.println("do you want to select (y/n):");
                            String answer=scanner.nextLine();
                            while(answer.equalsIgnoreCase("y")){

                                System.out.println("select the lesson by id:");
                                Lesson l=lessonRepoServices.showInfo(scanner.nextInt());
                                selectedLessons.add(l);
                                units+=l.getUnit();
                                System.out.println("you have selected "+units+" units!");
                                System.out.println("do you want to add another lesson?(y/n)");
                                answer=scanner.nextLine();
                            }
                            if (units>maxUnits){
                                System.out.println("sorry you have selected too many units!");
                                System.out.println();
                                break;
                            }else {

                                studentService.assignLessons(selectedLessons, student.getStudentId());
                                System.out.println("lessons have been added successfully!");
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("please enter a valid number");
                            break;
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("pleas enter a valid number");
            }
        }
    }
}
