package privateschool;

import java.util.Scanner;
import privateschool.menus.AssignmentMenu;
import privateschool.menus.CourseMenu;
import privateschool.menus.GroupAssignmentMenu;
import privateschool.menus.StudentMenu;
import privateschool.menus.TrainerMenu;

/**
 *
 * @author Ugleethyn
 */
public class PrivateSchool {

    private static Scanner _reader = new Scanner(System.in);
   private static CourseMenu _course = new CourseMenu();
   private static TrainerMenu _trainer = new TrainerMenu();
   private static StudentMenu _student = new StudentMenu();
   private static AssignmentMenu _assignment = new AssignmentMenu();
   private static GroupAssignmentMenu _groupAssignment = new GroupAssignmentMenu();

    public static void main(String[] args) {
        /**
         * Start of program
         */
        printMenu();
        /**
         * End of program
         */
    }

    public static void printMenu() {
        System.out.println("Welcome to School");
        System.out.println("Press \"Trainer\" to edit or see trainers\n"
                + "Press \"Student\" to edit or see students\n"
                + "Press \"Course\" to edit or see courses\n"
                + "Press \"Assignment\" to edit or see atomic assignments\n"
                + "Press \"Course Assignment\" to edit or see course assignments\n"
                + "Press \"Exit\" to exit");
        System.out.print("Type here : ");
        takeAnswer();
    }

    public static void takeAnswer() {
        switch (_reader.nextLine().toLowerCase()) {
            case "student":
                System.out.println("You have load Student list.");
                _student.printMenu();
                break;

            case "course":
                System.out.println("You have load Course list.");
                _course.printMenu();
                break;

            case "trainer":
                System.out.println("You have load Trainer list.");
                _trainer.printMenu();
                break;

            case "assignment":
                System.out.println("You have load Assignment list.");
                _assignment.printMenu();
                break;

            case "course assignment":
                System.out.println("You have load Assignment list.");
                _groupAssignment.printMenu();
                break;

            case "exit":
                System.out.println("Terminated.");
                System.exit(0);
                break;

            default:
                System.out.println("Please type : 'Student', 'Course', 'Trainer', 'Assignment' or 'Exit'");
                break;
        }
        takeAnswer();
    }
}
