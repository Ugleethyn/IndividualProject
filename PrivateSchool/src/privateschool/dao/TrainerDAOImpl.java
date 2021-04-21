/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import privateschool.models.Trainer;
import utils.DbConnection;
import utils.utils;

/**
 *
 * @author Ugleethyn
 */
public class TrainerDAOImpl extends utils implements TrainerDAOInterface {

    
        /**
     * Takes the data from SQL and it converts it into a list (Plus ID)
     *
     * @return It returns a list of Trainers ready to use
     */
    @Override
    public List<Trainer> getTrainers() {
        String query = "SELECT * FROM trainer";
        List<Trainer> _allTrainers = new ArrayList();
        ResultSet rs = null;

        try(Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int _idTrainer = rs.getInt("ID_TRAINER");
                String _firstName = rs.getString("FIRST_NAME");
                String _lastName = rs.getString("LAST_NAME");
                String _subj = rs.getString("SUBJECT");
                Trainer _trainer = new Trainer(_idTrainer, _firstName, _lastName, _subj);
                _allTrainers.add(_trainer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return _allTrainers;
    }

        /**
     * This method asks for the informations of the trainer after that it returns a
     * new trainer without ID
     * @return
     */
    @Override
    public Trainer createTrainer() {
        System.out.println("Please enter the first name of the trainer :");
        String _firstName = _reader.nextLine();
        System.out.println("Please enter the last name of the trainer");
        String _lastName = _reader.nextLine();
        System.out.println("Please enter the subject of the trainer");
        String _subj = _reader.nextLine();
        return new Trainer(_firstName, _lastName, _subj);
    }

    
        /**
     * This method is inserting the trainer in to the databse (ID is Auto
     * implemented)
     *
     * @param _trainer It asks for a trainer so you can call this method with the
     * method createTrainer as parametr for example insertTrainer(createTrainer());
     */
    @Override
    public void insertTrainer(Trainer _trainer) {
        String query = "insert into trainer (FIRST_NAME, LAST_NAME, SUBJECT) values (?,?,?)";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, _trainer.getFirstName());
            ps.setString(2, _trainer.getLastName());
            ps.setString(3, _trainer.getSubj()); 
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is taking an ID as parametr and deletes the ID from foreign
     * x); The check of the ID is taking place in another method named
     * checkTrainer();
     *
     * @param _trainerId
     */
    @Override
    public void deleteTrainer(int _trainerId) {
        String query = "DELETE FROM trainer WHERE ID_TRAINER=?";
        try(Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _trainerId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

/**
 * This method adds a trainer to a course
 * @param _trainerId
 * It asks for the id of the trainer you want to insert
 * @param _courseID 
 * And the Id of the course too
 */
    public void addToCourse(int _trainerId , int _courseID) {
       String query = "insert into training (ID_TRAINER, ID_COURSE) values (?,?)";
               try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, _trainerId);
            ps.setInt(2, _courseID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
