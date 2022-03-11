package console;

import entities.Teacher;
import services.TeacherRepoServices;

import java.sql.Date;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TeacherConsole {
//
//
//    public static TeacherRepoServices teacherServices=new TeacherRepoServices();
//
//    public static void teacherLogInMenu() {
//
//        Scanner scanner=new Scanner(System.in);
//        boolean flag = true;
//        while (flag) {
//            System.out.println("1-sign in: ");
//            System.out.println("0-exit");
//
//            int userInput=scanner.nextInt();
//            switch (userInput){
//                case 1:
//                    System.out.println("enter your username:");
//                    String username=scanner.next();
//                    System.out.println("enter your password:");
//                    String password=scanner.next();
//            }
//        }
//
//
//
//    public static void cinemaMainMenu(String username) {
//        System.out.println("1-generate tickets: ");
//        System.out.println("2-cancel tickets: ");
//
//        Scanner scanner = new Scanner(System.in);
//
//        try {
//            Cinema cinema = CinemaService.showInfo(username);
//            int userEntry = scanner.nextInt();
//            switch (userEntry) {
//                    case 1:
//                        while (true) {
//                        try {
//                            System.out.println("when is the ticket Date?");
//                            Date ticketDate = Date.valueOf(scanner.next());
//                            System.out.println("when is the start time?");
//                            Time startTime = Time.valueOf(scanner.next());
//                            System.out.println("when is the end time?");
//                            Time endingTime = Time.valueOf(scanner.next());
//                            System.out.println("what is the movie name?");
//                            String movieName = scanner.next();
//                            System.out.println("enter the number of tickets: ");
//                            int quantity = scanner.nextInt();
//                            System.out.println("enter the price");
//                            int price = scanner.nextInt();
//                            if (CinemaService.generateTickets(username,ticketDate, startTime, endingTime, movieName, cinema.getId(),
//                                    quantity, price)) {
//                                System.out.println("tickets generated successfully!");
//                                break;
//                            } else {
//                                System.out.println("your account is not validated!");
//                                System.out.println("please contact admin!");
//                                break;
//                            }
//                        } catch (IllegalArgumentException e ) {
//                            System.out.println("please enter date and time in the correct format! ");
//                            System.out.println();
//                            System.out.println("yyy-mm-dd");
//                            System.out.println("hh:mm:ss");
//                        }catch (InputMismatchException e){
//                            System.out.println("please enter a valid number! ");
//                        }
//                }
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("pleas enter a valid number");
//        }
//    }
}
