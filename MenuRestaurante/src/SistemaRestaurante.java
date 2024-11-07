import java.util.ArrayList;
import java.util.List;
import java.io.*;
// Classe principal para testar o sistema
public class SistemaRestaurante {
    public static void main(String[] args) {
        List<ItemMenu> menu = new ArrayList<>();

        // Adicionando itens ao menu
        Prato Niguiri = new Prato("Niguiri de Salmão", 25.50, TipoPrato.Principal);
        Niguiri.adicionarIngrediente("Arroz");
        Niguiri.adicionarIngrediente("Salmão");
        Niguiri.adicionarIngrediente("Alga");
        menu.add(Niguiri);

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
        for (ItemMenu item : menu) {
            item.descrever();
        }
        Mesa mesa = new Mesa(1, 4);
        Cliente cliente = new Cliente("João", mesa, 0, 0);
        try {
            mesa.fazerPedido(1);
        } catch (ExcecaoPedidoExiste e) {
            e.printStackTrace();

        }
        try {
            mesa.fazerPedido(2);
        } catch (ExcecaoPedidoExiste e) {
            e.printStackTrace();
        }
        Prato sushi = new Prato("Sushi de Salmão", 25.50, "Principal");
        sushi.adicionarIngrediente("Arroz");
        sushi.adicionarIngrediente("Salmão");
        sushi.adicionarIngrediente("Alga");


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sushi.ser"))) {
            oos.writeObject(sushi);
        } catch (IOException e) {
            System.err.println("Erro ao serializar o prato: " + e.getMessage());
        }
    }
}
