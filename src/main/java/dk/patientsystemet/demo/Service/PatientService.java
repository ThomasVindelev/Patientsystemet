package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.Diagnosis;
import dk.patientsystemet.demo.Model.Note;
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

    @Autowired
    Validate val;

    public String createPatient(Patient patient) throws SQLException {
        if(val.biggerOrEqualToNumber(patient.getFirstName(), 1)) {
            return "Error: First name has to be at least 2 characters";
        } else if(val.biggerOrEqualToNumber(patient.getLastName(), 1)) {
            return "Error: Last name has to be at least 2 characters";
        } else if(val.betweenInt(patient.getHeight(), 1, 300)) {
            return "Error: Not a valid height";
        } else if(val.betweenInt(patient.getWeight(), 1, 300)){
            return "Error: Not a valid weight";
        } else if(val.validateCPR(patient.getCpr())) {
            return "Error: CPR number not valid";
        } else {
            db.createPatient(patient);
            return "Success";
        }
    }

    public String editPatient(Patient patient) throws SQLException {
        if(val.biggerOrEqualToNumber(patient.getFirstName(), 1)) {
            return "Error: First name has to be at least 2 characters";
        } else if(val.biggerOrEqualToNumber(patient.getLastName(), 1)) {
            return "Error: Last name has to be at least 2 characters";
        } else if(val.betweenInt(patient.getHeight(), 1, 300)) {
            return "Error: Not a valid height";
        } else if(val.betweenInt(patient.getWeight(), 1, 300)){
            return "Error: Not a valid weight";
        } else if(val.betweenInt(patient.getCpr(), 999, 10000)) {
            return "Error: CPR number not valid";
        } else {
            db.editPatient(patient);
            return "Success";
        }
    }

    public String deletePatient(int id) throws SQLException {
        db.deletePatient(id);
        return "Success";

    }

    public List<Patient> fetchAll() throws SQLException {
        ResultSet rs = db.allPatients();
        List<Patient> patientList = new ArrayList<>();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setFirstName(rs.getString("firstname"));
            patient.setLastName(rs.getString("lastname"));
            patientList.add(patient);
        }
        return patientList;
    }

    public List<Patient> searchPatientList(String searchword) throws SQLException {
        ResultSet rs = db.searchPatientList(searchword);
        List<Patient> patientList = new ArrayList<>();
        while (rs.next()) {
            Patient patient = new Patient();
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
            patient.setCity(rs.getString("city"));
            patient.setZip(rs.getInt("zip"));
            patient.setAddress(rs.getString("address"));
            patient.setPhone(rs.getInt("phone"));
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
            note.setPatientId(rs.getInt("fk_patient"));
            note.setId(rs.getInt("id"));
            note.setNote(rs.getString("note"));
            note.setTimestamp(rs.getString("date"));
            noteList.add(note);
        }
        return noteList;
    }

    public String createNote(Patient patient, int id) throws SQLException {
        if(val.betweenString(patient.getNote(), 3, 360)) {
            return "Error: Note has to be between 3 and 360 characters.";
        } else {
            db.createNote(patient, id);
            return "Success";
        }
    }

    public String deleteNote(int id) throws SQLException {
        db.deleteNote(id);
        return "Success";
    }


}
