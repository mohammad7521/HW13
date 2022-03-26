package console;

import entities.*;
import exceptionHandler.DuplicateLessons;
import exceptionHandler.DuplicateUser;
import services.*;
import exceptionHandler.NoSuchId;
import exceptionHandler.WrongUnits;

import java.util.*;

public class StaffConsole {


    public static StaffRepoServices educationStaffService = new StaffRepoServices();
    public static StudentRepoServices studentService = new StudentRepoServices();
    public static TeacherRepoServices teacherService = new TeacherRepoServices();
    public static LessonRepoServices lessonService = new LessonRepoServices();
    public static MasterTermServices masterTermServices=new MasterTermServices();

    public static void educationStaffMenu() {

        boolean flag = true;
        while (flag) {
            System.out.println("1-employee management: ");
            System.out.println("2-Student management: ");
            System.out.println("3-Teacher management: ");
            System.out.println("4-Lesson management: ");
            System.out.println("5-term management: ");
            System.out.println("0-exit: ");

            Scanner scanner = new Scanner(System.in);

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        employeeManagement();
                        break;
                    case 2:
                        studentManagementMenu();
                        break;
                    case 3:
                        teacherManagementMenu();
                        break;
                    case 4:
                        lessonManagementMenu();
                        break;
                    case 5:
                        termManagementMenu();
                    case 0:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a valid number ! ");
            }
        }
    }


    //employee management
    public static void employeeManagement() {

        System.out.println("1-add new employee");
        System.out.println("2-remove employee");
        System.out.println("3-modify employee");
        System.out.println("0-exit");

        Scanner scanner = new Scanner(System.in);

        try {
            switch (scanner.nextInt()) {

                case 1:
                    System.out.println("enter name:");
                    String name = scanner.next();
                    System.out.println("enter last name:");
                    String lastName = scanner.next();
                    System.out.println("enter username:");
                    String username = scanner.next();
                    educationStaffService.checkUsername(username);
                    System.out.println("enter password:");
                    String password = scanner.next();
                    System.out.println("enter salary:");
                    long salary = scanner.nextLong();
                    Staff staff = new Staff(null, name, lastName, username, password, salary);
                    educationStaffService.add(staff);
                    System.out.println("user added successfully! ");
                    System.out.println();

                    break;

                case 2:
                    System.out.println("enter the id of the staff that you want to remove:");
                    int id = scanner.nextInt();
                    educationStaffService.remove(id);
                    System.out.println("user removed successfully");
                    break;

                case 3:
                    System.out.println("enter the id of the staff that you want to change:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("enter new name:");
                    name = scanner.next();
                    System.out.println("enter new last name:");
                    lastName = scanner.next();
                    System.out.println("enter new username:");
                    username = scanner.next();
                    System.out.println("enter new password:");
                    password = scanner.next();
                    System.out.println("enter new salary:");
                    salary = scanner.nextLong();
                    educationStaffService.checkUsername(username);
                    Staff modifiedStaff = educationStaffService.showInfo(id);
                    modifiedStaff.setFirstName(name);
                    modifiedStaff.setLastName(lastName);
                    modifiedStaff.setPassword(password);
                    modifiedStaff.setSalary(salary);

                    educationStaffService.update(modifiedStaff);
                    System.out.println("update successful!");
                    break;
                case 4:
                    System.out.println("enter the id of the employee:");
                    id = scanner.nextInt();

                    Staff loadedStaff = educationStaffService.showInfo(id);

                    System.out.println();
                    System.out.println();
                    System.out.println("the salary is:");
                    System.out.println(loadedStaff.getSalary());
                    break;

                case 0:
                    educationStaffMenu();
                    break;
            }
        } catch (DuplicateUser e) {
            System.out.println("username already exists!");
        } catch (NoSuchId e) {
            System.out.println("id does not exist!");
        }
    }


    public static void studentManagementMenu() {
        System.out.println("1-add a new student: ");
        System.out.println("2-remove a student: ");
        System.out.println("3-update an student: ");

        Scanner scanner = new Scanner(System.in);
        int userEntry = scanner.nextInt();

        try {
            switch (userEntry) {
                case 1:
                    System.out.println("enter first name: ");
                    String firstname = scanner.next();
                    System.out.println("enter last name:");
                    String lastName = scanner.next();
                    System.out.println("enter username:");
                    String username = scanner.next();
                    System.out.println("enter password:");
                    String password = scanner.next();
                    Student student = new Student(null, firstname, lastName, username, password, null);

                    studentService.add(student);
                    System.out.println("student added successfully");

                    break;

                case 2:
                    System.out.println("enter student ID: ");
                    int studentID = scanner.nextInt();

                    studentService.remove(studentID);
                    System.out.println("student removed successfully!");
                    break;
                case 3:
                    System.out.println("enter student ID: ");
                    studentID = scanner.nextInt();
                    System.out.println("enter new first name: ");
                    firstname = scanner.next();
                    System.out.println("enter new last name:");
                    lastName = scanner.next();
                    System.out.println("enter new username:");
                    username = scanner.next();
                    System.out.println("enter new password:");
                    password = scanner.next();
                    student = studentService.showInfo(studentID);
                    student.setFirstName(firstname);
                    student.setLastName(lastName);
                    student.setUsername(username);
                    student.setPassword(password);
                    studentService.update(student);
                    System.out.println("update successful");
                    break;
            }
        } catch (DuplicateUser e) {
            System.out.println("Username already exists! ");
            System.out.println();
        } catch (NoSuchId e) {
            System.out.println("user id does not exist! ");
        }
    }


    //teacher management
    public static void teacherManagementMenu() {
        System.out.println("1-add a new teacher: ");
        System.out.println("2-remove a teacher: ");
        System.out.println("3-update a teacher: ");
        System.out.println("4-assign lessons to a teacher: ");
        Scanner scanner = new Scanner(System.in);
        int userEntry = scanner.nextInt();

        try {
            switch (userEntry) {
                case 1:
                    System.out.println("enter first name: ");
                    String firstname = scanner.next();
                    System.out.println("enter last name:");
                    String lastName = scanner.next();
                    System.out.println("enter username:");
                    String username = scanner.next();
                    System.out.println("enter password:");
                    String password = scanner.next();
                    System.out.println("are they a board member?(Y for yes)");
                    Boolean isBoardMember = false;
                    if (scanner.next().toLowerCase(Locale.ROOT).charAt(0) == 'y') {
                        isBoardMember = true;
                    }
                    System.out.println("how much is their fixed salary?");
                    long fixedSalary = scanner.nextLong();
                    Teacher teacher = new Teacher(null, firstname, lastName, username, password, fixedSalary,
                            isBoardMember, null);

                    teacherService.add(teacher);
                    System.out.println("teacher added successfully!");
                    break;

                case 2:
                    System.out.println("enter teacher ID: ");
                    int teacherID = scanner.nextInt();
                    teacherService.remove(teacherID);
                    System.out.println("removed successfully!");
                    break;

                case 3:
                    System.out.println("enter teacher ID: ");
                    teacherID = scanner.nextInt();
                    System.out.println("enter new first name: ");
                    firstname = scanner.next();
                    System.out.println("enter new last name:");
                    lastName = scanner.next();
                    System.out.println("enter new username:");
                    username = scanner.next();
                    System.out.println("enter new password:");
                    password = scanner.next();
                    scanner.nextLine();
                    isBoardMember = false;
                    System.out.println("are they a board member?(Y for true) ");
                    if (scanner.nextLine().toLowerCase(Locale.ROOT).charAt(0) == 'Y') {
                        isBoardMember = true;
                    }
                    System.out.println("how much is their fixed salary?");
                    fixedSalary = scanner.nextLong();
                    teacher = teacherService.showInfo(teacherID);
                    teacher.setFirstName(firstname);
                    teacher.setLastName(lastName);
                    teacher.setUsername(username);
                    teacher.setPassword(password);
                    teacher.setBoardMember(isBoardMember);
                    teacher.setSalary(fixedSalary);
                    teacherService.update(teacher);
                    System.out.println("update successful!");
                    break;

                case 4:

                    try {
                        System.out.println("select the teacher by Id");
                        List<Teacher> teacherList = teacherService.showAll();

                        for (Teacher t : teacherList) {
                            System.out.println(t);
                        }

                        int teacherId = scanner.nextInt();
                        Teacher selectedTeacher = teacherService.showInfo(teacherId);

                        List<Lesson> lessonList = lessonService.showAll();

                        for (Lesson l : lessonList) {
                            System.out.println(l);
                        }

                        int units = 0;
                        List<Lesson> selectedLessons = new ArrayList<>();
                        scanner.nextLine();
                        System.out.println("do you want to select (y/n):");
                        char answer = scanner.next().toLowerCase(Locale.ROOT).charAt(0);
                        while (answer == ('y')) {

                            System.out.println("select the lesson by id:");
                            Lesson l = lessonService.showInfo(scanner.nextInt());
                            selectedLessons.add(l);
                            units += l.getUnit();
                            System.out.println("you have selected " + units + " units!");
                            System.out.println("do you want to add another lesson?(y/n)");
                            answer = scanner.next().charAt(0);
                        }
                        teacherService.assignLessons(selectedTeacher.getTeacherID(), selectedLessons);
                        System.out.println("lessons have been assigned successfully!");
                    }catch (DuplicateLessons e){
                        System.out.println("you have selected a lesson twice! please check again");
                    }
            }
        } catch (NoSuchId e) {
            System.out.println("user id does not exist!");
        } catch (DuplicateUser e) {
            System.out.println("username already exists!");
        }
    }


    //lesson management
    public static void lessonManagementMenu() {
        System.out.println("1-add a new lesson: ");
        System.out.println("2-remove a lesson: ");
        System.out.println("3-update a lesson: ");
        System.out.println("0-exit: ");

        Scanner scanner = new Scanner(System.in);
        int userEntry = scanner.nextInt();

        try {
            switch (userEntry) {
                case 1:
                    System.out.println("enter lesson name: ");
                    String lessonName = scanner.nextLine();
                    System.out.println("enter lesson units:");
                    int units = scanner.nextInt();
                    Lesson lesson = new Lesson(null, lessonName, units);

                    lessonService.add(lesson);
                    break;

                case 2:
                    System.out.println("enter lesson ID: ");
                    int lessonID = scanner.nextInt();


                    if (lessonService.showInfo(lessonID) != null) {
                        lessonService.remove(lessonID);
                        System.out.println("removed successfully");
                        break;
                    } else System.out.println("lesson does not exists! ");
                    break;
                case 3:
                    System.out.println("enter the lesson ID: ");
                    lessonID = scanner.nextInt();
                    System.out.println("enter new lesson name: ");
                    lessonName = scanner.next();
                    System.out.println("enter new lesson Units: ");
                    int lessonUnits = scanner.nextInt();
                    lesson = lessonService.showInfo(lessonID);
                    lesson.setName(lessonName);
                    lesson.setUnit(lessonUnits);
                    lessonService.update(lesson);
                    System.out.println("update successful");
                    break;

                case 0:
                    break;
            }
        } catch (NoSuchId e) {
            System.out.println("lesson does not exist!");
        } catch (WrongUnits e) {
            System.out.println("unit values must be between 1 and 4");
        }

    }


    //term management menu:returns term id
    public static void termManagementMenu() {
        System.out.println("1-show current term:");
        System.out.println("2-add new term: ");
        Scanner scanner = new Scanner(System.in);
        int userSelect = scanner.nextInt();


        try {
            switch (userSelect) {

                case 1:

                    System.out.println(masterTermServices.getLastTerm());
                    break;

                case 2:
                    Term term = new Term(null, null, null);
                    masterTermServices.addTerm(term);
                    System.out.println(term);
                    System.out.println("new term has been set");
                    break;


            }
        }catch (InputMismatchException e){
            System.out.println("please enter a valid number!");
        }
    }

}




