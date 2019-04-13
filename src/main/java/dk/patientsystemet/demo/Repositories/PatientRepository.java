package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Patient;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PatientRepository {

    private Connection dbConnect;
    private PreparedStatement preparedStatement;

    public PatientRepository() {
        try {
            this.dbConnect = DriverManager.getConnection(
                    "jdbc:mysql://den1.mysql3.gear.host/patientsystem",
                    "patientsystem",
                    "Ny19sR!!9TZ2");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
    Create patient in the database
     */

    public void createPatient(Patient patient) {
        String sql = "INSERT INTO patient (firstname, lastname, height, weight, birthdate, cpr, address, zip, city, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setInt(3, patient.getHeight());
            preparedStatement.setInt(4, patient.getWeight());
            preparedStatement.setString(5, patient.getBirthDate());
            preparedStatement.setString(6, patient.getCpr());
            preparedStatement.setString(7, patient.getAddress());
            preparedStatement.setInt(8, patient.getZip());
            preparedStatement.setString(9, patient.getCity());
            preparedStatement.setInt(10, patient.getPhone());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
    Search function in the "all patients" list
     */

    public ResultSet searchPatientList(String searchword) {
        String sql = "SELECT * FROM patient " +
                "WHERE firstname LIKE ? " +
                "OR lastname LIKE ? " +
                "OR cpr LIKE ? ";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, "%"+searchword+"%");
            preparedStatement.setString(2, "%"+searchword+"%");
            preparedStatement.setString(3, "%"+searchword+"%");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    Edit patient by ID
     */

    public void editPatient(Patient patient) {
        String sql = "UPDATE patient SET firstname = ?, lastname = ?, height = ?, weight = ?, cpr = ?, address = ?, zip = ?, city = ?, phone = ? WHERE id = " + patient.getId();
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setInt(3, patient.getHeight());
            preparedStatement.setInt(4, patient.getWeight());
            preparedStatement.setString(5, patient.getCpr());
            preparedStatement.setString(6, patient.getAddress());
            preparedStatement.setInt(7, patient.getZip());
            preparedStatement.setString(8, patient.getCity());
            preparedStatement.setInt(9, patient.getPhone());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
    Deletes patient from database
     */

    public void deletePatient(int id) {
        String sql = "DELETE FROM patient WHERE id = ?";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
    Searches for patient by CPR number and returns ID
     */

    public ResultSet searchPatient(Patient patient) {
        String sql = "SELECT id FROM patient WHERE cpr=?";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, patient.getCpr());
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    Grabs the ID returned from the function above and returns patient information
     */

    public ResultSet findPatient(int id) {
        String sql = "SELECT * FROM patient LEFT JOIN patient_note ON patient.id = patient_note.fk_patient WHERE patient.id=?";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    find note by patient ID
     */

    public ResultSet findPatientNote(int id) {
        String sql = "SELECT * FROM patient_note WHERE patient_note.fk_patient=?";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    Returns a ResultSet of all patients
     */

    public ResultSet allPatients() {
        String sql = "SELECT * FROM patient";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    Assigns note to patient
     */

    public void createNote(Patient patient, int id) {
        String sql = "INSERT INTO patient_note (note, fk_patient) VALUES (?,?)";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, patient.getNote());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
    Delete note from patient
     */

    public void deleteNote(int id) {
        String sql = "DELETE FROM patient_note where id=?";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
