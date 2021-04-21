/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.menus;

import privateschool.PrivateSchool;
import utils.TrainerUtils;

/**
 *
 * @author Ugleethyn
 */
public class TrainerMenu extends TrainerUtils {

    public void printMenu() {
        System.out.format("%-5s %-10s %-10s %-1s \n", "ID", "First Name", "Last Name", "Subject");
        getTrainers().forEach(System.out::println);
        System.out.println("Press \"Create\" to create a new trainer\n"
                + "Press \"Delete\" to delete a trainer\n"
                + "Press \"Refresh\" to refresh your list\n"
                + "Press \"Add\" to add a trainer to a course\n"
                + "Press \"Back\" to return at main menu");
        takeAnswer();
    }

    public void takeAnswer() {
        switch (_reader.nextLine().toLowerCase()) {
            case "create":
                insertTrainer(createTrainer());
                System.out.println("Trainer has been created!");
                System.out.println(printChoices());
                break;

            case "delete":
                System.out.println("Please enter the ID of the trainer you want to delete");
                delete();
                System.out.println(printChoices());
                break;

            case "back":
                PrivateSchool.printMenu();
                break;

            case "add":
                System.out.println("Please enter the ID of the trainer you want to add");
                add(checkTrainer());
                System.out.println(printChoices());
                break;

            case "refresh":
                printMenu();
                break;

            default:
                System.out.println(printChoices());
                break;
        }
        takeAnswer();
    }

    public String printChoices() {
        return "Please type : 'Create', 'Delete', 'Refresh'  or 'Back'";
    }

}
