/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.menus;

import privateschool.PrivateSchool;
import utils.StudentUtils;

/**
 *
 * @author Ugleethyn
 */
public class StudentMenu extends StudentUtils {

    public void printMenu() {
        System.out.format("%-5s %-15s %-15s %-15s %-2s \n", "ID", "First Name", "Last Name", "Birthday Date", "Tuition Fees");
        getStudents().forEach(System.out::println);
        System.out.println("Press \"Create\" to create a new student\n"
                + "Press \"Delete\" to delete a student\n"
                + "Press \"Refresh\" to refresh your list\n"
                + "Press \"Add\" to add a student to a course\n"
                + "Press \"Assignment\" to see all assignment per student per course\n"
                + "Press \"Multiple\" see students that belongs to more than one course\n"
                + "Press \"Back\" to return at main menu");
        takeAnswer();
    }

    public void takeAnswer() {
        switch (_reader.nextLine().toLowerCase()) {
            case "create":
                insertStudnet(createStudent());
                System.out.println("Student has been created!");
                System.out.println(printChoices());
                break;

            case "delete":
                System.out.println("Please enter the ID of the trainer you want to delete");
                delete();
                break;

            case "back":
                PrivateSchool.printMenu();
                break;

            case "add":
                System.out.println("Please enter the ID of the trainer you want to add");
                add(checkStudent());
                System.out.println(printChoices());
                break;

            case "refresh":
                printMenu();
                break;

            case "multiple":
                getStudentMultipleCourse().forEach(System.out::println);
                System.out.println(printChoices());
                break;
                
            case "assignment":
                System.out.format("%-15s %-15s %-15s %-15s %-15s %-15s %-20s %-20s %-20s \n", "First Name", "Last Name", "Course Title", "Stream","Type","Assign Title","Description","Student's Oral Mark","Student's Oral Mark");
                getAllAssigPerStPerCourse().forEach(System.out::println);
                System.out.println(printChoices());
                break;

            default:
                System.out.println(printChoices());
                break;
        }
        takeAnswer();
    }

    public String printChoices() {
        return "Please type : 'Create','Add', 'Delete', 'Refresh'  or 'Back'";
    }

}
