import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaRestaurante {
    private static List<ItemMenu> menu = new ArrayList<>();
    private static List<Mesa> mesas = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        setupMenu();

        while (running) {
            System.out.println("\n--- Menu do Restaurante ---");
            System.out.println("1. Cadastrar Cliente e Associar Mesa");
            System.out.println("2. Realizar Pedido para Mesa");
            System.out.println("3. Adicionar Item ao Pedido");
            System.out.println("4. Exibir Status da Mesa");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcaoMenu(scanner);

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    realizarPedido(scanner);
                    break;
                case 3:
                    adicionarItemPedido(scanner);
                    break;
                case 4:
                    exibirStatusMesa(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Encerrando sistema. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

    private static int lerOpcaoMenu(Scanner scanner) {
        int opcao = -1;
        while (true) {
            try {
                opcao = scanner.nextInt();
                if (opcao >= 1 && opcao <= 5) {
                    break;
                } else {
                    System.out.print("Opção inválida. Por favor, escolha uma opção entre 1 e 5: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Digite um número: ");
                scanner.next(); // Limpa a entrada inválida
            }
        }
        return opcao;
    }

    private static void setupMenu() {
        Prato niguiri = new Prato("Niguiri de Salmão", 25.50, "Principal");
        niguiri.adicionarIngrediente("Arroz");
        niguiri.adicionarIngrediente("Salmão");
        menu.add(niguiri);

        Prato harumaki = new Prato("Harumaki", 12.00, "Entrada");
        harumaki.adicionarIngrediente("Massa de Harumaki");
        menu.add(harumaki);

        Bebida chaVerde = new Bebida("Chá Verde", 8.00, false);
        menu.add(chaVerde);

        Bebida saque = new Bebida("Saquê", 18.00, true);
        menu.add(saque);

        mesas.add(new Mesa(1, 4));
        mesas.add(new Mesa(2, 2));
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();

        System.out.println("Selecione uma mesa para o cliente:");
        for (int i = 0; i < mesas.size(); i++) {
            Mesa mesa = mesas.get(i);
            System.out.println((i + 1) + ". Mesa " + mesa.getNumero() + " (Capacidade: " + mesa.getCapacidade() + ")");
        }
        int mesaEscolha = lerEscolhaMesa(scanner);

        if (mesaEscolha > 0 && mesaEscolha <= mesas.size()) {
            Mesa mesaSelecionada = mesas.get(mesaEscolha - 1);
            Cliente cliente = new Cliente(nome, mesaSelecionada, 0, 0);
            clientes.add(cliente);
            System.out.println("Cliente " + nome + " cadastrado na Mesa " + mesaSelecionada.getNumero());
        } else {
            System.out.println("Mesa inválida.");
        }
    }

    private static int lerEscolhaMesa(Scanner scanner) {
        int escolha = -1;
        while (true) {
            try {
                escolha = scanner.nextInt();
                if (escolha >= 1 && escolha <= mesas.size()) {
                    break;
                } else {
                    System.out.print("Escolha inválida. Por favor, escolha uma mesa válida: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Digite um número: ");
                scanner.next();
            }
        }
        return escolha;
    }

    private static void realizarPedido(Scanner scanner) {
        System.out.print("Digite o número da mesa para realizar o pedido: ");
        int numeroMesa = scanner.nextInt();

        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa != null && !mesa.isOcupada()) {
            try {
                mesa.fazerPedido(numeroMesa);
                mesa.reservar();
                System.out.println("Pedido realizado para a Mesa " + numeroMesa);
            } catch (ExcecaoPedidoExiste e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Mesa inválida ou já ocupada.");
        }
    }

    private static void adicionarItemPedido(Scanner scanner) {
        System.out.print("Digite o número da mesa para adicionar item: ");
        int numeroMesa = scanner.nextInt();

        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa != null && mesa.getPedido() != null) {
            System.out.println("Selecione um item do menu para adicionar ao pedido:");
            for (int i = 0; i < menu.size(); i++) {
                ItemMenu item = menu.get(i);
                System.out.println((i + 1) + ". " + item.getNome() + " - R$ " + item.getPreco());
            }
            int itemEscolha = lerEscolhaItemMenu(scanner);
            ItemMenu itemSelecionado = menu.get(itemEscolha - 1);
            mesa.pedirItem(itemSelecionado);
            System.out.println("Item " + itemSelecionado.getNome() + " adicionado ao pedido.");
        } else {
            System.out.println("Mesa inválida ou sem pedido realizado.");
        }
    }

    private static int lerEscolhaItemMenu(Scanner scanner) {
        int escolha = -1;
        while (true) {
            try {
                escolha = scanner.nextInt();
                if (escolha >= 1 && escolha <= menu.size()) {
                    break;
                } else {
                    System.out.print("Escolha inválida. Por favor, escolha um item válido: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Digite um número: ");
                scanner.next();
            }
        }
        return escolha;
    }

    private static void exibirStatusMesa(Scanner scanner) {
        System.out.print("Digite o número da mesa para exibir o status: ");
        int numeroMesa = scanner.nextInt();

        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa != null) {
            mesa.descreverMesa();
        } else {
            System.out.println("Mesa inválida.");
        }
    }

    private static Mesa buscarMesa(int numero) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumero() == numero) {
                return mesa;
            }
        }
        return null;
    }
}
