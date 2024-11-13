import java.util.ArrayList;

public class Pedido {
    private int id;
    private ArrayList<ItemMenu> itens;

    public Pedido(int id) {
        this.id = id;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemMenu item) {
        itens.add(item);
        System.out.println("Item "+ item + "adicionado.");
    }

    public double calcularValorTotal() {
        double total = 0;
        for (ItemMenu item : itens) {
            total += item.getPreco();
        }
        return total;
    }
    public ArrayList<ItemMenu> getItens(){
        return itens;
    }
    public void descreverPedido() {
        System.out.println("Pedido ID: " + id);
        for (ItemMenu item : itens) {
            item.descrever();
        }
        System.out.println("Valor Total: R$ " + calcularValorTotal());
    }
}
