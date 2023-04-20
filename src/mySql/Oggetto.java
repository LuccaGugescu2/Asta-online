package mySql;

public class Oggetto {
    private int id;
    private String nome;
    private int idCategoria;
    private int quntità;
    private float base_asta;
    private String ipMultiCast;

    public Oggetto(int id, String nome, int idCategoria, int quntità, float base_asta, String ipMultiCast) {
        this.id = id;
        this.nome = nome;
        this.idCategoria = idCategoria;
        this.quntità = quntità;
        this.base_asta = base_asta;
        this.ipMultiCast = ipMultiCast;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getIpMultiCast() {
        return ipMultiCast;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCategoria() {
        return idCategoria;
    }

    public int getQuntità() {
        return quntità;
    }

    public float getBase_asta() {
        return base_asta;
    }

    @Override
    public String toString() {
        return id+") "+nome;
    }

    public String stampaInformazioni(){
        return "nome: "+nome+"\nbaseAsta: "+base_asta+"\nquantita: "+quntità;
    }
}
