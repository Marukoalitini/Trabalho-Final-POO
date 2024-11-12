public class Mesa {
    private final int numero;
    private final int capacidade;
    private boolean ocupada;
    private Pedido pedido;
    private Garcom garcomResponsavel;

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

    public void reservar(Garcom garcom) {
        ocupada = true;
        this.garcomResponsavel = garcom;
    }

    public void liberar() {
        ocupada = false;
        pedido = null; // Limpa o pedido ao liberar a mesa
        garcomResponsavel = null;
    }
    public Garcom getGarcomResponsavel() {
        return garcomResponsavel;
    }

    public void fazerPedido(int id) throws ExcecaoPedidoExiste, ExcecaoMesaNaoOcupada {
        if (!ocupada) {
            throw new ExcecaoMesaNaoOcupada("Mesa não está ocupada.");
        }
        if (pedido != null) {
            throw new ExcecaoPedidoExiste("Pedido já existe para esta mesa.");
        }
        this.pedido = new Pedido(id);
    }

    public void pedirItem(ItemMenu item) {
        if (ocupada && pedido != null) {
            pedido.adicionarItem(item);
        } else {
            System.out.println("Mesa não está ocupada ou não há pedido.");
        }
    }

    public void descreverMesa() {
        String status = ocupada ? "Ocupada" : "Disponível";
        System.out.println("Mesa " + numero + " - Capacidade: " + capacidade + " - Status: " + status);
        if (pedido != null) {
            pedido.descreverPedido();
        }
    }


    public double getValorTotal() {
        return pedido.calcularValorTotal();
    }
}