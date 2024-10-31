public class Mesa {
    private final int numero;
    private final int capacidade;
    private boolean ocupada;
    private Pedido pedido;

    public Mesa(int numero, int capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.ocupada = false;
        this.pedido = null;
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void reservar() {
        ocupada = true;
    }

    public void liberar() {
        ocupada = false;
        pedido = null; // Limpa o pedido ao liberar a mesa
    }

    public void fazerPedido(Pedido novoPedido) {
        if (pedido == null) {
            this.pedido = novoPedido;
        } else {
            System.out.println("Pedido já existe.");
        }
    }

    public void pedirItem(ItemMenu item) {
        if (ocupada && pedido != null) {
            pedido.adicionarItem(item);
        } else {
            System.out.println("Mesa não está ocupada ou não há pedido.");
        }
    }

    public void descreverMesa() {
        System.out.println("Mesa: " + numero + " - Capacidade: " + capacidade + " - " + (ocupada ? "Ocupada" : "Disponível"));
        if (pedido != null) {
            System.out.println("Pedido atual:");
            pedido.descreverPedido(); // Chama o método para descrever o pedido
        }
    }
    public void getValorTotal(){
        pedido.calcularValorTotal();
    }
}
