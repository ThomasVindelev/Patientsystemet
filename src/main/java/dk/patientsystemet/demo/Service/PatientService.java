package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.Diagnosis;
import dk.patientsystemet.demo.Model.Note;
import dk.patientsystemet.demo.Model.Patient;
import dk.patientsystemet.demo.Model.User;
import dk.patientsystemet.demo.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository db;

    public String createPatient(Patient patient) throws SQLException {
        if (patient.getFirstName().length() <= 1 || patient.getLastName().length() <= 1) {
            return "First or last name length is too short.";
        } else if (patient.getHeight() < 0) {
            return "Not a valid height";
        } else if (patient.getWeight() < 0) {
            return "Not a valid weight";
        } else if (patient.getCpr() < 0 || patient.getCpr() > 9999) {
            return "CPR number not valid";
        }

        else {
            db.createPatient(patient);
            return "Success";
        }
    }

    public List<Patient> fetchAll() throws SQLException {
        ResultSet rs = db.allPatients();
        List<Patient> patientList = new ArrayList<>();
        while (rs.next()) {
            Patient patient = new Patient(

            );
            patient.setId(rs.getInt("id"));
            patient.setFirstName(rs.getString("firstname"));
            patient.setLastName(rs.getString("lastname"));
            patientList.add(patient);
        }
        return patientList;
    }

    public int searchPatient(Patient patient) throws SQLException {
        ResultSet rs = db.searchPatient(patient);
        if (rs.next()) {
            return rs.getInt("patient.id");
        } else {
            return 0;
        }
    }

    public Patient findPatient(int id) throws SQLException {
        ResultSet rs = db.findPatient(id);
        if (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getInt("patient.id"));
            patient.setFirstName(rs.getString("firstname"));
            patient.setLastName(rs.getString("lastname"));
            patient.setBirthDate(rs.getString("birthdate"));
            patient.setHeight(rs.getInt("height"));
            patient.setWeight(rs.getInt("weight"));
            patient.setCpr(rs.getInt("cpr"));
            patient.setNote(rs.getString("patient_note.note"));
            patient.setDate(rs.getString("date"));
            return patient;
        } else {
            return null;
        }
    }

    public List<Note> findPatientNote(int id) throws SQLException {
        ResultSet rs = db.findPatientNote(id);
        List<Note> noteList = new ArrayList<>();
        while (rs.next()) {
            Note note = new Note();
            note.setId(rs.getInt("id"));
            note.setNote(rs.getString("note"));
            noteList.add(note);
        }
        return noteList;
    }

    public String createNote(Patient patient, int id) throws SQLException {
        db.createNote(patient, id);
        return "Success!";
    }

    public List<Diagnosis> getDiagnosis(int id) throws SQLException {
        ResultSet rs = db.getDiagnosis(id);
        List<Diagnosis> diagnosisList = new ArrayList<>();
        while (rs.next()) {
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setNote(rs.getString("note"));
            diagnosis.setDate(rs.getString("date"));
            diagnosis.setDiagnosisName(rs.getString("diagnosis_names.name"));
            diagnosis.setPatientName(rs.getString("patient.firstname"));
            diagnosis.setDoctorName(rs.getString("users.name"));
            diagnosisList.add(diagnosis);
            System.out.println(diagnosis.getDiagnosisName());
        }
        return diagnosisList;
    }

    public String newDiagnosis(int id, Diagnosis diagnosis, User user) {
        db.createDiagnosis(id, diagnosis);
        return null;
    }
}
