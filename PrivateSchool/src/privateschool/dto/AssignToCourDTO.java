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
public class AssignToCourDTO {
    private int _courseId;
    private String _title;
    private String _stream;
    private String _type;
    private int _assId;
    private String _assTitle;
    private String _descr;
    private int _oralMark;
    private int _totalMark;


    @Override
    public String toString() {
        return String.format("%2s %10s %10s %2s %15s %15s %3s %3s", _courseId, _title, _stream,_type, _assId, _title, _descr,_oralMark,_totalMark );
    }

    public AssignToCourDTO(int _courseId, String _title, String _stream, String _type, int _assId, String _assTitle, String _descr, int _oralMark, int _totalMark) {
        this._courseId = _courseId;
        this._title = _title;
        this._stream = _stream;
        this._type = _type;
        this._assId = _assId;
        this._assTitle = _assTitle;
        this._descr = _descr;
        this._oralMark = _oralMark;
        this._totalMark = _totalMark;
    }

    
    
    
    public AssignToCourDTO() {
    }
    
    //Getters&Setters

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

    public int getAssId() {
        return _assId;
    }

    public void setAssId(int _assId) {
        this._assId = _assId;
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
