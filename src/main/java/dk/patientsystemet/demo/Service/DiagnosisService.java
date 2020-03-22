package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.Diagnosis;
import dk.patientsystemet.demo.Model.Medicine;
import dk.patientsystemet.demo.Repositories.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosisService {

    @Autowired
    DiagnosisRepository db;

    /**
     Gets all diagnoses assigned to a specific patient
     */

    public List<Diagnosis> getDiagnosisByPatient(int id, int choice) {
        ResultSet rs = db.getDiagnosisById(id, choice);
        List<Diagnosis> diagnosisList = new ArrayList<>();
        try {
            while (rs.next()) {
                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setId(rs.getInt("id"));
                diagnosis.setNote(rs.getString("note"));
                diagnosis.setDate(rs.getString("date"));
                diagnosis.setDiagnosisName(rs.getString("diagnosis_names.name"));
                diagnosis.setPatientName(rs.getString("patient.firstname"));
                diagnosis.setDoctorName(rs.getString("users.name"));
                diagnosis.setNameID(rs.getInt("diagnosis_names.id"));
                diagnosisList.add(diagnosis);
            }
            return diagnosisList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Gets all effective medicine to a diagnosis
     */

    public List<Medicine> getMedicineByDiagnosis(int id) {
        ResultSet rs = db.getMedicineByDiagnosis(id);
        List<Medicine> medicineList = new ArrayList<>();
        try {
            while (rs.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(rs.getInt("medicine.id"));
                medicine.setName(rs.getString("medicine.name"));
                medicineList.add(medicine);
            }
            return medicineList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Returns a list of all diagnoses
     */

    public List<Diagnosis> getDiagnosis() {
        List<Diagnosis> diagnosisList = new ArrayList<>();
        ResultSet rs = db.getDiagnosis();
        try {
            while (rs.next()) {
                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setId(rs.getInt("id"));
                diagnosis.setDiagnosisName(rs.getString("name"));
                diagnosisList.add(diagnosis);
            }
            return  diagnosisList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Returns a list of the five most recently assigned diagnoses
     */

    public List<Diagnosis> getNewDiagnosis() {
        List<Diagnosis> diagnosisList = new ArrayList<>();
        ResultSet rs = db.getNewDiagnosis();
        try {
            while (rs.next()) {
                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setId(rs.getInt("id"));
                diagnosis.setDiagnosisName(rs.getString("name"));
                diagnosis.setPatientName(rs.getString("firstname"));
                diagnosis.setDoctorName(rs.getString("users.name"));
                diagnosis.setNameID(rs.getInt("diagnosis_names.id"));
                diagnosisList.add(diagnosis);
            }
            return  diagnosisList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Gets all relevant information about one specific diagnosis
     */

    public Diagnosis viewDiagnosis(int id) {
        Diagnosis diagnosis = new Diagnosis();
        try {
            ResultSet rs = db.getDiagnosisById(id, 2);
            while (rs.next()) {
                diagnosis.setId(rs.getInt("id"));
                diagnosis.setNote(rs.getString("note"));
                diagnosis.setDate(rs.getString("date"));
                diagnosis.setDiagnosisName(rs.getString("diagnosis_names.name"));
                diagnosis.setPatientName(rs.getString("patient.firstname"));
                diagnosis.setDoctorName(rs.getString("users.name"));
                diagnosis.setNameID(rs.getInt("diagnosis.fk_diagnosis_names"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosis;
    }

    /**
     Assigns new diagnosis to patient
     */

    public void newDiagnosis(Diagnosis diagnosis, int patientID, int userID) {
        db.createDiagnosis(diagnosis, patientID, userID);
    }

    /**
     Creates diagnosis not already in database
     */

    public void newUnknownDiagnosis(String diagnosisName) {
        db.createUnknownDiagnosis(diagnosisName);
    }

    /**
     Deletes an assigned diagnosis
     */

    public void deleteDiagnosis(int id) {
        db.deleteDiagnosis(id);
    }

}

