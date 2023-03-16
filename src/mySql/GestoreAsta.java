package mySql;

import java.sql.*;

public class GestoreAsta {
    private String dbURL = "jdbc:mysql://localhost:3306/astaOnline";
    private String username = "root";
    private String password = "root";
    Connection connection;

    public GestoreAsta() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Oggetto getOggetto(int id) throws SQLException {

        Statement stat = connection.createStatement();
        ResultSet result = stat.executeQuery("" +
                "SELECT OGGETTI.id_oggetto, OGGETTI.nome,  FROM CATEGORIE, OGGETTI" +
                "WHERE CATEGORIE.id_categorie = OGGETTI.id_categorie"+
                "AND OGGETTI.id_oggetto = " + id
        );
        result.next();
        int id_oggetto = result.getInt("id_oggetto");
        String nome = result.getString("nome");
        return null;
    }
    public void close() throws SQLException {
        connection.close();
    }
}
