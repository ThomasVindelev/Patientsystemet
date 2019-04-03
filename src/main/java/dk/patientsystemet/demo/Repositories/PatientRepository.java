package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
