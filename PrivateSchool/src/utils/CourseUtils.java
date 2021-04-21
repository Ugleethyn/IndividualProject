/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import privateschool.dao.CourseDAOImpl;
import privateschool.models.Course;

/**
 *
 * @author Ugleethyn
 */
public class CourseUtils extends CourseDAOImpl {

        /**
     * Calls the correct methods to delete and check a course before delete
     *
     */
    public void delete() {
        int courseId = protectNumber();
        while (checkCourse(courseId) == false) {
            System.out.println("There's no course with that ID");
            courseId = protectNumber();
            checkCourse(courseId);
        }
        deleteCourse(courseId);
        System.out.println("Course has been succesfully deleted!");
    }
    /**
     * Prints all Students per course
     */
    public void printStudent() {
        int courseId = protectNumber();
        boolean check = checkCourse(courseId);
        while (check == false) {
            System.out.println("There's no course with that ID");
            courseId = protectNumber();
            check = checkCourse(courseId);
        }
        getStudentPerCourse(courseId).forEach(System.out::println);
    }

    /**
     * Prints all Trainers per course
     */
    public void printTrainers() {
        int courseId = protectNumber();
        boolean check = checkCourse(courseId);
        while (check == false) {
            System.out.println("There's no course with that ID");
            courseId = protectNumber();
            check = checkCourse(courseId);
        }
        getTrainerPerCourse(courseId).forEach(System.out::println);
    }

    /**
     * Prints all assignment per course by calling the method
     * getAssignPerCourse(int x);
     */
    public void printCourseAssign() {
        int courseId = protectNumber();
        boolean check = checkCourse(courseId);
        while (check == false) {
            System.out.println("There's no course with that ID");
            courseId = protectNumber();
            check = checkCourse(courseId);
        }
        getAssignPerCourse(courseId).forEach(System.out::println);
    }

    /**
     * Prints all assignment per student per course by calling the method
     * getAssPerStPerCourse(int x);
     */
    public void printAssPerCourPerSt() {
        int courseId = protectNumber();
        boolean check = checkCourse(courseId);
        while (check == false) {
            System.out.println("There's no course with that ID");
            courseId = protectNumber();
            check = checkCourse(courseId);
        }
         System.out.format("%-15s %-15s %-15s %-15s %-15s %-15s \n", "First Name", "Last Name","Assign Title","Description","Student's Oral Mark","Student's Oral Mark");
        getAssPerStPerCourse(courseId).forEach(System.out::println);
    }

    /**
     * This method is checking if the given ID is existing in our SQL database
     *
     * @param courseId
     * @return Returns true if the course exists else it returns false
     */
    public boolean checkCourse(int courseId) {
        for (Course course : getCourses()) {
            if (course.getCourseId() == courseId) {
                return true;
            }
        }
        return false;
    }
}
