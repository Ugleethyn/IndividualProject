/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.dto;

/**
 *
 * @author Ugleethyn
 */
public class AssignToStudentDTO {
    
    private int _studentId;
    private String _firstName;
    private String _lastName;
    private int _assignmentId;
    private String _title;
    private String _descr;
    private int _oralMark;
    private int _totalMark;

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-18s %-15s %-23s %-20s %-3s", _studentId, _firstName, _lastName, _assignmentId, _title, _descr,_oralMark,_totalMark );
    }
    
    
    
    //Constructors

    public AssignToStudentDTO(int _studentId, String _firstName, String _lastName, int _assignmentId, String _title, String _descr, int _oralMark, int _totalMark) {
        this._studentId = _studentId;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._assignmentId = _assignmentId;
        this._title = _title;
        this._descr = _descr;
        this._oralMark = _oralMark;
        this._totalMark = _totalMark;
    }
    
    //Getters & Setters

    public int getStudentId() {
        return _studentId;
    }

    public void setStudentId(int _studentId) {
        this._studentId = _studentId;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    public int getAssignmentId() {
        return _assignmentId;
    }

    public void setAssignmentId(int _assignmentId) {
        this._assignmentId = _assignmentId;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public String getDescr() {
        return _descr;
    }

    public void setDescr(String _descr) {
        this._descr = _descr;
    }

    public int getOralMark() {
        return _oralMark;
    }

    public void setOralMark(int _oralMark) {
        this._oralMark = _oralMark;
    }

    public int getTotalMark() {
        return _totalMark;
    }

    public void setTotalMark(int _totalMark) {
        this._totalMark = _totalMark;
    }
    
    
}
