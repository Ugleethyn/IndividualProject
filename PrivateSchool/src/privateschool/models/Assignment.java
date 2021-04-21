/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.models;

import java.sql.Date;
/**
 *
 * @author Ugleethyn
 */
public class Assignment {

    private int _assignId;
    private String _title;
    private String _descr;
    private Date _subDate;
    private int _oralMark;
    private int _totalMark;

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-15s %-20s %-20s %-10s %-5s ", _assignId, _title, _descr, _subDate, _oralMark, _totalMark);
    }

    //Constructors
    public Assignment() {
    }

    public Assignment(String _title, String _descr, Date _subDate, int _oralMark, int _totalMark) {
        this._title = _title;
        this._descr = _descr;
        this._subDate = _subDate;
        this._oralMark = _oralMark;
        this._totalMark = _totalMark;
    }

    public Assignment(int _assignId, String _title, String _descr, Date _subDate, int _oralMark, int _totalMark) {
        this._assignId = _assignId;
        this._title = _title;
        this._descr = _descr;
        this._subDate = _subDate;
        this._oralMark = _oralMark;
        this._totalMark = _totalMark;
    }

    //Getters & Setters 
    public int getAssignId() {
        return _assignId;
    }

    public void setAssignId(int _assignId) {
        this._assignId = _assignId;
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

    public Date getSubDate() {
        return _subDate;
    }

    public void setSubDate(Date _subDate) {
        this._subDate = _subDate;
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
