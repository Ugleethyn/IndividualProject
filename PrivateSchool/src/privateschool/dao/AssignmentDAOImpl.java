/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import privateschool.dto.AssignToStudentDTO;
import privateschool.models.Assignment;
import utils.DbConnection;
import utils.utils;

/**
 *
 * @author Ugleethyn
 */
public class AssignmentDAOImpl extends utils implements AssignmentDAOInterface {

    /**
     * Takes the data from SQL and it converts it into a list (Plus ID)
     *
     * @return It returns a list of all assignments ready to use
     */
    @Override
    public List<Assignment> getAssignment() {
        String query = "SELECT * FROM assignment";
        List<Assignment> _allAssignment = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int _idAssignment = rs.getInt("ID_ASSIGNMENT");
                String _title = rs.getString("TITLE");
                String _descr = rs.getString("DESCR");
                Date _subDate = rs.getDate("SUB_DATE");
                int _oralMark = rs.getInt("ORAL_MARK");
                int _totalMark = rs.getInt("TOTAL_MARK");
                Assignment _assignment = new Assignment(_idAssignment, _title, _descr, _subDate, _oralMark, _totalMark);
                _allAssignment.add(_assignment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _allAssignment;
    }

    /**
     * Takes the data from SQL and it converts it into a list (Plus ID)
     *
     * @return a list of all assignments per student with the id of both(Student
     * and Assignment) and some more informations that you want to know
     */
    @Override
    public List<AssignToStudentDTO> getAssignmentPer() {
        ArrayList<AssignToStudentDTO> _assignPerStudent = new ArrayList();
        String query = "SELECT person_implement.ID_STUDENT,FIRST_NAME,LAST_NAME,person_implement.ID_ASSIGNMENT,TITLE,DESCR,STUDENT_ORAL_MARK,STUDENT_TOTAL_MARK\n"
                + "FROM student,person_implement,assignment\n"
                + "WHERE student.ID_STUDENT=person_implement.ID_STUDENT\n"
                + "AND assignment.ID_ASSIGNMENT=person_implement.ID_ASSIGNMENT";
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int _studentId = rs.getInt("ID_STUDENT");
                String _firstName = rs.getString("FIRST_NAME");
                String _lastName = rs.getString("LAST_NAME");
                int _assignmentId = rs.getInt("ID_ASSIGNMENT");
                String _title = rs.getString("TITLE");
                String _descr = rs.getString("DESCR");
                int _oralMark = rs.getInt("STUDENT_ORAL_MARK");
                int _totalMark = rs.getInt("STUDENT_TOTAL_MARK");
                AssignToStudentDTO _assignPerSt = new AssignToStudentDTO(_studentId, _firstName, _lastName, _assignmentId, _title, _descr, _oralMark, _totalMark);
                _assignPerStudent.add(_assignPerSt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _assignPerStudent;
    }

    /**
     * This method asks for the informations of the assignment you want to
     * create
     *
     * @return
     */
    @Override
    public Assignment createAssignment() {
        System.out.println("Please enter the title of the assignment");
        String _title = _reader.nextLine();
        System.out.println("Please enter the description of the assignment");
        String _descr = _reader.nextLine();
        System.out.println("Please enter the sub date of the assignment");
        Date _subDate = correctDate();
        System.out.println("Please enter the value of the max Oral Mark");
        int _oralMark = protectNumber();
        System.out.println("Please enter the value of the max Total Mark");
        int _totalMark = protectNumber();
        return new Assignment(_title, _descr, _subDate, _oralMark, _totalMark);
    }

    /**
     * This method is inserting the assignment inside databse (ID is Auto
     * implemented)
     *
     * @param _assignment It asks for an assignment so you can call this method
     * with the method createAssignment() as parametr for example
     * insertAssignment(createAssignment());
     */
    @Override
    public void insertAssignment(Object _assign) {
        String query = "INSERT INTO assignment (`TITLE`, `DESCR`, `SUB_DATE`, `ORAL_MARK`, `TOTAL_MARK`) VALUES (?, ?, ?, ?, ?);";
        Assignment _assignment = (Assignment) _assign;
        try(Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, _assignment.getTitle());
            ps.setString(2, _assignment.getDescr());
            ps.setDate(3, _assignment.getSubDate());
            ps.setInt(4, _assignment.getOralMark());
            ps.setInt(5, _assignment.getTotalMark());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is taking an ID as parametr and deletes the ID from foreign
     * keys and from db at all Methods for foreign keys
     * delForKeyMustImplement(); The check of the ID is taking place in another
     * method named checkAssignment();
     *
     * @param _assignmentId
     */
    @Override
    public void deleteAssignment(int _assignmentId) {
        String query = "DELETE FROM assignment WHERE ID_ASSIGNMENT=?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _assignmentId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method adds an assignment to a student
     *
     * @param _assignmentId It asks for the id of the trainer you want to insert
     * @param _studentId And the Id of the student too
     */
    @Override
        public void addTo(int _assignmentId ,int _studentId) {
       String query = "insert into person_implement (ID_STUDENT, ID_ASSIGNMENT) values (?,?)";
               try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _studentId);
            ps.setInt(2, _assignmentId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets the maximum oral mark from an assignment
     *
     * @param _assignmentId Asks for an ID of assignments to take the maximum
     * Oral Mark
     * @return The correct mark
     */
    public int getMaxOral(int _assignmentId) {
        int maxMark = 0;
        ResultSet rs = null;
        String query = "SELECT ORAL_MARK FROM assignment WHERE ID_ASSIGNMENT = ?";
        try(Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _assignmentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                maxMark = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return maxMark;
    }

    /**
     * Gets the maximum total mark from an assignment
     *
     * @param _assignmentId Asks for an ID of assignments to take the maximum
     * Total Mark
     * @return The correct mark
     */
    public int getMaxTotal(int _assignmentId) {
        int maxMark = 0;
        ResultSet rs = null;
        String query = "SELECT TOTAL_MARK FROM assignment WHERE ID_ASSIGNMENT = ?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _assignmentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                maxMark = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return maxMark;
    }

    /**
     * Sets the oral mark into person_implement at STUDENT_ORAL_MARK column
     *
     * @param _mark mark should be filtered by the caller
     * @param _assignmentId Assignment should be filtered by the caller
     * @param _studentId student should be filtered by the caller
     */
    public void setOralMark(int _mark, int _assignmentId, int _studentId) {
        String query = "UPDATE `school_project`.`person_implement` SET `STUDENT_ORAL_MARK` = ? WHERE (`ID_ASSIGNMENT` = ?) and (`ID_STUDENT` = ?);";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _mark);
            ps.setInt(2, _assignmentId);
            ps.setInt(3, _studentId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sets the Total mark into person_implement at STUDENT_TOTAL_MARK column
     *
     * @param _mark mark should be filtered by the caller
     * @param _assignmentId Assignment should be filtered by the caller
     * @param _studentId student should be filtered by the caller
     */
    public void setTotalMark(int _mark, int _assignmentId, int _studentId) {
        String query = "UPDATE `school_project`.`person_implement` SET `STUDENT_TOTAL_MARK` = ? WHERE (`ID_ASSIGNMENT` = ?) and (`ID_STUDENT` = ?);";
        try(Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, _mark);
            ps.setInt(2, _assignmentId);
            ps.setInt(3, _studentId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
