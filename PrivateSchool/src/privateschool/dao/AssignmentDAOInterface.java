/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.dao;

import java.util.List;

/**
 *
 * @author Ugleethyn
 */
public interface AssignmentDAOInterface <E> {

    public List<E> getAssignment();
    
    public List<E> getAssignmentPer();

    public E createAssignment();
    
    public void insertAssignment(Object _assignment);

    public void deleteAssignment(int _assignmentId);

    public void addTo(int _assignmentId, int _studentId);

    public int getMaxOral(int _assignmentId);

    public int getMaxTotal(int _assignmentId);

    public void setOralMark(int _mark, int _assignmentId, int _studentId);

    public void setTotalMark(int _mark, int _assignmentId, int _studentId);
             
             
}
