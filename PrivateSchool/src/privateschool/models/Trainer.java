/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.models;

/**
 *
 * @author Ugleethyn
 */
public class Trainer {
    
    private int _trainerId;
    private String _firstName;
    private String _lastName;
    private String _subj;



    @Override
    public String toString() {
       return String.format("%-5s %-10s %-10s %-1s ", _trainerId, _firstName, _lastName, _subj);
    }
    
    // Constructors
    public Trainer(int _trainerId, String _firstName, String _lastName, String _subj) {
        this._trainerId = _trainerId;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._subj = _subj;
    }

    public Trainer(String _firstName, String _lastName, String _subj) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._subj = _subj;
    }

    
    
    public Trainer() {
    }
    
    //Getters && Setters
    public int getTrainerId() {
        return _trainerId;
    }

    public void setTrainerId(int _trainerId) {
        this._trainerId = _trainerId;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String _fistName) {
        this._firstName = _fistName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String getSubj() {
        return _subj;
    }

    public void setSubj(String _subj) {
        this._subj = _subj;
    }
    
    
}
