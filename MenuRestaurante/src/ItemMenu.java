import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serial;
import java.io.Serializable;

// Classe abstrata que representa um item do menu
abstract class ItemMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String nome;
    private final double preco;
    private final List<String> ingredientes = new ArrayList<>();

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

    public List<String> getIngredientes() {
        return Collections.unmodifiableList(ingredientes);
    }

    // Método abstrato a ser sobrescrito pelas subclasses
    public abstract void descrever();
}
