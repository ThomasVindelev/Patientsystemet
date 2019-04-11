package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Diagnosis;
import dk.patientsystemet.demo.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.*;

@Repository
public class PatientRepository {

    private Connection dbConnect;
    private PreparedStatement preparedStatement;

    public PatientRepository() throws SQLException {
        this.dbConnect = DriverManager.getConnection(
                "jdbc:mysql://den1.mysql3.gear.host/patientsystem",
                "patientsystem",
                "Ny19sR!!9TZ2");
    }

    public void createPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient (firstname, lastname, height, weight, birthdate, cpr) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setString(1, patient.getFirstName());
        preparedStatement.setString(2, patient.getLastName());
        preparedStatement.setInt(3, patient.getHeight());
        preparedStatement.setInt(4, patient.getWeight());
        preparedStatement.setString(5, patient.getBirthDate());
        preparedStatement.setInt(6, patient.getCpr());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public ResultSet searchPatientList(String searchword) throws SQLException {
        String sql = "SELECT * FROM patient " +
                "WHERE firstname LIKE ? " +
                "OR lastname LIKE ? " +
                "OR cpr LIKE ? ";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setString(1, "%"+searchword+"%");
        preparedStatement.setString(2, "%"+searchword+"%");
        preparedStatement.setString(3, "%"+searchword+"%");
        return preparedStatement.executeQuery();
    }

    public void editPatient(Patient patient) throws SQLException {
        String sql = "UPDATE patient SET firstname = ?, lastname = ?, height = ?, weight = ?, cpr = ? WHERE id = " + patient.getId();
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setString(1, patient.getFirstName());
        preparedStatement.setString(2, patient.getLastName());
        preparedStatement.setInt(3, patient.getHeight());
        preparedStatement.setInt(4, patient.getWeight());
        preparedStatement.setInt(5, patient.getCpr());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void deletePatient(int id) throws SQLException {
        String sql = "DELETE FROM patient WHERE id = ?";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public ResultSet searchPatient(Patient patient) throws SQLException {
        String sql = "SELECT id FROM patient WHERE cpr=?";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setInt(1, patient.getCpr());
        return preparedStatement.executeQuery();
    }

    public ResultSet findPatient(int id) throws SQLException {
        String sql = "SELECT * FROM patient LEFT JOIN patient_note ON patient.id = patient_note.fk_patient WHERE patient.id=?";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public ResultSet findPatientNote(int id) throws SQLException {
        String sql = "SELECT * FROM patient_note WHERE patient_note.fk_patient=?";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public ResultSet allPatients() throws SQLException {
        String sql = "SELECT * FROM patient";
        preparedStatement = dbConnect.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void createNote(Patient patient, int id) throws SQLException {
        String sql = "INSERT INTO patient_note (note, fk_patient) VALUES (?,?)";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setString(1, patient.getNote());
        preparedStatement.setInt(2, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void deleteNote(int id) throws SQLException {
        String sql = "DELETE FROM patient_note where id=?";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }



}
