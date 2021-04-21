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
public class Student {
    
    private int _studentId;
    private String _firstName;
    private String _lastName;
    private Date _birthDate;
    private int _tuitionFees ;


    @Override
    public String toString() {
         return String.format("%-5s %-15s %-15s %-15s %-2s ", _studentId, _firstName, _lastName, _birthDate, _tuitionFees);
    }
    
    //Constructors
    public Student(int _studentId, String _firstName, String _lastName, Date _birthDate, int _tuitionFees) {
        this._studentId = _studentId;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._birthDate = _birthDate;
        this._tuitionFees = _tuitionFees;
    }
    public Student() {
    }

    public Student(String _firstName, String _lastName, Date _birthDate, int _tuitionFees) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._birthDate = _birthDate;
        this._tuitionFees = _tuitionFees;
    }
    
    
    //Getters& Setters
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

    public Date getBirthDate() {
        return _birthDate;
    }

    public void setBirthDate(Date _birthDate) {
        this._birthDate = _birthDate;
    }

    public int getTuitionFees() {
        return _tuitionFees;
    }

    public void setTuitionFees(int _tuitionFees) {
        this._tuitionFees = _tuitionFees;
    }
    
    
    
}
