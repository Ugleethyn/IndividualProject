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
public class GroupAssign {

    private int _groupAssID;
    private String _title;
    private String _descr;
    private Date _subDate;
    private int _oralMark;
    private int _totalMark;

    @Override
    public String toString() {
        return String.format("%-5s %-15s %-20s %-20s %-10s %-5s ", _groupAssID, _title, _descr, _subDate, _oralMark, _totalMark);
    }

    
    
    //Constructors
    public GroupAssign() {
    }

    public GroupAssign(int _groupAssID, String _title, String _descr, Date _subDate, int _oralMark, int _totalMark) {
        this._groupAssID = _groupAssID;
        this._title = _title;
        this._descr = _descr;
        this._subDate = _subDate;
        this._oralMark = _oralMark;
        this._totalMark = _totalMark;
    }

    public GroupAssign(String _title, String _descr, Date _subDate, int _oralMark, int _totalMark) {
        this._title = _title;
        this._descr = _descr;
        this._subDate = _subDate;
        this._oralMark = _oralMark;
        this._totalMark = _totalMark;
    }
    
    

    //Getter & Setter
    public int getGroupAssID() {
        return _groupAssID;
    }

    public void setGroupAssID(int _groupAssID) {
        this._groupAssID = _groupAssID;
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
