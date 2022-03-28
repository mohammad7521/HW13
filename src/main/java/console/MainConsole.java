package console;

import entities.Staff;

import java.util.InputMismatchException;
import java.util.Scanner;

public class   MainConsole {

    public static void mainMenu()  {

        while (true) {
            System.out.println("1-staff: ");
            System.out.println("2-Student: ");
            System.out.println("3-Teacher: ");

            Scanner scanner = new Scanner(System.in);

            try {
                int userSelect = scanner.nextInt();
                switch (userSelect) {
                    case 1:
                        StaffConsole.staffLogInMenu();
                        break;
                    case 2:
                        StudentConsole.studentLogInMenu();
                        break;
                    case 3:
                        TeacherConsole.teacherLogInMenu();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
            }
        }
    }
}
