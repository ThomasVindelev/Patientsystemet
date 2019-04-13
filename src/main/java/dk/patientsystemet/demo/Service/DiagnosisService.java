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

    public List<Diagnosis> getDiagnosisByPatient(int id, int choice) throws SQLException {
        ResultSet rs = db.getDiagnosisById(id, choice);
        List<Diagnosis> diagnosisList = new ArrayList<>();
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
    }

    public List<Medicine> getMedicineByDiagnosis(int id) throws SQLException {
        ResultSet rs = db.getMedicineByDiagnosis(id);
        List<Medicine> medicineList = new ArrayList<>();
        while (rs.next()) {
            Medicine medicine = new Medicine();
            medicine.setId(rs.getInt("medicine.id"));
            medicine.setName(rs.getString("medicine.name"));
            medicineList.add(medicine);
        }
        return medicineList;
    }

    public List<Diagnosis> getDiagnosis() throws SQLException {
        List<Diagnosis> diagnosisList = new ArrayList<>();
        ResultSet rs = db.getDiagnosis();
        while (rs.next()) {
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setId(rs.getInt("id"));
            diagnosis.setDiagnosisName(rs.getString("name"));
            diagnosisList.add(diagnosis);
        }
        return  diagnosisList;
    }

    public List<Diagnosis> getNewDiagnosis() throws SQLException {
        List<Diagnosis> diagnosisList = new ArrayList<>();
        ResultSet rs = db.getNewDiagnosis();
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
    }

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

    public void newDiagnosis(Diagnosis diagnosis, int patientID, int userID) throws SQLException {
        db.createDiagnosis(diagnosis, patientID, userID);
    }

    public void newUnknownDiagnosis(String diagnosisName) throws SQLException {
        db.createUnknownDiagnosis(diagnosisName);
    }

    public void deleteDiagnosis(int id) throws SQLException {
        db.deleteDiagnosis(id);
    }

}
