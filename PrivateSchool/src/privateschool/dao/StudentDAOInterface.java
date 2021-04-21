/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.dao;

import java.util.List;
import privateschool.models.Student;

/**
 *
 * @author Ugleethyn
 */
public interface StudentDAOInterface {

    public List<Student> getStudents();

    public Student createStudent();

    public void insertStudnet(Student _student);

    public void deleteStudent(int _studentId);

    public void addToCourse(int _studentId, int _courseID);

}
