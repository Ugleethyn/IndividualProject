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
import privateschool.dto.AssPerCourPerStDTO;
import privateschool.models.Course;
import privateschool.models.GroupAssign;
import privateschool.models.Student;
import privateschool.models.Trainer;
import utils.DbConnection;
import utils.utils;

/**
 *
 * @author Ugleethyn
 */
public class CourseDAOImpl extends utils implements CourseDAOInterface {

    /**
     * Takes the data from SQL and it converts it into a list (Plus ID)
     *
     * @return It returns a list of Courses ready to use
     */
    @Override
    public List<Course> getCourses() {
        String query = "SELECT * FROM course";
        List<Course> _allCourses = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int _idCourse = rs.getInt("ID_COURSE");
                String _title = rs.getString("TITLE");
                String _stream = rs.getString("STREAM");
                String _type = rs.getString("TYPE");
                java.sql.Date _startDate = rs.getDate("START_DATE");
                java.sql.Date _endDate = rs.getDate("END_DATE");
                Course c = new Course(_idCourse, _title, _stream, _type, _startDate, _endDate);
                _allCourses.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _allCourses;
    }

    /**
     * This method asks for the informations of the course after that it returns
     * a new course NOTE* for 'type' asks only for '1' or '2' and it converts it
     * into a String to avoid user's wrongs
     *
     * @return
     */
    @Override
    public Course createCourse() {
        String _stringType;

        System.out.println("Please enter the title of the course :");
        String _title = _reader.nextLine();
        System.out.println("Please enter the stream of the course:");
        String _stream = _reader.nextLine();
        System.out.println("Please enter the type of the course (Type '1' for Part Time or type '2' for Full Time)");
        int _type = protectNumber();
        while (_type != 1 && _type != 2) {
            System.out.println("Please enter '1' or '2'");
            _type = protectNumber();
        }
        System.out.println("Please enter the start date of the course (Format yyyy-MM-dd)");
        java.sql.Date _start_date = correctDate();
        System.out.println("Please enter the end date of the course (Format yyyy-MM-dd)");
        java.sql.Date _end_date = correctDate();
        if (_type == 1) {
            _stringType = "Part Time";
        } else {
            _stringType = "Full Time";
        }
        return new Course(_title, _stream, _stringType, _start_date, _end_date);
    }

    /**
     * This method is inserting a course in to the databse (ID is Auto
     * implemented)
     *
     * @param _course It asks for a course so you can call this method with the
     * method CreateCourse as parametr for example insertCourse(createCourse());
     */
    @Override
    public void insertCourse(Course _course) {
        String query = "insert into course (TITLE, STREAM, TYPE, START_DATE, END_DATE) values (?,?,?,?,?)";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, _course.getTitle());
            ps.setString(2, _course.getStream());
            ps.setString(3, _course.getType());
            ps.setDate(4, (Date) _course.getStartDate());
            ps.setDate(5, (Date) _course.getStartDate());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is taking an ID as parametr and deletes the ID from foreign
     * keys and from db at all
     *
     * @param _courseId
     */
    public void deleteCourse(int _courseId) {
        String query = "DELETE FROM course WHERE ID_COURSE=?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _courseId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets all students in a spesific course 
     * @param _courseId
     * The course you want to check
     * @return 
     * A list of students that exists at the given course
     */
    @Override
    public List<Student> getStudentPerCourse(int _courseId) {
        String query = "SELECT student.*\n"
                + "FROM student,watches\n"
                + "WHERE student.ID_STUDENT=watches.ID_STUDENT\n"
                + "AND watches.ID_COURSE=?;";
        List<Student> _allStudents = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _courseId);
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
     * Gets all trainers in a spesific course 
     * @param _courseId
     * The course you want to check
     * @return 
     * A list of trainers that exists at the given course
     */
    @Override
    public List<Trainer> getTrainerPerCourse(int _courseId) {
        String query = "SELECT trainer.*\n"
                + "FROM trainer,training\n"
                + "WHERE trainer.ID_TRAINER=training.ID_TRAINER\n"
                + "AND training.ID_COURSE=?";
        List<Trainer> _allTrainers = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int _idTrainer = rs.getInt("ID_TRAINER");
                String _firstName = rs.getString("FIRST_NAME");
                String _lastName = rs.getString("LAST_NAME");
                String _subj = rs.getString("SUBJECT");
                Trainer _trainer = new Trainer(_idTrainer, _firstName, _lastName, _subj);
                _allTrainers.add(_trainer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _allTrainers;
    }
    /**
     * Gets all course assignments  in a spesific course 
     * @param _courseId
     * The course you want to check
     * @return 
     * A list of group assignments that exists at the given course
     */
    @Override
    public List<GroupAssign> getAssignPerCourse(int _courseId) {
        String query = "SELECT course_assign.*\n" +
"FROM course_assign,team_implement\n" +
"WHERE course_assign.ID_COURSE_ASSIGN=team_implement.ID_COURSE_ASSIGN\n" +
"AND team_implement.ID_COURSE=?;";
        List<GroupAssign> _allAssignment = new ArrayList();
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _courseId);
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
     * Gets all course assignments  per student in a spesific course 
     * @param _courseId
     * The course you want to check
     * @return 
     * A list assignments that exists at the given course
     */
    @Override
    public List<AssPerCourPerStDTO> getAssPerStPerCourse(int _courseId) {
        ArrayList<AssPerCourPerStDTO> _assignPerCourPerSt = new ArrayList();
        String query = "SELECT student.FIRST_NAME,student.LAST_NAME,TITLE,DESCR,person_implement.STUDENT_ORAL_MARK,person_implement.STUDENT_TOTAL_MARK\n" +
"FROM student,watches,assignment,person_implement\n" +
"WHERE student.ID_STUDENT=watches.ID_STUDENT\n" +
"AND student.ID_STUDENT= person_implement.ID_STUDENT\n" +
"AND assignment.ID_ASSIGNMENT=person_implement.ID_ASSIGNMENT\n" +
"AND watches.ID_COURSE=?;";
        ResultSet rs = null;

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String _firstName = rs.getString("FIRST_NAME");
                String _lastName = rs.getString("LAST_NAME");
                String _title = rs.getString("TITLE");
                String _descr = rs.getString("DESCR");
                int _oralMark = rs.getInt("STUDENT_ORAL_MARK");
                int _totalMark = rs.getInt("STUDENT_TOTAL_MARK");
                AssPerCourPerStDTO _assignPerSt = new AssPerCourPerStDTO(_firstName, _lastName, _title, _descr, _oralMark, _totalMark);
                _assignPerCourPerSt.add(_assignPerSt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _assignPerCourPerSt;
    }
}
