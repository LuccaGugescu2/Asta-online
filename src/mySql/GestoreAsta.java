package mySql;

import java.sql.*;
import java.util.ArrayList;

public class GestoreAsta {
    private String dbURL = "jdbc:mysql://localhost:3306/asta";
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
    public ArrayList<Categoria> getCategorie() throws SQLException {
        Statement stat = connection.createStatement();
        ResultSet result = stat.executeQuery("" +
                "SELECT NomeCategoria, CategoriaID FROM CATEGORIE"
        );
        ArrayList<Categoria> categorie = new ArrayList<>();
        int id_categoria;
        String nome_categoria;
        while(result.next()) {
            id_categoria = result.getInt("CategoriaID");
            nome_categoria = result.getString("NomeCategoria");
            Categoria categoria = new Categoria(id_categoria, nome_categoria);
            categorie.add(categoria);
        }
        return categorie;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
