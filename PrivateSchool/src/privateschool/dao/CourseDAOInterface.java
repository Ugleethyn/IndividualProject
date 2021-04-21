/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.dao;

import java.util.List;
import privateschool.dto.AssPerCourPerStDTO;
import privateschool.models.Course;
import privateschool.models.GroupAssign;
import privateschool.models.Student;
import privateschool.models.Trainer;

/**
 *
 * @author Ugleethyn
 */
public interface CourseDAOInterface {

    public List<Course> getCourses();

    public Course createCourse();

    public void insertCourse(Course c);

    public List<Student> getStudentPerCourse(int _courseId);

    public List<Trainer> getTrainerPerCourse(int _courseId);

    public List<GroupAssign> getAssignPerCourse(int _courseId);

    public List<AssPerCourPerStDTO> getAssPerStPerCourse(int _courseId);

    public void deleteCourse(int _courseId);

}
