/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.menus;

import privateschool.PrivateSchool;
import utils.GroupAssignUtils;

/**
 *
 * @author Ugleethyn
 */
public class GroupAssignmentMenu extends GroupAssignUtils {

    public void printMenu() {
        System.out.format("%-5s %-15s %-20s %-17s %-10s %-10s \n", "ID", "Title", "Descreption", "Submision Date", "Oral Mark", "Total Mark");
        getAssignment().forEach(System.out::println);
        System.out.println("Press \"Create\" to create a new assignment\n"
                + "Press \"Delete\" to delete a assignment\n"
                + "Press \"Refresh\" to refresh your list\n"
                + "Press \"Add\" to add an assignment to a student\n"
                + "Press \"All\" to see all course assignments per course\n"
                + "Press \"Oral\" to set oral mark of a student\n"
                + "Press \"Total\" to set the total mark of a student\n"
                + "Press \"Back\" to return at main menu");
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

            case "all":
                System.out.format("%-15s %-15s %-15s %-15s %-20s %-20s %-20s \n",  "Course Title", "Stream", "Type", "Assign Title", "Description", "Student's Oral Mark", "Student's Oral Mark");
                getAllAssignPerCourse().forEach(System.out::println);
                 System.out.println(printChoises());
                break;

            default:
                System.out.println(printChoises());
                break;
        }
        takeAnswer();
    }

    public String printChoises() {
        return "Please type :'Create', 'Delete', 'Refresh', 'Add', 'Oral', 'Total' or 'Back'";
    }
}
