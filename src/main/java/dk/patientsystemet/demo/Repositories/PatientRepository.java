package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        String sql = "SELECT * FROM patient INNER JOIN patient_note ON patient.id = patient_note.fk_patient WHERE cpr=?";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, patient.getCpr());
        return preparedStatement.executeQuery();
    }

    public ResultSet allPatients() throws SQLException {
        String sql = "SELECT * FROM patient INNER JOIN patient_note ON patient.id = patient_note.fk_patient";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void createNote(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient_note (note, fk_patients) VALUES (?,?)";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, patient.getNote());
        preparedStatement.setInt(2, patient.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

}
