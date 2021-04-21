/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import privateschool.dao.AssignmentDAOImpl;
import privateschool.dto.AssignToStudentDTO;
import privateschool.models.Assignment;

/**
 * @author Ugleethyn
 */
public class AssignmentUtils extends AssignmentDAOImpl {

    private StudentUtils _checkStudent = new StudentUtils();

    /**
     * Checks if the given assignment is connected to the given student
     *
     * @param _assignmentId is the given assignment
     * @param _studentId is the given student
     * @return
     */
    public boolean checkAssPerSt(int _assignmentId, int _studentId) {
        for (AssignToStudentDTO _assignment : getAssignmentPer()) {
            if (_assignment.getAssignmentId() == _assignmentId && _assignment.getStudentId() == _studentId) {
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
        for (Assignment _assignment : getAssignment()) {
            if (_assignment.getAssignId() == _assignmentId) {
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
     * Checks the ID of the student you gave when you try to add an assignment
     * to a student or to set student's mark
     *
     * @return the correct student's Id
     */
    public int checkStudent() {
        System.out.println("Please enter the ID of the Student (Look above list of students is printed)");
        int _studentId = protectNumber();
        boolean check = _checkStudent.checkStudent(_studentId);
        while (check == false) {
            System.out.println("There's no student with that ID");
            _studentId = protectNumber();
            check = _checkStudent.checkStudent(_studentId);
        }
        return _studentId;
    }

    /**
     * Checks if the given assignment is linked with the given student
     *
     * @param _assignmentId is the given assignment
     * @return returns a correct ID of a student that is linked with this
     * assignment
     */
    public int checkStPerAssign(int _assignmentId) {
        System.out.println("Please enter the ID of the Student (Look above list of students is printed)");
        int _studentId = protectNumber();
        boolean check = checkAssPerSt(_assignmentId, _studentId);
        while (check == false) {
            System.out.println("There's no student linked with that assignment try again");
            _studentId = protectNumber();
            check = checkAssPerSt(_assignmentId, _studentId);
        }
        return _studentId;
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
        System.out.format("%-15s %-15s %-15s %-20s %-15s %-15s %-20s %-20s\n", "Student ID", "First Name", "Last Name", "Assignment ID", "Title", "Description", "Student's Oral Mark", "Student's Total ");
        getAssignmentPer().forEach(System.out::println);
        int _studentId = checkStPerAssign(_assignmentId);
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
        int _studentId = checkStPerAssign(_assignmentId);
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
     * Prints the list of the student so you can choose in witch student you
     * want to add this assignment It adds an assignment to a student
     */
    public void add(int _assignmentId) {
        _checkStudent.getStudents().forEach(System.out::println);
        System.out.println("Please enter the ID of the student you want to add this student (List is above)");
        int _studentId = protectNumber();
        boolean check = _checkStudent.checkStudent(_studentId);
        while (check == false) {
            System.out.println("There's no student with that ID");
            _studentId = protectNumber();
        }
        System.out.println("Assignment has been added to the student");
        addTo(_assignmentId, _studentId);
    }

}
