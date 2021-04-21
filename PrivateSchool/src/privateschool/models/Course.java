/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.models;

import java.util.Date;

/**
 *
 * @author Ugleethyn
 */
public class Course {

    private int _courseId;
    private String _title;
    private String _stream;
    private String _type;
    private java.sql.Date _startDate;
    private java.sql.Date _endDate;

    @Override
    public String toString() {
        return String.format("%-5s %-10s %-10s %-15s %-15s %-1s", _courseId, _title, _stream, _type, _startDate, _endDate);
    }

    //Constructors

    public Course(String _title, String _stream, String _type, java.sql.Date _startDate, java.sql.Date _endDate) {
        this._title = _title;
        this._stream = _stream;
        this._type = _type;
        this._startDate = _startDate;
        this._endDate = _endDate;
    }
    
    public Course() {
    }

    public Course(int _courseId, String _title, String _stream, String _type, java.sql.Date _startDate, java.sql.Date _endDate) {
        this._courseId = _courseId;
        this._title = _title;
        this._stream = _stream;
        this._type = _type;
        this._startDate = _startDate;
        this._endDate = _endDate;
    }

    //Getters & Setters
    public int getCourseId() {
        return _courseId;
    }

    public void setCourseId(int _courseId) {
        this._courseId = _courseId;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
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

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(java.sql.Date _startDate) {
        this._startDate = _startDate;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(java.sql.Date _endDate) {
        this._endDate = _endDate;
    }

}
