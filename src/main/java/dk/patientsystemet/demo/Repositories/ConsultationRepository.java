package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Consultation;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ConsultationRepository {

    private Connection dbConnect;
    private PreparedStatement preparedStatement;

    public ConsultationRepository() throws SQLException {
        this.dbConnect = DriverManager.getConnection(
                "jdbc:mysql://den1.mysql3.gear.host/patientsystem",
                "patientsystem",
                "Ny19sR!!9TZ2");
    }

    /**
     Create consultation with consultation object
     */

    public void createConsultation(Consultation consultation) {
        String sql = "INSERT INTO consultation (description, date, time, fk_patient, fk_users) VALUES (?, ?, ?, ?, ?)";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, consultation.getDescription());
            preparedStatement.setString(2, consultation.getDate());
            preparedStatement.setString(3, consultation.getTime());
            preparedStatement.setInt(4, consultation.getPatientId());
            preparedStatement.setInt(5, consultation.getUserId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     Find consultations by patient id
     */

    public ResultSet findConsultations(int id) {
        String sql = "SELECT * FROM consultation LEFT JOIN patient ON consultation.fk_patient = patient.id " +
                "LEFT JOIN users ON consultation.fk_users = users.id WHERE consultation.fk_patient=? ORDER BY consultation.date ASC";
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
     Find specific consultation by id
     */

    public ResultSet findConsultationById(int id) {
        String sql = "SELECT * FROM consultation LEFT JOIN patient ON consultation.fk_patient = patient.id WHERE consultation.id = ?";
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
     Find consultations for specific user (Doctor) by session ID
     */

    public ResultSet upcomingConsultations(int userId) {
        String sql = "SELECT * FROM consultation INNER JOIN patient ON consultation.fk_patient = patient.id WHERE fk_users=? ORDER BY consultation.date ASC LIMIT 5";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Fetch all consultations
     */

    public ResultSet fetchAll() {
        String sql = "SELECT * FROM consultation INNER JOIN patient ON consultation.fk_patient = patient.id ORDER BY consultation.date ASC LIMIT 5";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Edit consultation with Consultation object
     */

    public void editConsultation(Consultation consultation) {
        String sql = "UPDATE consultation SET description=?,conclusion=?, date=?, time=? WHERE id=?";
        try {
            preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, consultation.getDescription());
            preparedStatement.setString(2, consultation.getConclusion());
            preparedStatement.setString(3, consultation.getDate());
            preparedStatement.setString(4, consultation.getTime());
            preparedStatement.setInt(5, consultation.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     Delete consultation by consultation ID
     */

    public void deleteConsultation(int id) {
        String sql = "DELETE FROM consultation WHERE id=?";
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
