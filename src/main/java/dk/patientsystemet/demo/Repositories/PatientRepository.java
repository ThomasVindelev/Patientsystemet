package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Diagnosis;
import dk.patientsystemet.demo.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.*;

@Repository
public class PatientRepository {

    @Autowired
    DBConnect dbConnect;
    
    private PreparedStatement preparedStatement;

    public void createPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient (firstname, lastname, height, weight, birthdate, cpr) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, patient.getFirstName());
        preparedStatement.setString(2, patient.getLastName());
        preparedStatement.setInt(3, patient.getHeight());
        preparedStatement.setInt(4, patient.getWeight());
        preparedStatement.setString(5, patient.getBirthDate());
        preparedStatement.setInt(6, patient.getCpr());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public ResultSet findPatient(Patient patient) throws SQLException {
        String sql = "SELECT * FROM patient LEFT JOIN patient_note ON patient.id = patient_note.fk_patient WHERE cpr=?";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, patient.getCpr());
        return preparedStatement.executeQuery();
    }

    public ResultSet findPatientNote(int id) throws SQLException {
        String sql = "SELECT * FROM patient_note WHERE patient_note.fk_patient=?";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public ResultSet allPatients() throws SQLException {
        String sql = "SELECT * FROM patient";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void createNote(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient_note (note, fk_patient) VALUES (?,?)";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, patient.getNote());
        preparedStatement.setInt(2, patient.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public ResultSet getDiagnosis(int id) throws SQLException {
        String sql = "SELECT * FROM diagnosis LEFT JOIN diagnosis_names ON diagnosis.fk_diagnosis_names = diagnosis_names.id " +
                "LEFT JOIN patient ON diagnosis.fk_patient = patient.id LEFT JOIN users ON diagnosis.fk_users = users.id WHERE fk_patient = " + id;
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void createDiagnosis(int id, Diagnosis diagnosis) {
        String sql = "INSERT INTO diagnosis (note, date, fk_diagnosis_names, fk_patient, fk_users)";
    }

}
