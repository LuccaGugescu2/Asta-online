package mySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestoreAsta {
    private String dbURL = "jdbc:mysql://localhost:3306/astaOnline";
    private String username = "root";
    private String password = "root";
    Connection connection;
    public GestoreAsta() {
        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
