package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.Patient;
import dk.patientsystemet.demo.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

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
}
