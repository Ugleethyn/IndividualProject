/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import privateschool.dao.TrainerDAOImpl;
import privateschool.models.Trainer;

/**
 *
 * @author Ugleethyn
 */
public class TrainerUtils extends TrainerDAOImpl {

    /**
     * Calls the correct methods to delete and check a trainer before delete
     * ready to use for menu
     */
    public void delete() {
        int _trainerId = protectNumber();
        while (checkTrainer(_trainerId) == false) {
            System.out.println("There's no trainer with that ID");
            _trainerId = protectNumber();
            checkTrainer(_trainerId);
        }
        deleteTrainer(_trainerId);
        System.out.println("Trainer has been succesfully deleted!");
    }

    /**
     * Prints the list of the courses so you can choose in witch course you want
     * to add this student It adds an student to a course
     */
    public void add(int _trainerId) {
        CourseUtils _checkCourse = new CourseUtils();
        _checkCourse.getCourses().forEach(System.out::println);
        System.out.println("Please enter the ID of the course you want to add this trainer (List is above)");
        int _courseId = protectNumber();
        boolean check = _checkCourse.checkCourse(_courseId);
        while (check == false) {
            System.out.println("There's no course with that ID");
            _courseId = protectNumber();
        }
        addToCourse(_trainerId, _courseId);
    }

    /**
     * Checks if the trainer you gave exists or no
     *
     * @return returns a correct id of a trainer
     */
    public int checkTrainer() {
        int _trainerId = protectNumber();
        boolean check = checkTrainer(_trainerId);
        while (check == false) {
            System.out.println("There's no trainer with that ID");
            _trainerId = protectNumber();
            check = checkTrainer(_trainerId);
        }
        System.out.println("Please enter the ID of the course you want to add this trainer");
        return _trainerId;
    }

    /**
     * This method is checking if the given ID is existing in our SQL database
     *
     * @param _trainerId
     * @return Returns true if the trainer exists else it returns false
     */
    public boolean checkTrainer(int _trainerId) {
        for (Trainer _trainer : getTrainers()) {
            if (_trainer.getTrainerId() == _trainerId) {
                return true;
            }
        }
        return false;
    }

}
