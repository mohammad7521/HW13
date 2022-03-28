package console;


import entities.Lesson;
import entities.LessonTerm;
import entities.Student;
import exceptionHandler.*;
import services.LessonRepoServices;
import services.LessonStudentServices;
import services.LessonTermServices;
import services.StudentRepoServices;

import java.util.*;

public class StudentConsole {

    public static StudentRepoServices studentService = new StudentRepoServices();
    public static LessonRepoServices lessonRepoServices = new LessonRepoServices();
    public static LessonStudentServices lessonStudentServices = new LessonStudentServices();
    public static LessonTermServices lessonTermServices = new LessonTermServices();

    public static void studentLogInMenu() {


        boolean flag = true;
        while (flag) {
            System.out.println("1-Register: ");
            System.out.println("2-Sign in: ");
            System.out.println("0-exit: ");

            Scanner scanner = new Scanner(System.in);

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        System.out.println("enter a username: ");
                        String username = scanner.next();
                        System.out.println("enter a password: ");
                        String password = scanner.next();
                        System.out.println("enter your first name: ");
                        String firstName = scanner.next();
                        System.out.println("enter your last name: ");
                        String lastName = scanner.next();

                        Student student = new Student(null, firstName, lastName, username, password,
                                null);
                        studentService.add(student);
                        System.out.println("sign up successfull");
                        break;
                    case 2:
                        System.out.println("please enter your username: ");
                        username = scanner.next();
                        System.out.println("please enter your password: ");
                        password = scanner.next();
                        if (studentService.logIn(username, password)) {
                            System.out.println("log in successful! ");
                            studentMainMenu(username);
                            break;
                        } else System.out.println("password is wrong! ");
                        break;
                    case 0:
                        MainConsole.mainMenu();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a valid number ! ");
            } catch (NoSuchId e) {
                System.out.println("id does not exist");
            } catch (DuplicateUser e) {
                System.out.println("username already exists!");
            }
        }
    }


    //    student main menu
    public static void studentMainMenu(String username) {

        while (true) {
            System.out.println("1-show info: ");
            System.out.println("2-show passed lessons: ");
            System.out.println("3-select lessons: ");
            System.out.println("0-log out: ");

            Scanner scanner = new Scanner(System.in);
            try {
                Student student = studentService.showInfo(username);
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        System.out.println(student.toString());
                        break;

                    case 2:
                        List<LessonTerm> lessonList = studentService.showAllLessons(student.getStudentId());

                        for (LessonTerm l : lessonList) {
                            System.out.println(l);
                        }
                        break;

                    case 3:

                        int maxUnits;
                        if (studentService.eligible(student.getStudentId())) {
                            maxUnits = 24;
                            System.out.println();
                            System.out.println("you are eligible to select up to 24 units of lessons!");
                            System.out.println();
                        } else {
                            maxUnits = 20;
                            System.out.println();
                            System.out.println("you are eligible to only select up to 20 units of lessons!");
                            System.out.println();
                        }

                        List<LessonTerm> allLessons = studentService.showUnpassed();
                        for (LessonTerm l : allLessons) {
                            System.out.println(l);
                        }

                        try {

                            int units = 0;
                            List<LessonTerm> selectedLessons = new ArrayList<>();
                            scanner.nextLine();
                            System.out.println("do you want to select (y/n):");
                            char answer = scanner.next().toLowerCase(Locale.ROOT).charAt(0);
                            while (answer == ('y')) {

                                System.out.println("select the lesson by id:");
                                LessonTerm l = lessonTermServices.showInfo(scanner.nextInt());
                                selectedLessons.add(l);
                                units += l.getUnit();
                                System.out.println("you have selected " + units + " units!");
                                System.out.println("do you want to add another lesson?(y/n)");
                                answer = scanner.next().charAt(0);
                            }
                            if (units > maxUnits) {
                                System.out.println("sorry you have selected too many units!");
                                System.out.println();
                                break;
                            } else if (units < maxUnits && units > 0) {
                                studentService.assignLessons(student.getStudentId(), selectedLessons);
                                System.out.println("lessons have been added successfully!");
                            } else System.out.println("you didn't select any lessons!");
                            break;

                        } catch (InputMismatchException e) {
                            System.out.println("please enter a valid number");
                            break;
                        } catch (LessonPassed e) {
                            System.out.println("you have passed one of the selected lessons! please check again");
                            break;
                        } catch (NoSuchId e) {
                            System.out.println("lesson Id does not exist!");
                        } catch (DuplicateLessons e) {
                            System.out.println("you have selected a lesson twice! please check again");
                        }
                    case 0:
                        studentLogInMenu();

                }
            } catch (InputMismatchException e) {
                System.out.println("pleas enter a valid number");
            } catch (GradeNotSet e) {
                System.out.println("the grade for your last term's lessons has not been set! please ask the teacher to set your grade!");
            } catch (NoLessonRemaining e) {
                System.out.println("lessons for the new term have not been added to the system! please wait or call the education staff");
            }
        }
    }
}
