import java.util.ArrayList;
import java.util.List;

class Mesa {
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

    public boolean isOcupada() {
        return ocupada;
    }

    public void reservar(Garcom garcom) {
        ocupada = true;
        this.garcomResponsavel = garcom;
    }

    public void liberar() {
        ocupada = false;
        pedido = null;
        garcomResponsavel = null;
    }

    public Garcom getGarcomResponsavel() {
        return garcomResponsavel;
    }

    public void fazerPedido(int id) throws ExcecaoPedidoExiste {
        if (pedido != null) {
            throw new ExcecaoPedidoExiste("Pedido já existe para esta mesa.");
        }
        this.pedido = new Pedido(id);
    }

    public void pedirItem(ItemMenu item) throws ExcecaoMesaNaoOcupada {
        if (!ocupada) {
            throw new ExcecaoMesaNaoOcupada("Mesa não está ocupada. Não é possível fazer o pedido.");
        }
        if (pedido == null) {
            this.pedido = new Pedido(1); // Inicializa o pedido com um ID padrão se não existir
        }
        pedido.adicionarItem(item);
    }


    public double getTotalConta() {
        if (pedido == null) {
            return 0.0;
        }
        return pedido.calcularValorTotal();
    }

    public List<ItemMenu> getItensPedido() {
        if (pedido == null) {
            return new ArrayList<>();
        }
        return pedido.getItens();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void descreverMesa() {
        
    }
}
