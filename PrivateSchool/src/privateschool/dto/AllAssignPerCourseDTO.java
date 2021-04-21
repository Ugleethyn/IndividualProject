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
public class AllAssignPerCourseDTO {
    private String _courseTitle;
    private String _stream;
    private String _type;
    private String _assignTitle;
    private String _descr;
    private int _studentOralMark;
    private int _studentTotalMark;

    @Override
    public String toString() {
 return String.format("%-15s %-15s %-15s %-15s %-25s %-20s %-5s", _courseTitle, _stream, _type,_assignTitle, _descr, _studentOralMark,_studentTotalMark );
    }
    
    
    
    //Constructor

    public AllAssignPerCourseDTO(String _courseTitle, String _stream, String _type, String _assignTitle, String _descr, int _studentOralMark, int _studentTotalMark) {
        this._courseTitle = _courseTitle;
        this._stream = _stream;
        this._type = _type;
        this._assignTitle = _assignTitle;
        this._descr = _descr;
        this._studentOralMark = _studentOralMark;
        this._studentTotalMark = _studentTotalMark;
    }
    
    
    //Getters & Setters

    public String getCourseTitle() {
        return _courseTitle;
    }

    public void setCourseTitle(String _courseTitle) {
        this._courseTitle = _courseTitle;
    }

    public String getStream() {
        return _stream;
    }

    public void setStream(String _stream) {
        this._stream = _stream;
    }

    public String getType() {
        return _type;
    }

    public void setType(String _type) {
        this._type = _type;
    }

    public String getAssignTitle() {
        return _assignTitle;
    }

    public void setAssignTitle(String _assignTitle) {
        this._assignTitle = _assignTitle;
    }

    public String getDescr() {
        return _descr;
    }

    public void setDescr(String _descr) {
        this._descr = _descr;
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
