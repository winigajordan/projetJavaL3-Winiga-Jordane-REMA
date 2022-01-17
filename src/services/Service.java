/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.AdminDao;
import dao.ConstantesDao;
import dao.ConsultationDao;
import dao.MedecinDao;
import dao.MedicamentDao;
import dao.OrdMedDao;
import dao.OrdonnanceDao;
import dao.PatientDao;
import dao.PrestationDao;
import dao.RdvDao;
import dao.RpDao;
import dao.SecretaireDao;
import dao.SpecialiteDao;
import dao.UserDao;
import dao.TypePrestationDao;
import dto.ConsultationDto;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author user
 */
public class Service implements IService{
    
    //gerer les dépendances avec la couche DAO
    UserDao daoUser=new UserDao();
    PatientDao daoPatient = new PatientDao();
    TypePrestationDao typeDao = new TypePrestationDao();
    SpecialiteDao speDao = new SpecialiteDao();
    RdvDao rdvDao = new RdvDao();
    PrestationDao prestationDao = new PrestationDao();
    ConsultationDao consultationDao = new ConsultationDao();
    MedecinDao medecinDao = new MedecinDao(); 
    ConstantesDao constantesDao = new ConstantesDao();
    MedicamentDao medicamentDao = new MedicamentDao();
    OrdMedDao ordMedDao = new OrdMedDao();
    OrdonnanceDao ordonnanceDao = new OrdonnanceDao();
    SecretaireDao secretaireDao  = new SecretaireDao();
    RpDao rpDao = new RpDao();
    AdminDao adminDao = new AdminDao();

    @Override
    public User login(String login, String password) {
     return daoUser.findUserLoginAndPassword(login, password);
      //return null;
    }
    
    public int searchByNci (int nci)
    {
        return daoUser.searchByNci(nci);
    }
    
    public int addPatient(Patient patient)
    {
        //int x = daoPatient
        return daoPatient.insert(patient);
        //return 0;
    }
    
    

    @Override
    public int askRdv(Rdv rdv) {
        return rdvDao.insert(rdv);
    }

    @Override
    public int createAccount(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    @Override
    public List<TypePrestation> showAllType()
    {
        return typeDao.findAll();
    
    }
    
    
    @Override
    public List<Specialite> showAllSpecialisation()
    {
        return speDao.findAll();
    
    }

    @Override
    public List<Rdv> showMyRdv(int patient_nci) {
        return rdvDao.findAllRdv(patient_nci);
    }

    @Override
    public List<PrestationDto> showPrestationToPatient(int patient_nci) {
        return prestationDao.findByNci(patient_nci);
    }

    @Override
    public List<ConsultationDto> showConsultationToPatient(int patient_nci) {
        return consultationDao.findByNci(patient_nci);
    }

    @Override
    public List<ConsultationDto> showConsultationDetailsToPatient(int patient_nci) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RdvDto> showConsultationToSecretaire() {
        return rdvDao.findRdvConsultation();
    }

    @Override
    public List<RdvDto> showPrestationsToSecretaire() {
        return rdvDao.findRdvPrestation();
    }

    @Override
    public int updateRdv(int rdv_id) {
        //return rdvDao.updateEtat(int rdv_id);
        //System.out.println(rdvDao.updateEtat(3));
        return rdvDao.updateEtat(rdv_id);
        
    }

    @Override
    public int createPrestation(Prestation prestation) {
        return prestationDao.insert(prestation);
    }

    @Override
    public List<Medecin> showMedecin(String specialite) {
        List <Medecin> med = new ArrayList();
        List <Medecin> medecins = medecinDao.findAll();
        List <Specialite> specialites = showAllSpecialisation();
        for (Specialite s:specialites)
        {
            if(s.getLibelle().equals(specialite))
            {
                for (Medecin m : medecins){
                    if (m.getSpecialite_id()==s.getId())
                    {
                        med.add(m);
                    }
                    
                }
            }
        }
        return med;
    }

    @Override
    public int createConsultation(Consultation consultation) {
        return consultationDao.insert(consultation);
    }

    @Override
    public boolean checkRdvInPrestation(int id) {
        List <Prestation> prestations = prestationDao.findAll();
        boolean statut = false;
        for(Prestation p : prestations) {
            if (p.getRdvId() == id)
            {
                statut = true;
            }
        }
        return statut;
    }

    @Override
    public boolean checkRdvInConsultation(int id) {
        List <Consultation> consultations = consultationDao.findAll();
        boolean statut = false;
        for(Consultation c : consultations) {
            if (c.getRdvId() == id)
            {
                statut = true;
            }
        }
        return statut;
    }

    @Override
    public List<Consultation> showConsultationToMedecin(int medecin_nci) {
        return consultationDao.findByNciMedecin(medecin_nci);
    }

    @Override
    public int deleteConsultation(int idConsultation) {
        int idModifie = consultationDao.updateStatut(idConsultation);
        
        return idModifie;
    }

    @Override
    public int insertConstantes(Constantes c) {
    return constantesDao.insert(c);
    }

    @Override
    public int saveConsultation(int idConsultation,int idRdv, int idConsante) {
        return consultationDao.updateStatutFait(idConsultation,idRdv, idConsante);
    }

    @Override
    public List<Medicament> findAllMedocs() {
        return medicamentDao.findAll();
    }

    @Override
    public int insertOrdMedList(OrdMed ord) {
        return ordMedDao.insert(ord);
    }

    @Override
    public int createOrdonance(Ordonnance ord) {
        return ordonnanceDao.insert(ord);
    }

    @Override
    public List<PrestationDto> showPrestationToRp() {
        return prestationDao.returnPrestationToRp();
    }

    @Override
    public List<Date> returnDate() {
        return prestationDao.findDate();
    }

    @Override
    public List<PrestationDto> showPrestationToRpByDate(Date date) {
        return prestationDao.returnPrestationToRpByDate(date);
    }
    
    @Override
    public void annulationPrestation(int id) {
        prestationDao.annulationPrestation(id);
    }

    @Override
    public void validatePrestation(int id, String resultat) {
        prestationDao.updateConsultation(id, resultat);
    }

    @Override
    public List<User> showUsers() {
        return daoUser.findAll();
    }

    @Override
    public int addMed(Medecin med) {
        return medecinDao.insert(med);
    }

    @Override
    public int addSecretaire(Secretaire sc) {
        return secretaireDao.insert(sc);
    }
    
    @Override
    public int addRp(Rp rp){
        return rpDao.insert(rp);
    }
    
    @Override
    public int addAdmin(Admin admin){
        return adminDao.insert(admin);
    }
    

    
    
}
