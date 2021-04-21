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
import privateschool.dto.AllAssignPerStPerCourseDTO;
import privateschool.models.Student;
import utils.DbConnection;
import utils.utils;

/**
 *
 * @author Ugleethyn
 */
public class StudentDAOImpl extends utils implements StudentDAOInterface {

    /**
     * Takes the data from SQL and it converts it into a list (Plus ID)
     *
     * @return It returns a list of all students ready to use
     */
    @Override
    public List<Student> getStudents() {
        String query = "SELECT * FROM student";
        List<Student> _allStudents = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int _idStudent = rs.getInt("ID_STUDENT");
                String _firstName = rs.getString("FIRST_NAME");
                String _lastName = rs.getString("LAST_NAME");
                Date _birthDate = rs.getDate("BIRTH_DATE");
                int _tuitionFees = rs.getInt("TUITION_FEES");
                Student _student = new Student(_idStudent, _firstName, _lastName, _birthDate, _tuitionFees);
                _allStudents.add(_student);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _allStudents;
    }

    /**
     * This method asks for the informations of the student after that it
     * returns a new trainer without ID
     *
     * @return
     */
    @Override
    public Student createStudent() {
        System.out.println("Please enter the first name of the student :");
        String _firstName = _reader.nextLine();
        System.out.println("Please enter the last name of the student");
        String _lastName = _reader.nextLine();
        System.out.println("Please enter the birthday date of student");
        Date _birthDate = correctDate();
        System.out.println("Please enter the value of tuition fees of student");
        int _tuitionFees = protectNumber();
        return new Student(_firstName, _lastName, _birthDate, _tuitionFees);
    }

    /**
     * This method is inserting the trainer in to the databse (ID is Auto
     * implemented)
     *
     * @param _student It asks for a student so you can call this method with
     * the method createStudent as parametr for example
     * insertStudnet(createStudent());
     */
    @Override
    public void insertStudnet(Student _student) {
        String query = "INSERT INTO student (`FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `TUITION_FEES`) VALUES (?, ?, ?, ?);";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, _student.getFirstName());
            ps.setString(2, _student.getLastName());
            ps.setDate(3, _student.getBirthDate());
            ps.setInt(4, _student.getTuitionFees());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is taking an ID as parametr and deletes the ID from foreign
     *
     * @param _studentId
     */
    @Override
    public void deleteStudent(int _studentId) {
        String query = "DELETE FROM student WHERE ID_STUDENT=?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _studentId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method adds a student to a course
     *
     * @param _studentId It asks for the id of the student you want to insert
     * @param _courseID And the Id of the course too
     */
    @Override
    public void addToCourse(int _studentId, int _courseID) {
        String query = "insert into watches (ID_STUDENT, ID_COURSE) values (?,?)";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(query)) {
            ps.setInt(1, _studentId);
            ps.setInt(2, _courseID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get all students that exists in more than one course
     * @return 
     * a list of students 
     */
    public List<Student> getStudentMultipleCourse() {
        String query = "SELECT student.*\n"
                + "FROM watches, student\n"
                + "WHERE student.ID_STUDENT=watches.ID_STUDENT\n"
                + "GROUP BY watches.ID_STUDENT\n"
                + "HAVING COUNT(watches.ID_COURSE)>1;";
        List<Student> _allStudents = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int _idStudent = rs.getInt("ID_STUDENT");
                String _firstName = rs.getString("FIRST_NAME");
                String _lastName = rs.getString("LAST_NAME");
                Date _birthDate = rs.getDate("BIRTH_DATE");
                int _tuitionFees = rs.getInt("TUITION_FEES");
                Student _student = new Student(_idStudent, _firstName, _lastName, _birthDate, _tuitionFees);
                _allStudents.add(_student);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _allStudents;
    }
    /**
     * Get all assignments per all students with names ,informations for assignments and marks of students
     * @return 
     * a list of all students with their assignments
     */
    public List<AllAssignPerStPerCourseDTO> getAllAssigPerStPerCourse() {
        String query = "SELECT student.FIRST_NAME,student.LAST_NAME,course.TITLE,stream,type,assignment.TITLE AS 'ASSIGN_TITLE',DESCR,person_implement.STUDENT_ORAL_MARK,person_implement.STUDENT_TOTAL_MARK\n" +
"FROM student,watches,assignment,person_implement,course\n" +
"WHERE student.ID_STUDENT=watches.ID_STUDENT\n" +
"AND student.ID_STUDENT= person_implement.ID_STUDENT\n" +
"AND assignment.ID_ASSIGNMENT=person_implement.ID_ASSIGNMENT\n" +
"AND course.ID_COURSE=watches.ID_COURSE;";
        List<AllAssignPerStPerCourseDTO> _allAssignPerStPerC = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                String _firstName = rs.getString("FIRST_NAME");
                String _lastName = rs.getString("LAST_NAME");
                String _courseTitle = rs.getString("TITLE");
                String _courseStream = rs.getString("STREAM");
                String _courseType = rs.getString("TYPE");
                String _assignTitle = rs.getString("ASSIGN_TITLE");
                String _assignDescr = rs.getString("DESCR");
                int _studentOralMark = rs.getInt("STUDENT_ORAL_MARK");
                int _studentTotalMark = rs.getInt("STUDENT_TOTAL_MARK");
                AllAssignPerStPerCourseDTO _object = new AllAssignPerStPerCourseDTO(_firstName, _lastName, _courseTitle, _courseStream, _courseType, _assignTitle, _assignDescr, _studentOralMark, _studentTotalMark);
                _allAssignPerStPerC.add(_object);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _allAssignPerStPerC;
    }

}
