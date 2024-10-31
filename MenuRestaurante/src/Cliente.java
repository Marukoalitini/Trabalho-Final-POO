public class Cliente extends Pessoa{
    public double conta;
    public double gorjeta;


    public Cliente(String nome, Mesa mesa, double conta, double gorjeta) {
        super(nome, mesa);
        this.conta = conta;
        this.gorjeta = gorjeta;
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
}