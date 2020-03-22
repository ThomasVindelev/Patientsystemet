package dk.patientsystemet.demo.Repositories;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DBConnect {

    public Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://den1.mysql3.gear.host/patientsystem",
                "patientsystem",
                "Ny19sR!!9TZ2");
        return con;
    }

}

