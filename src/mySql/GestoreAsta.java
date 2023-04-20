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
            connection = DriverManager.getConnection(dbURL, username, password);
            if (connection != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Oggetto getOggetto(int id) throws SQLException {
        Statement stat = connection.createStatement();
        ResultSet result = stat.executeQuery("" +
                "SELECT oggetti.id_oggetto, oggetti.nome,  FROM categoire, oggetti" +
                "WHERE categorie.id_categorie = oggetti.id_categorie" +
                "AND oggetti.id_oggetto = " + id
        );
        result.next();
        int id_oggetto = result.getInt("id_oggetto");
        String nome = result.getString("nome");
        return null;
    }

    public ArrayList<Categoria> getCategorie() throws SQLException {
        Statement stat = connection.createStatement();
        ResultSet result = stat.executeQuery("" +
                "SELECT CategoriaID, NomeCategoria FROM categorie"
        );
        ArrayList<Categoria> categorie = new ArrayList<>();
        int id_categoria;
        String nome_categoria;
        while (result.next()) {
            id_categoria = result.getInt("CategoriaID");
            nome_categoria = result.getString("NomeCategoria");
            System.out.println(id_categoria);
            System.out.println(nome_categoria);
            Categoria categoria = new Categoria(id_categoria, nome_categoria);
            categorie.add(categoria);
        }
        return categorie;
    }

    public ArrayList<Oggetto> getOggettiByIdCategoria(int idCategoria) throws SQLException {
        Statement stat = connection.createStatement();

        ResultSet result = stat.executeQuery("" +
                "SELECT * FROM oggetti WHERE CategoriaID = " + idCategoria
        );
        ArrayList<Oggetto> oggetti = new ArrayList<>();
        int idOggetto;
        String nomeOggetto;
        int quantita;
        float baseAsta;
        String ipMulticast;
        while (result.next()) {
            idOggetto = result.getInt("OggettoID");
            nomeOggetto = result.getString("NomeOggetto");
            quantita = result.getInt("Quantita");
            baseAsta = result.getFloat("BaseAsta");
            ipMulticast = result.getString("IpMulticast");
            oggetti.add(new Oggetto(idOggetto, nomeOggetto, idCategoria, quantita, baseAsta, ipMulticast));
        }
        return oggetti;
    }

    public ArrayList<String> getIpMultiCast() throws SQLException {
        Statement stat = connection.createStatement();

        ResultSet result = stat.executeQuery("" +
                "SELECT IpMulticast FROM oggetti"
        );
        ArrayList<String> listIp = new ArrayList<>();
        String ipMulticast;
        while (result.next()) {
            ipMulticast = result.getString("IpMulticast");
            listIp.add(ipMulticast);
        }
        return listIp;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
