package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.User;
import dk.patientsystemet.demo.Repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public boolean verifyUser(User user) throws SQLException {
        ResultSet rs = loginRepository.userLogin(user);
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setRoleName(rs.getString("role.name"));
            return true;
        } else {
            return false;
        }
    }

}
