package mySql;

public class Oggetto {
    private int id;
    private String nome;
    private Categoria categoria;
    private int quntità;
    private float base_asta;

    public Oggetto(int id, String nome, Categoria categoria, int quntità, float base_asta) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.quntità = quntità;
        this.base_asta = base_asta;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getQuntità() {
        return quntità;
    }

    public float getBase_asta() {
        return base_asta;
    }
}
