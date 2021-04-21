/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import privateschool.dao.GroupAssignDAOImpl;
import privateschool.dto.AssignToCourDTO;
import privateschool.models.GroupAssign;

/**
 *
 * @author Ugleethyn
 */
public class GroupAssignUtils extends GroupAssignDAOImpl {

    private CourseUtils _checkCourse = new CourseUtils();

    /**
     * Checks if the given assignment is connected to the given course
     *
     * @param _assignmentId is the given assignment
     * @param _courseId is the given course
     * @return
     */
    public boolean checkAssPerCourse(int _assignmentId, int _courseId) {
        for (AssignToCourDTO _assignment : getAssignmentPer()) {
            if (_assignment.getAssId() == _assignmentId && _assignment.getCourseId() == _courseId) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is checking if the given mark is bigger than the max Mark of
     * the given assignment Get maxMark from methods getMaxOral(int
     * _assignmentId),getMaxTotal(int _assignmentId)
     *
     * @param _maxMark
     * @param _mark
     * @return
     */
    public boolean checkMark(int _maxMark, int _mark) {
        if (_mark <= _maxMark) {
            return true;
        }
        return false;
    }

    /**
     * This method is checking if the given ID is existing in our SQL database
     *
     * @param _assignmentId
     * @return Returns true if the assignment exists else it returns false
     */
    public boolean checkAssignment(int _assignmentId) {
        for (GroupAssign _assignment : getAssignment()) {
            if (_assignment.getGroupAssID() == _assignmentId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calls the correct methods to delete and check an assignment before delete
     *
     */
    public void delete() {
        int _assignmentId = protectNumber();
        while (checkAssignment(_assignmentId) == false) {
            System.out.println("There's no assignment with that ID");
            _assignmentId = protectNumber();
            checkAssignment(_assignmentId);
        }
        deleteAssignment(_assignmentId);
        System.out.println("Assignment has been succesfully deleted!");
    }
    

    /**
     * Checks the ID of the course you gave when you try to add an assignment to
     * a course or to set student's mark
     *
     * @return
     * the correct course Id
     */
    public int checkCourse() {
        System.out.println("Please enter the ID of the course (Look above list of students is printed)");
        int _courseId = protectNumber();
        boolean check = _checkCourse.checkCourse(_courseId);
        while (check == false) {
            System.out.println("There's no course with that ID");
            _courseId = protectNumber();
            check = _checkCourse.checkCourse(_courseId);
        }
        return _courseId;
    }

    /**
     * Checks if the given assignment is linked with the given course
     *
     * @param _assignmentId is the given assignment
     * @return returns a correct ID of a course that is linked with this
     * assignment
     */
    public int checkAssignPerCourse(int _assignmentId) {
        System.out.println("Please enter the ID of the Course (Look above list of Courses is printed)");
        int _courseId = protectNumber();
        boolean check = checkAssPerCourse(_assignmentId, _courseId);
        while (check == false) {
            System.out.println("There's no course linked with that assignment try again");
            _courseId = protectNumber();
            check = checkAssPerCourse(_assignmentId, _courseId);
        }
        return _courseId;
    }

    /**
     * Checks if the assignment you gave exists or no
     *
     * @return returns a correct id of an assignment
     */
    public int checkAssignment() {
        int _assignmentId = protectNumber();
        boolean check = checkAssignment(_assignmentId);
        while (check == false) {
            System.out.println("There's no assignment with that ID");
            _assignmentId = protectNumber();
            check = checkAssignment(_assignmentId);
        }
        return _assignmentId;
    }

    /**
     * Sets the mark and also checks the maximum value of it
     */
    public void setOral() {
        int _assignmentId = checkAssignment();
        int _maxOralMark = getMaxOral(_assignmentId);
        getAssignmentPer().forEach(System.out::println);
        int _studentId = checkAssignPerCourse(_assignmentId);
        System.out.println("Please enter the mark");
        int _grade = protectNumber();
        boolean check = checkMark(_maxOralMark, _grade);
        while (check == false) {
            System.out.println("Please enter a number less than " + _maxOralMark);
            _grade = protectNumber();
            check = checkMark(_maxOralMark, _grade);
        }
        setOralMark(_grade, _assignmentId, _studentId);
        System.out.println("Mark has been setted!");
    }

    /**
     * Sets the mark and also checks the maximum value of it
     */
    public void setTotal() {
        int _assignmentId = checkAssignment();
        int _maxTotalMark = getMaxOral(_assignmentId);
        getAssignmentPer().forEach(System.out::println);
        int _studentId = checkAssignPerCourse(_assignmentId);
        System.out.println("Please enter the mark");
        int _grade = protectNumber();
        boolean check = checkMark(_maxTotalMark, _grade);
        while (check == false) {
            System.out.println("Please enter a number less than " + _maxTotalMark);
            _grade = protectNumber();
            check = checkMark(_maxTotalMark, _grade);
        }
        setTotalMark(_grade, _assignmentId, _studentId);
        System.out.print("Mark has been setted!");
    }

    /**
     * Prints the list of the courses so you can choose in witch course you want to add this assignment
     * It adds an assignment to a course
     */

    public void add(int _assignmentId) {
        CourseUtils _checkCourse = new CourseUtils();
        _checkCourse.getCourses().forEach(System.out::println);
         System.out.println("Please enter the ID of the course you want to add this assignment (List is above)");
        int _courseId = protectNumber();
        boolean check = _checkCourse.checkCourse(_courseId);
        while (check == false) {
            System.out.println("There's no course with that ID");
            _courseId = protectNumber();
        }
        System.out.println("Assignment has been added to the course");
        addTo(_assignmentId, _courseId);
    }
    
}
