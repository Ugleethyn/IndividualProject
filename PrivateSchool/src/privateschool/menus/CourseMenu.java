/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.menus;

import java.util.Scanner;
import privateschool.PrivateSchool;
import utils.CourseUtils;

/**
 *
 * @author Ugleethyn
 */
public class CourseMenu extends CourseUtils {

    private Scanner _reader = new Scanner(System.in);

    public void printMenu() {
        System.out.format("%-5s %-10s %-10s %-15s %-15s %-10s \n", "ID", "Title", "Stream", "Type","Start Date","End Date");
        getCourses().forEach(System.out::println);
        System.out.println("Press \"Create\" to create a new course\n"
                + "Press \"Delete\" to delete a course\n"
                + "Press \"Refresh\" to refresh your list\n"
                + "Press \"Student\" to see student in a specific \n"
                + "Press \"Trainer\" to see trainers in a specific course\n"
                + "Press \"Course Assignment\" to course assignments in a specific course\n"
                + "Press \"Assignment\" to see assignment per student in a specific course\n"
                + "Press \"Back\" to return at main menu");
        takeAnswer();
    }

    public void takeAnswer() {
        switch (_reader.nextLine().toLowerCase()) {
            case "create":
                insertCourse(createCourse());
                System.out.println("Course has been created!");
                System.out.println(printChoises());
                break;

            case "delete":
                System.out.println("Please enter the ID of the course you want to delete");
                delete();
                System.out.println(printChoises());
                break;

            case "back":
                PrivateSchool.printMenu();
                break;

            case "refresh":
                printMenu();
                break;

            case "student":
                System.out.println("Please enter the ID of the course you want to check");
                printStudent();
                System.out.println(printChoises());
                break;

            case "trainer":
                System.out.println("Please enter the ID of the course you want to check");
                printTrainers();
                System.out.println(printChoises());
                break;

            case "course assignment":
                System.out.println("Please enter the ID of the course you want to check");
                printCourseAssign();
                System.out.println(printChoises());
                break;

            case "assignment":
                System.out.println("Please enter the ID of the course you want to check");
                printAssPerCourPerSt();
                System.out.println(printChoises());
                break;

            default:
                System.out.println(printChoises());
                break;
        }
        takeAnswer();
    }

    public String printChoises() {
        return "Please type :'Create', 'Delete', 'Refresh', 'Student', 'Trainer', 'Course Assignment', 'Assignment' or 'Back'";
    }

}
