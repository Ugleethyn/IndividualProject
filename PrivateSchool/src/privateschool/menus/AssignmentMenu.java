/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.menus;

import privateschool.PrivateSchool;
import utils.AssignmentUtils;

/**
 *
 * @author Ugleethyn
 */
public class AssignmentMenu extends AssignmentUtils {

    public void printMenu() {
        System.out.format("%-5s %-15s %-20s %-17s %-10s %-10s \n", "ID", "Title", "Descreption", "Submision Date", "Oral Mark", "Total Mark");
        getAssignment().forEach(System.out::println);
        System.out.println("");
        System.out.println("Press \"Create\" to create a new assignment\n"
                + "Press \"Delete\" to delete a assignment\n"
                + "Press \"Student\" to see all students and their marks on assignments\n"
                + "Press \"Add\" to add an assignment to a student\n"
                + "Press \"Oral\" to set oral mark of a student\n"
                + "Press \"Total\" to set the total mark of a student\n"
                + "Press \"Refresh\" to refresh your list\n"
                + "Press \"Back\" to return at main menu");
        System.out.println("");
        takeAnswer();
    }

    public void takeAnswer() {
        switch (_reader.nextLine().toLowerCase()) {
            case "create":
                insertAssignment(createAssignment());
                System.out.println("Assignment has been created!");
                System.out.println(printChoises());
                break;

            case "delete":
                System.out.println("Please enter the Id of the assignment you want to delete");
                delete();
                System.out.println(printChoises());
                break;

            case "back":
                PrivateSchool.printMenu();
                break;

            case "add":
                System.out.println("Please enter the Id of the assignment you want to add");
                add(checkAssignment());
                printChoises();
                break;

            case "refresh":
                printMenu();
                break;

            case "oral":
                System.out.println("Please enter the Id of the assignment you want to grade");
                setOral();
                System.out.println(printChoises());
                break;

            case "total":
                System.out.println("Please enter the Id of the assignment you want to grade");
                setOral();
                System.out.println(printChoises());
                break;

            case "student":
                System.out.format("%-15s %-15s %-15s %-20s %-15s %-15s %-20s %-20s\n", "Student ID", "First Name", "Last Name", "Assignment ID", "Title", "Description", "Student's Oral Mark", "Student's Total ");
                getAssignmentPer().forEach(System.out::println);
                System.out.println(printChoises());
                break;

            default:
                System.out.println(printChoises());
                break;
        }
        takeAnswer();
    }

    public String printChoises() {
        return "Please type :'Create', 'Delete', 'Refresh','Add', 'Oral', 'Total' or 'Back'";
    }

}
