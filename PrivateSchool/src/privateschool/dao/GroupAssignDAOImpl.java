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
import privateschool.dto.AllAssignPerCourseDTO;
import privateschool.dto.AssignToCourDTO;
import privateschool.models.GroupAssign;
import utils.DbConnection;
import utils.utils;

/**
 *
 * @author Ugleethyn
 */
public class GroupAssignDAOImpl extends utils implements AssignmentDAOInterface {

    /**
     * Takes the data from SQL and it converts it into a list (Plus ID)
     *
     * @return It returns a list of all assignments ready to use
     */
    @Override
    public List<GroupAssign> getAssignment() {
        String query = "SELECT * FROM course_assign";
        List<GroupAssign> _allAssignment = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int _idAssignment = rs.getInt("ID_COURSE_ASSIGN");
                String _title = rs.getString("TITLE");
                String _descr = rs.getString("DESCR");
                Date _subDate = rs.getDate("SUB_DATE");
                int _oralMark = rs.getInt("ORAL_MARK");
                int _totalMark = rs.getInt("TOTAL_MARK");
                GroupAssign _assignment = new GroupAssign(_idAssignment, _title, _descr, _subDate, _oralMark, _totalMark);
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
    public List<AssignToCourDTO> getAssignmentPer() {
        ArrayList<AssignToCourDTO> _assignPrCourse = new ArrayList();
        String query = "SELECT team_implement.ID_COURSE,course.TITLE,STREAM,TYPE,team_implement.ID_COURSE_ASSIGN,course_assign.DESCR,STUDENT_ORAL_MARK,STUDENT_TOTAL_MARK\n"
                + "FROM course,team_implement,course_assign\n"
                + "WHERE course.ID_COURSE=team_implement.ID_COURSE\n"
                + "AND course_assign.ID_COURSE_ASSIGN=team_implement.ID_COURSE_ASSIGN";
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int _courseId = rs.getInt("ID_COURSE");
                String _courseTitle = rs.getString("TITLE");
                String _stream = rs.getString("STREAM");
                String _type = rs.getString("TYPE");
                int _assignmentId = rs.getInt("ID_COURSE_ASSIGN");
                String _title = rs.getString("TITLE");
                String _descr = rs.getString("DESCR");
                int _oralMark = rs.getInt("STUDENT_ORAL_MARK");
                int _totalMark = rs.getInt("STUDENT_TOTAL_MARK");
                AssignToCourDTO _assignPerCr = new AssignToCourDTO(_courseId, _courseTitle, _stream, _type, _assignmentId, _title, _descr, _oralMark, _totalMark);
                _assignPrCourse.add(_assignPerCr);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _assignPrCourse;
    }

    /**
     * This method asks for the informations of the assignment you want to
     * create
     *
     * @return
     */
    @Override
    public GroupAssign createAssignment() {
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
        return new GroupAssign(_title, _descr, _subDate, _oralMark, _totalMark);
    }

    /**
     * This method is inserting the assignment inside databse (ID is Auto
     * implemented)
     *
     * @param _assign It asks for an assignment so you can call this method with
     * the method createAssignment() as parametr for example
     * insertAssignment(createAssignment());
     */
    @Override
    public void insertAssignment(Object _assign) {
        String query = "INSERT INTO course_assign (`TITLE`, `DESCR`, `SUB_DATE`, `ORAL_MARK`, `TOTAL_MARK`) VALUES (?, ?, ?, ?, ?);";
        GroupAssign _assignment = (GroupAssign) _assign;
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
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
     * keys and from db at all
     *
     * @param _assignmentId
     */
    @Override
    public void deleteAssignment(int _assignmentId) {
        String query = "DELETE FROM course_assign WHERE ID_COURSE_ASSIGN=?";
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
     * @param courseId And the Id of the student too
     */
    @Override
    public void addTo(int _assignmentId, int courseId) {
        String query = "insert into team_implement (ID_COURSE, ID_COURSE_ASSIGN) values (?,?)";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(query)) {
            ps.setInt(1, courseId);
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
        String query = "SELECT ORAL_MARK FROM course_assign WHERE ID_COURSE_ASSIGN = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(query)) {
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
        String query = "SELECT TOTAL_MARK FROM course_assign WHERE ID_COURSE_ASSIGN = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(query)) {
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
     * Sets the oral mark into team_implement at STUDENT_ORAL_MARK column
     *
     * @param _mark mark should be filtered by the caller
     * @param _assignmentId Assignment should be filtered by the caller
     * @param _courseId course should be filtered by the caller
     */
    public void setOralMark(int _mark, int _assignmentId, int _courseId) {
        String query = "UPDATE `school_project`.`team_implement` SET `STUDENT_ORAL_MARK` = ? WHERE (`COURSE_ID` = ?) and (`ID_COURSE_ASSIGN` = ?);";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(query)) {
            ps.setInt(1, _mark);
            ps.setInt(2, _courseId);
            ps.setInt(2, _assignmentId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sets the Total mark into team_implement at STUDENT_TOTAL_MARK column
     *
     * @param _mark mark should be filtered by the caller
     * @param _assignmentId Assignment should be filtered by the caller
     * @param _courseId student should be filtered by the caller
     */
    public void setTotalMark(int _mark, int _assignmentId, int _courseId) {
        String query = "UPDATE `school_project`.`team_implement` SET `STUDENT_TOTAL_MARK` = ? WHERE (`COURSE_ID` = ?) and (`ID_COURSE_ASSIGN` = ?);";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(query)) {
            ps.setInt(1, _mark);
            ps.setInt(2, _courseId);
            ps.setInt(2, _assignmentId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets all course assignments per course with the course title/stream and type
     * @return 
     * A list of all course assignments
     */
    public List<AllAssignPerCourseDTO> getAllAssignPerCourse() {
        String query = "SELECT course.TITLE,stream,type,course_assign.TITLE AS 'ASSIGN_TITLE',DESCR,team_implement.STUDENT_ORAL_MARK,team_implement.STUDENT_TOTAL_MARK\n" +
"FROM course_assign,team_implement,course\n" +
"WHERE course_assign.ID_COURSE_ASSIGN=team_implement.ID_COURSE_ASSIGN\n" +
"AND course.ID_COURSE=team_implement.ID_COURSE;";
        List<AllAssignPerCourseDTO> _allAssignPerCourse = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                String _courseTitle = rs.getString("TITLE");
                String _courseStream = rs.getString("STREAM");
                String _courseType = rs.getString("TYPE");
                String _assignTitle = rs.getString("ASSIGN_TITLE");
                String _assignDescr = rs.getString("DESCR");
                int _studentOralMark = rs.getInt("STUDENT_ORAL_MARK");
                int _studentTotalMark = rs.getInt("STUDENT_TOTAL_MARK");
                AllAssignPerCourseDTO _object = new AllAssignPerCourseDTO(_courseTitle, _courseStream,_courseType, _assignTitle,_assignDescr, _studentOralMark, _studentTotalMark);
                _allAssignPerCourse.add(_object);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _allAssignPerCourse;
    }
}
