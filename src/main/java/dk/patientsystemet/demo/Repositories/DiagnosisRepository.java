package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Diagnosis;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DiagnosisRepository {

    Connection dbConnect;
    PreparedStatement preparedStatement;

    public DiagnosisRepository() throws SQLException {
        this.dbConnect = DriverManager.getConnection(
                "jdbc:mysql://den1.mysql3.gear.host/patientsystem",
                "patientsystem",
                "Ny19sR!!9TZ2");
    }

    public ResultSet getDiagnosisById(int id, int choice) throws SQLException {
        String sql;
        if (choice == 1) {
            sql = "SELECT * FROM diagnosis " +
                    "LEFT JOIN diagnosis_names ON diagnosis.fk_diagnosis_names = diagnosis_names.id " +
                    "LEFT JOIN patient ON diagnosis.fk_patient = patient.id " +
                    "LEFT JOIN users ON diagnosis.fk_users = users.id " +
                    "LEFT JOIN junction_diagnosis_and_medicine ON diagnosis_names.id = junction_diagnosis_and_medicine.fk_diagnosis " +
                    "LEFT JOIN medicine ON junction_diagnosis_and_medicine.fk_medicine = medicine.id WHERE fk_patient = " + id;
        } else {
            sql = "SELECT * FROM diagnosis " +
                    "LEFT JOIN diagnosis_names ON diagnosis.fk_diagnosis_names = diagnosis_names.id " +
                    "LEFT JOIN patient ON diagnosis.fk_patient = patient.id " +
                    "LEFT JOIN users ON diagnosis.fk_users = users.id " +
                    "LEFT JOIN junction_diagnosis_and_medicine ON diagnosis_names.id = junction_diagnosis_and_medicine.fk_diagnosis " +
                    "LEFT JOIN medicine ON junction_diagnosis_and_medicine.fk_medicine = medicine.id WHERE diagnosis.id = " + id;
        }
        preparedStatement = dbConnect.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet getDiagnosis() throws SQLException {
        String sql = "SELECT * FROM diagnosis_names";
        preparedStatement = dbConnect.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet getNewDiagnosis() throws SQLException {
        String sql = "SELECT * FROM diagnosis " +
                "LEFT JOIN diagnosis_names ON diagnosis.fk_diagnosis_names = diagnosis_names.id " +
                "LEFT JOIN patient ON diagnosis.fk_patient = patient.id " +
                "LEFT JOIN users ON diagnosis.fk_users = users.id ORDER BY diagnosis.id DESC LIMIT 5";
        preparedStatement = dbConnect.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void createDiagnosis(Diagnosis diagnosis, int patientID, int userID) throws SQLException {
        String sql = "INSERT INTO diagnosis (note, fk_patient, fk_users, fk_diagnosis_names) VALUES (?, ?, ?, ?)";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setString(1, diagnosis.getNote());
        preparedStatement.setInt(2, patientID);
        preparedStatement.setInt(3, userID);
        preparedStatement.setInt(4, diagnosis.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void createUnknownDiagnosis(String diagnosisName) throws SQLException {
        String sql = "INSERT INTO diagnosis_names (name) VALUE (?)";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setString(1, diagnosisName);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void deleteDiagnosis(int id) throws SQLException {
        String sql = "DELETE FROM diagnosis WHERE id = ?";
        preparedStatement = dbConnect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

}
