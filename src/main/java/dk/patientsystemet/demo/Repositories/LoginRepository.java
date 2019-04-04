package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LoginRepository {

    @Autowired
    DBConnect dbConnect;

    private PreparedStatement preparedStatement;

    public ResultSet userLogin(User user) throws SQLException {
        Connection connection = dbConnect.getConnection();

        String query = "SELECT * from users " +
                "INNER JOIN role ON users.fk_role = role.id WHERE email=? AND password=?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        return preparedStatement.executeQuery();
    }
}
