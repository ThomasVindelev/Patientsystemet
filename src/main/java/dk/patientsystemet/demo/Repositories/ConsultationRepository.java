package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ConsultationRepository {

    @Autowired
    DBConnect dbConnect;

    private PreparedStatement preparedStatement;

    public ResultSet findConsultations(int id) throws SQLException {
        String sql = "SELECT * FROM consultation LEFT JOIN patient ON consultation.fk_patient = patient.id " +
                "LEFT JOIN users ON consultation.fk_users = users.id WHERE consultation.fk_patient=?";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public void deleteConsultation(int id) throws SQLException {
        String sql = "DELETE FROM consultation WHERE id=?";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }



}
