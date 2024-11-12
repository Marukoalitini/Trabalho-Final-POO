public class Cliente extends Pessoa{
    private double conta;
    private double gorjeta;
    private Mesa mesa;

    public Cliente(String nome, Mesa mesa, double conta, double gorjeta) {
        super(nome);
        this.conta = conta;
        this.gorjeta = gorjeta;
        this.mesa = mesa;
    }


    public void pagarConta() { // Método para pagar conta
        conta = 0;
        gorjeta = 0;
    }


    public double getConta() {
        return conta;
    }
    public void setConta(double conta) {
        this.conta = conta;
    }

    public double getGorjeta() {
        return gorjeta;
    }
    public void setGorjeta(double gorjeta) {
        this.gorjeta = gorjeta;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}