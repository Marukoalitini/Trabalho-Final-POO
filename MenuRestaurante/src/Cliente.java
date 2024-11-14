public class Cliente extends Pessoa {
    private Mesa mesa;

    public Cliente(String nome, Mesa mesa) {
        super(nome);
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
