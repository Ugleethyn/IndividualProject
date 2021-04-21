/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool.dao;

import java.util.List;
import privateschool.models.Trainer;

/**
 *
 * @author Ugleethyn
 */
public interface TrainerDAOInterface {
    
    public List<Trainer> getTrainers();

    public Trainer createTrainer();

    public void insertTrainer(Trainer _trainer);

    public void deleteTrainer(int _trainerId);

    public void addToCourse(int _trainerId, int _courseID);
    
}
