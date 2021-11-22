/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ConsultationDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConsultationDao implements IDao {
    
    DataBase database = new DataBase();
    private final String SQL_SELECT_BY_NCI_PATIENT ="SELECT c.id, c.date, s.libelle_specialite, c.statut FROM consultation as c, specialite as s where s.id=c.specialite_id and c.patient_id = ?";

    @Override
    public int insert(Object ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Object ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List <ConsultationDto> findByNci(int nci_patient) 
    {
        List<ConsultationDto> consultations = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_BY_NCI_PATIENT);
        try {
            database.getPs().setInt(1, nci_patient);
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_NCI_PATIENT);
            while (rs.next())
            {
                ConsultationDto consultationDto = new ConsultationDto(
                rs.getInt("id"),
                rs.getDate("date"),
                rs.getString("libelle_specialite"),
                rs.getString("statut")
                );
                consultations.add(consultationDto);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return consultations;
    }
    
    
}
