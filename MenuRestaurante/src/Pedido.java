import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private final int id;
    private final ArrayList<ItemMenu> itens;

    public Pedido(int id) {
        this.id = id;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemMenu item) {
        if (item != null) {
            itens.add(item);
            System.out.println("Item " + item.getNome() + " adicionado.");
        } else {
            System.out.println("Item inválido. Não é possível adicionar 'null' ao pedido.");
        }
    }

    public double calcularValorTotal() {
        double total = 0;
        for (ItemMenu item : itens) {
            total += item.getPreco();
        }
        return total;
    }

    public List<ItemMenu> getItens() {
        return Collections.unmodifiableList(itens); // Retorna uma lista imutável
    }

    public void descreverPedido() {
        System.out.println("Pedido ID: " + id);
        for (ItemMenu item : itens) {
            item.descrever();
        }
        System.out.println("Valor Total: R$ " + calcularValorTotal());
    }

    @Override
    public String toString() {
        return "Pedido ID: " + id + ", Itens: " + itens.size() + ", Valor Total: R$ " + calcularValorTotal();
    }
}
