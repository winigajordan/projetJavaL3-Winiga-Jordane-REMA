/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dto.ConsultationDto;
import dto.OrdonnanceDto;
import dto.PrestationDto;
import dto.RdvDto;
import entities.Admin;
import entities.Constantes;
import entities.Consultation;
import entities.Medecin;
import entities.Medicament;
import entities.OrdMed;
import entities.Ordonnance;
import entities.Patient;
import entities.Prestation;
import entities.Rdv;
import entities.Rp;
import entities.Secretaire;
import entities.Specialite;
import entities.TypePrestation;
import entities.User;
import java.util.List;

/**
 *
 * @author user
 */
public interface IService {
    
    //se connecter
     public User login(String login,String password);
     
     //Patient
     public int askRdv(Rdv rdv);  //
     public int createAccount(User user); //
     public List<ConsultationDto> showConsultationToPatient(int patient_nci);
     public List<ConsultationDto> showConsultationDetailsToPatient(int patient_nci);
     public List<PrestationDto> showPrestationToPatient(int patient_nci); //
     public List<Specialite> showAllSpecialisation(); //
     public List<TypePrestation> showAllType(); //
     public List<Rdv> showMyRdv(int patient_nci); //
     //public List<>
     
     
     //secretaire
     public List<RdvDto> showConsultationToSecretaire();
     public List<RdvDto> showPrestationsToSecretaire();
     public int updateRdv (int rdv_id);
     public int createPrestation(Prestation prestation);
     public List <Medecin> showMedecin(String specialite);
     public int createConsultation(Consultation consultation);
     public boolean checkRdvInPrestation(int id);
     public boolean checkRdvInConsultation(int id);
     
     
     //Medecin
     public List <Consultation> showConsultationToMedecin(int medecin_nci);
     public int deleteConsultation(int idConsultation);
     public int insertConstantes(Constantes c);
     public int saveConsultation(int idConsultation,int idRdv, int idConstante);
     public List<Medicament> findAllMedocs();
     public int createOrdonance(Ordonnance ord);
     public int insertOrdMedList(OrdMed ord);
     public List <Patient> findAllPatiens();
     public Constantes getConstante(int id);
     public List <OrdonnanceDto> getOrdonnance(int consultationId);
     
     
     
     //RP
     public List <PrestationDto> showPrestationToRp();
     public List<java.sql.Date> returnDate();
     public List <PrestationDto> showPrestationToRpByDate(java.sql.Date date);
     public void annulationPrestation(int id);
     public void validatePrestation(int id, String resultat);
     public PrestationDto getPrestationByConsultation(int rdvId);
     
     
     //Admin
     public List<User> showUsers();
     public int addMed(Medecin med);
     public int addSecretaire (Secretaire sc);
     public int addRp(Rp rp);
     public int addAdmin(Admin admin);
     public int deleteUser(int id);
     
     
    
}
