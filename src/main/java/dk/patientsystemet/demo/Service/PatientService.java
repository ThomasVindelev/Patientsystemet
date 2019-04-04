package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.Patient;
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
            patient.setNote(rs.getString("note"));
            patientList.add(patient);
        }
        return patientList;
    }

    public Patient findPatient(Patient patient) throws SQLException {
        ResultSet rs = db.findPatient(patient);
        if (rs.next()) {
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

    public List<Patient> findPatientNote(int id) throws SQLException {
        ResultSet rs = db.findPatientNote(id);
        List<Patient> noteList = new ArrayList<>();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setNote(rs.getString("note"));
            patient.setNoteId(rs.getInt("id"));
            noteList.add(patient);
        }
        return noteList;
    }



    public String createNote(Patient patient) throws SQLException {
        db.createNote(patient);
        return "Success!";
    }
}
