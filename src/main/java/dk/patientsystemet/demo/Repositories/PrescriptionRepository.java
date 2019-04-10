package dk.patientsystemet.demo.Repositories;

import com.mysql.cj.protocol.Resultset;
import dk.patientsystemet.demo.Model.Medicine;
import dk.patientsystemet.demo.Model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import dk.patientsystemet.demo.Model.Prescription;
import java.sql.*;

@Repository
public class PrescriptionRepository {

    private Connection dbConnect;
    private PreparedStatement preparedStatement;

    public PrescriptionRepository() throws SQLException {
        this.dbConnect = DriverManager.getConnection(
                "jdbc:mysql://den1.mysql3.gear.host/patientsystem",
                "patientsystem",
                "Ny19sR!!9TZ2");
    }

    public ResultSet findPrescriptionByPatient(int id) {
        try {
            String sql = "SELECT * FROM prescription " +
                    "INNER JOIN patient ON prescription.fk_patient = patient.id " +
                    "INNER JOIN users ON prescription.fk_users = users.id " +
                    "WHERE prescription.fk_patient = ?";

            /*String sql =    "SELECT * FROM junction_prescription_and_medicine " +
                            "INNER JOIN prescription ON junction_prescription_and_medicine.fk_prescription = prescription.id " +
                            "INNER JOIN medicine ON junction_prescription_and_medicine.fk_medicine = medicine.id " +
                            "INNER JOIN patient ON prescription.fk_patient = patient.id " +
                            "INNER JOIN users ON prescription.fk_users = users.id " +
                            "WHERE prescription.fk_patient = ?";*/
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public ResultSet recentPrescriptions(int id) {
        try {
            String sql = "SELECT * FROM prescription " +
                    "INNER JOIN patient ON prescription.fk_patient = patient.id " +
                    "INNER JOIN users ON prescription.fk_users = users.id " +
                    "WHERE prescription.fk_users = ? " +
                    "ORDER BY prescription.date DESC " +
                    "LIMIT 5 ";
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public ResultSet findPrescriptionById(int id) {
        try {
            String sql = "SELECT * FROM prescription " +
                    "INNER JOIN patient ON prescription.fk_patient = patient.id " +
                    "INNER JOIN users ON prescription.fk_users = users.id " +
                    "WHERE prescription.id = ?";
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public ResultSet findMedicineByPrescription(int id) {
        try {
            String sql = "SELECT * FROM junction_prescription_and_medicine " +
                    "INNER JOIN medicine ON junction_prescription_and_medicine.fk_medicine = medicine.id " +
                    "WHERE fk_prescription = ?";
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void createPrescription(Prescription prescription) {
        try {
            String sql = "INSERT INTO prescription (description, fk_patient, fk_users) VALUES (?, ?, ?)";
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, prescription.getDescription());
            preparedStatement.setInt(2, prescription.getPatientId());
            preparedStatement.setInt(3, prescription.getDoctorId());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void editPrescription() {

    }
    public void deletePrescription(int preInt) {
        try {
            deleteMedicineOne(preInt);
            String sql = "DELETE FROM prescription WHERE id=?";
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, preInt);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteMedicineOne(int preId) {
        try {
            String sql = "DELETE FROM junction_prescription_and_medicine WHERE fk_prescription=?";
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, preId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet getAllMedicine() {
        try {
            String sql = "SELECT * FROM medicine";
            preparedStatement = dbConnect.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    public void createMedicine(int medId, int preInt) {
        try {
            String sql = "INSERT INTO patientsystem.junction_prescription_and_medicine (fk_medicine, fk_prescription) VALUES (?, ?)";
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, medId);
            preparedStatement.setInt(2, preInt);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteMedicine(int medId, int preInt) {
        try {
            String sql = "DELETE FROM patientsystem.junction_prescription_and_medicine WHERE fk_medicine=? AND fk_prescription=?";
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, medId);
            preparedStatement.setInt(2, preInt);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet findPrescriptionByLastUser(int id) {
        try {
            String sql = "SELECT id FROM prescription WHERE fk_users = ? ORDER BY date DESC LIMIT 1";
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
