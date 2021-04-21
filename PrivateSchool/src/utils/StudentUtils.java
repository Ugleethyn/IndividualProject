/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import privateschool.dao.StudentDAOImpl;
import privateschool.models.Student;

/**
 *
 * @author Ugleethyn
 */
public class StudentUtils extends StudentDAOImpl {

    /**
     * Prints the list of the courses so you can choose in witch course you want
     * to add this student It adds an student to a course
     */
    public void add(int _studentId) {
        CourseUtils _checkCourse = new CourseUtils();
        _checkCourse.getCourses().forEach(System.out::println);
        System.out.println("Please enter the ID of the course you want to add this student (List is above)");
        int _courseId = protectNumber();
        boolean check = _checkCourse.checkCourse(_courseId);
        while (check == false) {
            System.out.println("There's no course with that ID");
            _courseId = protectNumber();
        }
        addToCourse(_studentId, _courseId);
    }

    /**
     * Checks if the student you gave exists or no
     *
     * @return returns a correct id of a student
     */
    public int checkStudent() {
        int _studentId = protectNumber();
        boolean check = checkStudent(_studentId);
        while (check == false) {
            System.out.println("There's no trainer with that ID");
            _studentId = protectNumber();
            check = checkStudent(_studentId);
        }
        System.out.println("Please enter the ID of the course you want to add this trainer");
        return _studentId;
    }

    /**
     * Calls the correct methods to delete and check a student before delete
     * ready to use for menu
     */
    public void delete() {
        int _studentId = protectNumber();
        while (checkStudent(_studentId) == false) {
            System.out.println("There's no student with that ID");
            _studentId = protectNumber();
            checkStudent(_studentId);
        }
        deleteStudent(_studentId);
        System.out.println("Student has been succesfully deleted!");
    }

    /**
     * This method is checking if the given ID is existing in our SQL database
     *
     * @param _studentId
     * @return Returns true if the student exists else it returns false
     */
    public boolean checkStudent(int _studentId) {
        for (Student _student : getStudents()) {
            if (_student.getStudentId() == _studentId) {
                return true;
            }
        }
        return false;
    }

}
