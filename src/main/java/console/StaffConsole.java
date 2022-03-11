package console;

import entities.Lesson;
import entities.Staff;
import entities.Student;
import entities.Teacher;
import utils.DuplicateUser;
import services.LessonRepoServices;
import services.StaffRepoServices;
import services.StudentRepoServices;
import services.TeacherRepoServices;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StaffConsole {


    public static StaffRepoServices educationStaffService=new StaffRepoServices();
    public static StudentRepoServices studentService=new StudentRepoServices();
    public static TeacherRepoServices teacherService=new TeacherRepoServices();
    public static LessonRepoServices lessonService=new LessonRepoServices();

    public static void educationStaffMenu() {

        boolean flag = true;
        while (flag) {
            System.out.println("1-employee management: ");
            System.out.println("2-Student management: ");
            System.out.println("3-Teacher management: ");
            System.out.println("4-Lesson management: ");
            System.out.println("0-exit");

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
//                    case 4:
//                        lessonManagementMenu();
//                        break;
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
    public static void employeeManagement(){

        System.out.println("1-add new employee");
        System.out.println("2-remove employee");
        System.out.println("3-modify employee");
        System.out.println("4-show salary");

        Scanner scanner=new Scanner(System.in);

        switch (scanner.nextInt()){

            case 1:
                System.out.println("enter name:");
                String name=scanner.next();
                System.out.println("enter last name:");
                String lastName=scanner.next();
                System.out.println("enter username:");
                String username=scanner.next();
                System.out.println("enter password:");
                String password=scanner.next();
                System.out.println("enter salary:");
                long salary=scanner.nextInt();

                Staff staff=new Staff(null,name,lastName,username,password,salary);
                try {
                    educationStaffService.add(staff);
                        System.out.println("user added successfully! ");
                        System.out.println();
                    } catch (DuplicateUser e){
                    System.out.println("Username already exists! ");
                    System.out.println();
                }
                break;

            case 2:
                System.out.println("enter the id of the staff that you want to remove:");
                int id=scanner.nextInt();
                educationStaffService.remove(id);
                System.out.println("user removed successfully");
                break;

            case 3:
                System.out.println("enter the id of the staff that you want to change:");
                id=scanner.nextInt();

                System.out.println("enter new name:");
                name=scanner.next();
                System.out.println("enter new last name:");
                lastName=scanner.next();
                System.out.println("enter new username:");
                username=scanner.next();
                System.out.println("enter new password:");
                password=scanner.next();
                System.out.println("enter new salary:");
                salary=scanner.nextLong();

                Staff modifiedStaff=new Staff(null,name,lastName,username,password,salary);

                if (educationStaffService.showInfo(id)!=null){
                    educationStaffService.update(modifiedStaff);
                }else System.out.println("id does not exist");
                break;

            case 4:
                System.out.println("enter the id of the employee:");
                id=scanner.nextInt();

                Staff loadedStaff=educationStaffService.showInfo(id);

                System.out.println();
                System.out.println();
                System.out.println("the salary is:");
                System.out.println(loadedStaff.getSalary());

        }
    }




    public static void studentManagementMenu(){
        System.out.println("1-add a new student: ");
        System.out.println("2-remove a student: ");
        System.out.println("3-update an student: ");

        Scanner scanner=new Scanner(System.in);
        int userEntry=scanner.nextInt();

        switch (userEntry){
            case 1:
                System.out.println("enter first name: ");
                String firstname=scanner.next();
                System.out.println("enter last name:");
                String lastName=scanner.next();
                System.out.println("enter username:");
                String username=scanner.next();
                System.out.println("enter password:");
                String password=scanner.next();
                Student student=new Student(null,firstname,lastName,username,password,null);

                try {
                    studentService.add(student);

                }catch (DuplicateUser e){
                    System.out.println("Username already exists! ");
                    System.out.println();
                }
                break;

            case 2:
                System.out.println("enter student ID: ");
                int studentID=scanner.nextInt();

                if (studentService.showInfo(studentID)!=null){
                    studentService.remove(studentID);
                    System.out.println("removed successfully");
                }else System.out.println("student ID does not exist! ");
                break;

            case 3:
                System.out.println("enter student ID: ");
                studentID=scanner.nextInt();
                System.out.println("enter new first name: ");
                 firstname=scanner.next();
                System.out.println("enter new last name:");
                 lastName=scanner.next();
                System.out.println("enter new username:");
                 username=scanner.next();
                System.out.println("enter new password:");
                 password=scanner.next();
                 student=new Student(null,firstname,lastName,username,password,null);

                 if (studentService.showInfo(studentID)!=null){
                     studentService.update(student);
                     System.out.println("update successful");
                 }
                 else System.out.println("student ID does not exist! ");
                break;
        }
    }




    //teacher management
    public static void teacherManagementMenu(){
        System.out.println("1-add a new teacher: ");
        System.out.println("2-remove a teacher: ");
        System.out.println("3-update an teacher: ");

        Scanner scanner=new Scanner(System.in);
        int userEntry=scanner.nextInt();

        switch (userEntry){
            case 1:
                System.out.println("enter first name: ");
                String firstname=scanner.next();
                System.out.println("enter last name:");
                String lastName=scanner.next();
                System.out.println("enter username:");
                String username=scanner.next();
                System.out.println("enter password:");
                String password=scanner.next();
                System.out.println("are they a board member?");
                Boolean isBoardMember=scanner.nextBoolean();
                System.out.println("how much is their fixed salary?");
                long fixedSalary=scanner.nextLong();
                Teacher teacher=new Teacher(null,firstname,lastName,username,password,fixedSalary,
                        isBoardMember,null);

                try {
                    teacherService.add(teacher);
                }catch (DuplicateUser e){
                    System.out.println("Username already exists! ");

                }
                break;

            case 2:
                System.out.println("enter teacher ID: ");
                int teacherID=scanner.nextInt();

                if (teacherService.showInfo(teacherID)!=null){
                    teacherService.remove(teacherID);
                    System.out.println("removed successfully!");
                    break;
                }else System.out.println("teacher ID does not exist! ");
                break;

            case 3:
                System.out.println("enter teacher ID: ");
                teacherID=scanner.nextInt();
                System.out.println("enter new first name: ");
                firstname=scanner.next();
                System.out.println("enter new last name:");
                lastName=scanner.next();
                System.out.println("enter new username:");
                username=scanner.next();
                System.out.println("enter new password:");
                password=scanner.next();
                System.out.println("are they a board member? ");
                isBoardMember=scanner.nextBoolean();
                fixedSalary=scanner.nextLong();
                teacher=new Teacher(null,firstname,lastName,username,password,fixedSalary,
                        isBoardMember,null);

                if (teacherService.showInfo(teacherID)!=null){
                    teacherService.update(teacher);
                    System.out.println("update successful!");
                    System.out.println();
                }
                else System.out.println("teacher ID does not exist! ");
                break;
        }
    }




    //lesson management
    public static void lessonManagementMenu(){
        System.out.println("1-add a new lesson: ");
        System.out.println("2-remove a lesson: ");
        System.out.println("3-update a lesson: ");

        Scanner scanner=new Scanner(System.in);
        int userEntry=scanner.nextInt();

        switch (userEntry){
            case 1:
                System.out.println("enter lesson name: ");
                String lessonName=scanner.next();
                System.out.println("enter lesson units:");
                int units=scanner.nextInt();
                Lesson lesson=new Lesson(null,lessonName,units,0,null,null);

                try {
                    lessonService.add(lesson);
                }catch (DuplicateUser e){
                    System.out.println("lesson already exists! ");

                }
                break;

            case 2:
                System.out.println("enter lesson ID: ");
                int lessonID=scanner.nextInt();


                if (lessonService.showInfo(lessonID)!=null){
                    lessonService.remove(lessonID);
                    System.out.println("removed successfully");
                    break;
                }else System.out.println("lesson does not exists! ");
                break;
            case 3:
                System.out.println("enter the lesson ID: ");
                lessonID=scanner.nextInt();
                System.out.println("enter new lesson name: ");
                lessonName=scanner.next();
                System.out.println("enter new lesson Units: ");
                int lessonUnits=scanner.nextInt();

                lesson=new Lesson(null,lessonName,lessonUnits,0,null,null);
                if (lessonService.showInfo(lessonID)!=null){
                    lessonService.update(lesson);
                    System.out.println("update successful");
                }
                else System.out.println("lesson id does not exist!");
                break;

            case 0:
                break;
        }

    }


//    //term management menu:returns term id
//    public static int termManagementMenu(){
//        System.out.println("1-set current term:");
//        System.out.println("2-add lessons to a term:");
//        int currentTerm=0;
//        Scanner scanner=new Scanner(System.in);
//        int userSelect=scanner.nextInt();
//
//        switch (userSelect){
//            case 1:
//                System.out.println("enter the current term: ");
//                currentTerm=scanner.nextInt();
//                Term.setTermID(currentTerm);
//                break;
//            case 2:
//                List<Lesson> lessonList=LessonService.showAll();
//
//                for (Lesson lesson:lessonList){
//                    System.out.println(lesson.toString());
//                }
//
//                List<Lesson> lessonList1=new ArrayList<>();
//                System.out.println("select lessons by id: ");
//                while (scanner.hasNextInt()){
//                    Lesson lesson=LessonService.showInfo(scanner.nextInt());
//                    lessonList1.add(lesson);
//                }
//                TermService.addTerm(lessonList1,currentTerm);
//                break;
//        }
//        return  currentTerm;
//    }


}




