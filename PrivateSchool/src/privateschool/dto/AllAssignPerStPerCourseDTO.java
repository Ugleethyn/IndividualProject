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
public class AllAssignPerStPerCourseDTO {
    private String _firstName;
    private String _lastName;
    private String _courseTitle;
    private String _courseStream;
    private String _courseType;
    private String _assignTitle;
    private String _assignDescr;
    private int _studentOralMark;
    private int _studentTotalMark;

    @Override
    public String toString() {
    return String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-25s %-20s %5s ", _firstName, _lastName, _courseTitle,_courseStream, _courseType, _assignTitle,_assignDescr, _studentOralMark,_studentTotalMark);
    }
    
    
    
    //Constructor

    public AllAssignPerStPerCourseDTO(String _firstName, String _lastName, String _courseTitle, String _courseStream, String _courseType, String _assignTitle, String _assignDescr, int _studentOralMark, int _studentTotalMark) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._courseTitle = _courseTitle;
        this._courseStream = _courseStream;
        this._courseType = _courseType;
        this._assignTitle = _assignTitle;
        this._assignDescr = _assignDescr;
        this._studentOralMark = _studentOralMark;
        this._studentTotalMark = _studentTotalMark;
    }
    
    
    //Getters & Setters

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

    public String getCourseTitle() {
        return _courseTitle;
    }

    public void setCourseTitle(String _courseTitle) {
        this._courseTitle = _courseTitle;
    }

    public String getCourseStream() {
        return _courseStream;
    }

    public void setCourseStream(String _courseStream) {
        this._courseStream = _courseStream;
    }

    public String getCourseType() {
        return _courseType;
    }

    public void setCourseType(String _courseType) {
        this._courseType = _courseType;
    }

    public String getAssignTitle() {
        return _assignTitle;
    }

    public void setAssignTitle(String _assignTitle) {
        this._assignTitle = _assignTitle;
    }

    public String getAssignDescr() {
        return _assignDescr;
    }

    public void setAssignDescr(String _assignDescr) {
        this._assignDescr = _assignDescr;
    }

    public int getStudentOralMark() {
        return _studentOralMark;
    }

    public void setStudentOralMark(int _studentOralMark) {
        this._studentOralMark = _studentOralMark;
    }

    public int getStudentTotalMark() {
        return _studentTotalMark;
    }

    public void setStudentTotalMark(int _studentTotalMark) {
        this._studentTotalMark = _studentTotalMark;
    }
    
    
}
