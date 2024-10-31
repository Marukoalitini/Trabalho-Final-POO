import java.util.ArrayList;
import java.util.List;

// Classe principal para testar o sistema
public class SistemaRestaurante {
    public static void main(String[] args) {
        List<ItemMenu> menu = new ArrayList<>();

        // Adicionando itens ao menu
        Prato sushi = new Prato("Sushi de Salmão", 25.50, "Principal");
        sushi.adicionarIngrediente("Arroz");
        sushi.adicionarIngrediente("Salmão");
        sushi.adicionarIngrediente("Alga");
        menu.add(sushi);

        Prato harumaki = new Prato("Harumaki", 12.00, "Entrada");
        harumaki.adicionarIngrediente("Massa de Harumaki");
        harumaki.adicionarIngrediente("Repolho");
        harumaki.adicionarIngrediente("Cenoura");
        menu.add(harumaki);

        Bebida chaVerde = new Bebida("Chá Verde", 8.00, false);
        chaVerde.adicionarIngrediente("Chá Verde");
        chaVerde.adicionarIngrediente("Água");
        menu.add(chaVerde);

        Bebida saque = new Bebida("Saquê", 18.00, true);
        saque.adicionarIngrediente("Saquê");
        menu.add(saque);

        // Exibindo descrição dos itens do menu (polimorfismo em ação)
        for (ItemMenu item : menu) {
            item.descrever();
        }

    }
}
