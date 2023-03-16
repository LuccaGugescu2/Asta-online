package mySql;

public class Cliente {
    private String nome;
    private int id_cliente;
    private String cognome;

    public Cliente(String nome, int id_cliente, String cognome) {
        this.nome = nome;
        this.id_cliente = id_cliente;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public String getCognome() {
        return cognome;
    }
}
