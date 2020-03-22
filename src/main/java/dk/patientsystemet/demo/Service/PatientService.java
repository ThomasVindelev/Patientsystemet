package dk.patientsystemet.demo.Service;

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

    /**
    Checks input from patient creation page
     */

    public String createPatient(Patient patient) {
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
        } else if(val.betweenString(patient.getCity(), 1, 500)) {
            return "Error: Fill out City";
        } else if(val.zipNumber(patient.getZip())) {
            return "Error: Zip has to be 4 numbers";
        } else if(val.betweenString(patient.getAddress(), 1, 500)) {
            return "Error: Fill out Address";
        } else if(val.phoneNumber(patient.getPhone())) {
            return "Error: Phone Number has to be 8 numbers";
        } else {
            db.createPatient(patient);
            return "Success";
        }
    }

    /**
    Checks input from patient edit page
     */

    public String editPatient(Patient patient) {
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
        } else if(val.betweenString(patient.getCity(), 1, 500)) {
            return "Error: Fill out City";
        } else if(val.zipNumber(patient.getZip())) {
            return "Error: Zip has to be 4 numbers";
        } else if(val.betweenString(patient.getAddress(), 1, 500)) {
            return "Error: Fill out Address";
        } else if(val.phoneNumber(patient.getPhone())) {
            return "Error: Phone Number has to be 8 numbers";
        } else {
            db.editPatient(patient);
            return "Success";
        }
    }

    /**
    Delete patient
     */

    public String deletePatient(int id) {
        db.deletePatient(id);
        return "Success";

    }

    /**
    Returns a list of all patients
     */

    public List<Patient> fetchAll() {
        ResultSet rs = db.allPatients();
        List<Patient> patientList = new ArrayList<>();
        try {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setFirstName(rs.getString("firstname"));
                patient.setLastName(rs.getString("lastname"));
                patientList.add(patient);
            }
            return patientList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    Return patients that matches the search word in a list
     */

    public List<Patient> searchPatientList(String searchword) {
        ResultSet rs = db.searchPatientList(searchword);
        List<Patient> patientList = new ArrayList<>();
        try {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setFirstName(rs.getString("firstname"));
                patient.setLastName(rs.getString("lastname"));
                patientList.add(patient);
            }
            return patientList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    Returns a patient id as int
     */

    public int searchPatient(Patient patient) {
        ResultSet rs = db.searchPatient(patient);
        try {
            if (rs.next()) {
                return rs.getInt("patient.id");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
    Returns a Patient object to the patient page
     */

    public Patient findPatient(int id) {
        ResultSet rs = db.findPatient(id);
        try {
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient.id"));
                patient.setFirstName(rs.getString("firstname"));
                patient.setLastName(rs.getString("lastname"));
                patient.setBirthDate(rs.getString("birthdate"));
                patient.setHeight(rs.getInt("height"));
                patient.setWeight(rs.getInt("weight"));
                patient.setCpr(rs.getString("cpr"));
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return null;
    }

    /**
    Returns a list of notes by patient id
     */

    public List<Note> findPatientNote(int id) {
        ResultSet rs = db.findPatientNote(id);
        List<Note> noteList = new ArrayList<>();
        try {
            while (rs.next()) {
                Note note = new Note();
                note.setPatientId(rs.getInt("fk_patient"));
                note.setId(rs.getInt("id"));
                note.setNote(rs.getString("note"));
                note.setTimestamp(rs.getString("date"));
                noteList.add(note);
            }
            return noteList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    Checks for input in note creation fields
     */

    public String createNote(Patient patient, int id) {
        if(val.betweenString(patient.getNote(), 3, 360)) {
            return "Error: Note has to be between 3 and 360 characters.";
        } else {
            db.createNote(patient, id);
            return "Success";
        }
    }

    /**
    Delete note
     */

    public String deleteNote(int id) {
        db.deleteNote(id);
        return "Success";
    }

}
