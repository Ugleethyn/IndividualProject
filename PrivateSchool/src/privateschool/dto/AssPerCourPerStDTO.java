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
public class AssPerCourPerStDTO {

    private String _firstName;
    private String _lastName;
    private String _title;
    private String _descr;
    private int _oralMark;
    private int _totalMark;

    @Override
    public String toString() {
       return String.format("%-15s %-15s %-15s %-20s %-15s %-5s", _firstName, _lastName, _title,_descr, _oralMark, _totalMark );
    }

    public AssPerCourPerStDTO(String _firstName, String _lastName, String _title, String _descr, int _oralMark, int _totalMark) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._title = _title;
        this._descr = _descr;
        this._oralMark = _oralMark;
        this._totalMark = _totalMark;
    }

}
