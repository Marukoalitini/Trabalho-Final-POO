import java.util.*;

public class SistemaRestaurante {
    private static List<ItemMenu> menu = new ArrayList<>();
    private static List<Mesa> mesas = new ArrayList<>();
    private static List<Garcom> garcons = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        setupMenu();

        while (true) {
            System.out.println("\n--- Menu do Restaurante ---");
            System.out.println("1. Área de Cadastramento");
            System.out.println("2. Área de Exclusão");
            System.out.println("3. Cadastrar Cliente e Associar Mesa");
            System.out.println("4. Fazer Pedido");
            System.out.println("5. Exibir Status da Mesa");
            System.out.println("6. Encerrar Conta e Liberar Mesa");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = lerOpcao(scanner, 7);

            switch (opcao) {
                case 1 -> areaDeCadastramento(scanner);
                case 2 -> areaDeExclusao(scanner);
                case 3 -> cadastrarCliente(scanner);
                case 4 -> fazerPedido(scanner);
                case 5 -> exibirStatusMesa(scanner);
                case 6 -> encerrarContaLiberarMesa(scanner);
                case 7 -> {
                    System.out.println("Encerrando sistema.");
                    return;
                }
            }
        }
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.print("Nome do cliente: ");
        String nome = scanner.next();

        System.out.println("Escolha uma mesa para associar:");
        listarOpcoes(mesas);
        int mesaEscolha = lerOpcao(scanner, mesas.size());
        Mesa mesa = mesas.get(mesaEscolha - 1);

        System.out.println("Escolha um garçom:");
        listarOpcoes(garcons);
        int garcomEscolha = lerOpcao(scanner, garcons.size());
        Garcom garcom = garcons.get(garcomEscolha - 1);

        mesa.reservar(garcom);
        System.out.println("Cliente " + nome + " associado à mesa " + mesa.getNumero());
    }

    private static void fazerPedido(Scanner scanner) {
        System.out.println("Escolha a mesa para fazer o pedido:");
        listarOpcoes(mesas);
        System.out.println((mesas.size() + 1) + ". Voltar ao Menu Principal");

        int mesaEscolha = lerOpcao(scanner, mesas.size() + 1);
        if (mesaEscolha == mesas.size() + 1) return;

        Mesa mesa = mesas.get(mesaEscolha - 1);
        if (!mesa.isOcupada()) {
            System.out.println("Mesa está vazia. Associe um cliente antes de fazer um pedido.");
            return;
        }

        while (true) {
            System.out.println("\nEscolha o tipo de item para adicionar ao pedido:");
            System.out.println("1. Bebida\n2. Prato\n3. Voltar ao Menu Principal");
            int tipoItem = lerOpcao(scanner, 3);

            if (tipoItem == 3) return;

            List<ItemMenu> itensDisponiveis = tipoItem == 1 ? filtrarBebidas() : filtrarPratos();
            listarOpcoes(itensDisponiveis);
            System.out.println((itensDisponiveis.size() + 1) + ". Voltar ao Menu Principal");

            int itemEscolha = lerOpcao(scanner, itensDisponiveis.size() + 1);
            if (itemEscolha == itensDisponiveis.size() + 1) return;

            ItemMenu itemEscolhido = itensDisponiveis.get(itemEscolha - 1);
            mesa.pedirItem(itemEscolhido);
            System.out.println("Item " + itemEscolhido.getNome() + " adicionado ao pedido.");
        }
    }

    private static void exibirStatusMesa(Scanner scanner) {
        System.out.println("Escolha a mesa para ver o status:");
        listarOpcoes(mesas);
        System.out.println((mesas.size() + 1) + ". Voltar ao Menu Principal");

        int mesaEscolha = lerOpcao(scanner, mesas.size() + 1);
        if (mesaEscolha == mesas.size() + 1) return;

        Mesa mesa = mesas.get(mesaEscolha - 1);

        if (!mesa.isOcupada()) {
            System.out.println("Mesa está vazia.");
            return;
        }

        System.out.println("\n--- Status da Mesa " + mesa.getNumero() + " ---");
        System.out.println("Garçom Responsável: " + mesa.getGarcomResponsavel().getNome());

        if (mesa.getItensPedido().isEmpty()) {
            System.out.println("Nenhum item foi adicionado ao pedido.");
        } else {
            System.out.println("Itens do Pedido:");
            for (ItemMenu item : mesa.getItensPedido()) {
                System.out.println("- " + item.getNome() + " - R$ " + item.getPreco());
            }
        }
        System.out.println("Total acumulado: R$ " + mesa.getTotalConta());
    }

    private static void encerrarContaLiberarMesa(Scanner scanner) {
        System.out.println("Escolha a mesa para encerrar a conta:");
        listarOpcoes(mesas);
        System.out.println((mesas.size() + 1) + ". Voltar ao Menu Principal");

        int mesaEscolha = lerOpcao(scanner, mesas.size() + 1);
        if (mesaEscolha == mesas.size() + 1) return;

        Mesa mesa = mesas.get(mesaEscolha - 1);

        if (!mesa.isOcupada()) {
            System.out.println("Mesa já está vazia.");
            return;
        }

        System.out.println("\n--- Encerrando Conta da Mesa " + mesa.getNumero() + " ---");
        System.out.println("Total da Conta: R$ " + mesa.getTotalConta());
        mesa.liberar();
        System.out.println("Mesa " + mesa.getNumero() + " foi liberada.");
    }

    private static void setupMenu() {
        menu.add(new Prato("Niguiri de Salmão", 25.50, TipoPrato.Principal));
        menu.add(new Bebida("Chá Verde", 8.00, false));
        mesas.add(new Mesa(1, 4));
        garcons.add(new Garcom("Carlos", 1500, Turno.Noite));
    }

    // Métodos utilitários omitidos para evitar redundância
}
