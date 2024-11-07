public class Cliente extends Pessoa{
    private double conta;
    private double gorjeta;
    private Pedido pedido = new Pedido();


    public Cliente(String nome, Mesa mesa, double conta, double gorjeta) {
        super(nome, mesa);
        this.conta = conta;
        this.gorjeta = gorjeta;
    }


    public void adicionarItemPedido(ItemMenu item) {
        pedido.adicionarItem(item);
        atualizarConta();
    }


    public void atualizarConta() {
        this.conta = pedido.calcularValorTotal();
    }


    public void incluirGorjeta (double valorGrojeta) {
        this.gorjeta = valorGrojeta;
    }


    public void pagarConta() { // Método para pagar conta
        conta = 0;
        gorjeta = 0;
        pedido = new Pedido(int id);
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