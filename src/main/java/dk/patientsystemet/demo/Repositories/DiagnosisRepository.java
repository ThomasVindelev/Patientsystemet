package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Diagnosis;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DiagnosisRepository {

    Connection dbConnect;
    PreparedStatement preparedStatement;

    public DiagnosisRepository() {
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
     Gets specific diagnosis depending on choice
     */

    public ResultSet getDiagnosisById(int id, int choice) {
        String sql;
        if (choice == 1) {
            sql = "SELECT * FROM diagnosis " +
                    "LEFT JOIN diagnosis_names ON diagnosis.fk_diagnosis_names = diagnosis_names.id " +
                    "LEFT JOIN patient ON diagnosis.fk_patient = patient.id " +
                    "LEFT JOIN users ON diagnosis.fk_users = users.id " +
                    "WHERE fk_patient = " + id;
        } else {
            sql = "SELECT * FROM diagnosis " +
                    "LEFT JOIN diagnosis_names ON diagnosis.fk_diagnosis_names = diagnosis_names.id " +
                    "LEFT JOIN patient ON diagnosis.fk_patient = patient.id " +
                    "LEFT JOIN users ON diagnosis.fk_users = users.id " +
                    "WHERE diagnosis.id = " + id;
        }
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Gets medicine assigned to a diagnosis
     */

    public ResultSet getMedicineByDiagnosis(int id) {
        String sql = "SELECT * FROM junction_diagnosis_and_medicine " +
                "LEFT JOIN diagnosis_names ON junction_diagnosis_and_medicine.fk_diagnosis = diagnosis_names.id " +
                "LEFT JOIN medicine ON junction_diagnosis_and_medicine.fk_medicine = medicine.id WHERE junction_diagnosis_and_medicine.fk_diagnosis = " + id;
        try {
           preparedStatement = dbConnect.prepareStatement(sql);
           return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     Gets all diagnoses
     */

    public ResultSet getDiagnosis() {
        String sql = "SELECT * FROM diagnosis_names";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Gets the five most recently assigned diagnoses
     */

    public ResultSet getNewDiagnosis() {
        String sql = "SELECT * FROM diagnosis " +
                "LEFT JOIN diagnosis_names ON diagnosis.fk_diagnosis_names = diagnosis_names.id " +
                "LEFT JOIN patient ON diagnosis.fk_patient = patient.id " +
                "LEFT JOIN users ON diagnosis.fk_users = users.id ORDER BY diagnosis.id DESC LIMIT 5";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Assigns diagnosis to patient
     */

    public void createDiagnosis(Diagnosis diagnosis, int patientID, int userID) {
        String sql = "INSERT INTO diagnosis (note, fk_patient, fk_users, fk_diagnosis_names) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, diagnosis.getNote());
            preparedStatement.setInt(2, patientID);
            preparedStatement.setInt(3, userID);
            preparedStatement.setInt(4, diagnosis.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     Creates a new diagnosis not previously in the system
     */

    public void createUnknownDiagnosis(String diagnosisName) {
        String sql = "INSERT INTO diagnosis_names (name) VALUE (?)";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, diagnosisName);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     Deletes a diagnosis assigned to a patient
     */

    public void deleteDiagnosis(int id) {
        String sql = "DELETE FROM diagnosis WHERE id = ?";
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
