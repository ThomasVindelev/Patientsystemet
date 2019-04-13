package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LoginRepository {

    private Connection con;
    private PreparedStatement preparedStatement;

    public LoginRepository() {
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://den1.mysql3.gear.host/patientsystem",
                    "patientsystem",
                    "Ny19sR!!9TZ2");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     verifies user
     */

    public ResultSet userLogin(User user) {

        String query = "SELECT * from users " +
                "INNER JOIN role ON users.fk_role = role.id WHERE email=? AND password=md5(?)";

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
