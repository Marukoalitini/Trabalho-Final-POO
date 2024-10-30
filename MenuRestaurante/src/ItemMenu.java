import java.util.ArrayList;
import java.util.List;

//  Classe abstrata que representa um item do menu
abstract class ItemMenu {
    private String nome;
    private double preco;
    private List<String> ingredientes = new ArrayList<>();

    public ItemMenu(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void adicionarIngrediente(String ingrediente) {
        ingredientes.add(ingrediente);
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    //  Método abstrato a ser sobrescrito pelas subclasses
    public abstract void descrever();
}
