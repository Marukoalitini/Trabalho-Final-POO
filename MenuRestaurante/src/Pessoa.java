public class Pessoa {
    private Mesa mesa;
    private String nome;


    public Pessoa (String nome, Mesa mesa) {
        this.nome = nome;
        this.mesa = mesa;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


    public Mesa getMesa() {
        return mesa;
    }
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}