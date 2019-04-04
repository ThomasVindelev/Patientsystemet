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
        String sql = "INSERT INTO patient (firstname, lastname, height, weight, birthdate, cpr, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, patient.getFirstName());
        preparedStatement.setString(2, patient.getLastName());
        preparedStatement.setInt(3, patient.getHeight());
        preparedStatement.setInt(4, patient.getWeight());
        preparedStatement.setString(5, patient.getBirthDate());
        preparedStatement.setInt(6, patient.getCpr());
        preparedStatement.setString(7, patient.getNote());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public ResultSet findPatient(Patient patient) throws SQLException {
        String sql = "SELECT * FROM patient WHERE cpr=?";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, patient.getCpr());
        return preparedStatement.executeQuery();
    }

    public ResultSet allPatients() throws SQLException {
        String sql = "SELECT * FROM patient";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void editNote(Patient patient) throws SQLException {
        String sql = "UPDATE patient SET note = ? WHERE cpr = ?";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, patient.getNote());
        preparedStatement.setInt(2, patient.getCpr());
        preparedStatement.execute();
        preparedStatement.close();
    }

}
