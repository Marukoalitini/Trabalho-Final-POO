import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
                case 7 -> { System.out.println("Encerrando sistema."); return; }
            }
        }
    }

    private static int lerOpcao(Scanner scanner, int maxOpcao) {
        while (true) {
            try {
                int opcao = scanner.nextInt();
                if (opcao >= 1 && opcao <= maxOpcao) return opcao;
                System.out.print("Escolha entre 1 e " + maxOpcao + ": ");
            } catch (InputMismatchException e) {
                System.out.print("Digite um número: ");
                scanner.next();
            }
        }
    }

    private static void setupMenu() {
        menu.add(new Prato("Niguiri de Salmão", 25.50, "Principal"));
        menu.add(new Bebida("Chá Verde", 8.00, false));
        mesas.add(new Mesa(1, 4));
        garcons.add(new Garcom("Carlos", null, 1500, "Noite"));
    }

    private static void areaDeCadastramento(Scanner scanner) {
        System.out.println("\n--- Área de Cadastramento ---");
        System.out.println("1. Nova Mesa\n2. Nova Bebida\n3. Novo Prato\n4. Novo Garçom\n5. Voltar");
        int opcao = lerOpcao(scanner, 5);

        switch (opcao) {
            case 1 -> cadastrarNovaMesa(scanner);
            case 2 -> cadastrarNovaBebida(scanner);
            case 3 -> cadastrarNovoPrato(scanner);
            case 4 -> cadastrarNovoGarcom(scanner);
        }
    }

    private static void areaDeExclusao(Scanner scanner) {
        System.out.println("\n--- Área de Exclusão ---");
        System.out.println("1. Excluir Mesa\n2. Excluir Bebida\n3. Excluir Prato\n4. Excluir Garçom\n5. Voltar");
        int opcao = lerOpcao(scanner, 5);

        switch (opcao) {
            case 1 -> excluirMesa(scanner);
            case 2 -> excluirBebida(scanner);
            case 3 -> excluirPrato(scanner);
            case 4 -> excluirGarcom(scanner);
        }
    }

    private static void cadastrarNovaMesa(Scanner scanner) {
        System.out.print("Número da nova mesa: ");
        int numero = lerNumeroInteiro(scanner);

        System.out.print("Capacidade: ");
        int capacidade = lerNumeroInteiro(scanner);

        mesas.add(new Mesa(numero, capacidade));
        System.out.println("Mesa cadastrada.");
    }

    private static void cadastrarNovaBebida(Scanner scanner) {
        System.out.print("Nome da bebida: ");
        String nome = scanner.next();

        double preco = lerNumeroDecimal(scanner, "Preço: ");

        boolean alcoolica = false;
        while (true) {
            System.out.print("A bebida é alcoólica? (1 para Sim / 2 para Não): ");
            int escolha = scanner.nextInt();
            if (escolha == 1) {
                alcoolica = true;
                break;
            } else if (escolha == 2) {
                alcoolica = false;
                break;
            } else {
                System.out.println("Opção inválida. Escolha 1 para Sim ou 2 para Não.");
            }
        }

        Bebida bebida = new Bebida(nome, preco, alcoolica);
        menu.add(bebida);
        System.out.println("Nova bebida cadastrada com sucesso.");
    }

    private static void cadastrarNovoPrato(Scanner scanner) {
        System.out.print("Nome do prato: ");
        String nome = scanner.next();

        double preco = lerNumeroDecimal(scanner, "Preço: ");

        System.out.print("Tipo (Entrada/Principal/Sobremesa): ");
        String tipoPrato = scanner.next();

        Prato prato = new Prato(nome, preco, tipoPrato);
        menu.add(prato);
        System.out.println("Novo prato cadastrado com sucesso.");
    }

    private static void cadastrarNovoGarcom(Scanner scanner) {
        System.out.print("Nome do garçom: ");
        String nome = scanner.next();

        double salario = lerNumeroDecimal(scanner, "Salário: ");

        System.out.print("Turno: ");
        String turno = scanner.next();

        Garcom garcom = new Garcom(nome, null, salario, turno);
        garcons.add(garcom);
        System.out.println("Novo garçom cadastrado com sucesso.");
    }

    private static void excluirMesa(Scanner scanner) {
        System.out.println("Escolha a mesa para excluir:");
        for (int i = 0; i < mesas.size(); i++)
            System.out.println((i + 1) + ". Mesa " + mesas.get(i).getNumero());
        int escolha = lerOpcao(scanner, mesas.size());
        mesas.remove(escolha - 1);
        System.out.println("Mesa excluída.");
    }

    private static void excluirBebida(Scanner scanner) {
        List<Bebida> bebidas = new ArrayList<>();
        System.out.println("Escolha a bebida para excluir:");
        for (ItemMenu item : menu) if (item instanceof Bebida) bebidas.add((Bebida) item);
        for (int i = 0; i < bebidas.size(); i++)
            System.out.println((i + 1) + ". " + bebidas.get(i).getNome());
        int escolha = lerOpcao(scanner, bebidas.size());
        menu.remove(bebidas.get(escolha - 1));
        System.out.println("Bebida excluída.");
    }

    private static void excluirPrato(Scanner scanner) {
        List<Prato> pratos = new ArrayList<>();
        System.out.println("Escolha o prato para excluir:");
        for (ItemMenu item : menu) if (item instanceof Prato) pratos.add((Prato) item);
        for (int i = 0; i < pratos.size(); i++)
            System.out.println((i + 1) + ". " + pratos.get(i).getNome());
        int escolha = lerOpcao(scanner, pratos.size());
        menu.remove(pratos.get(escolha - 1));
        System.out.println("Prato excluído.");
    }

    private static void excluirGarcom(Scanner scanner) {
        System.out.println("Escolha o garçom para excluir:");
        for (int i = 0; i < garcons.size(); i++)
            System.out.println((i + 1) + ". " + garcons.get(i).getNome());
        int escolha = lerOpcao(scanner, garcons.size());
        garcons.remove(escolha - 1);
        System.out.println("Garçom excluído.");
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.print("Nome do cliente: ");
        String nome = scanner.next();
        System.out.println("Selecione uma mesa:");
        for (int i = 0; i < mesas.size(); i++)
            if (!mesas.get(i).isOcupada())
                System.out.println((i + 1) + ". Mesa " + mesas.get(i).getNumero());
        int mesaEscolha = lerOpcao(scanner, mesas.size());
        System.out.println("Selecione um garçom:");
        for (int i = 0; i < garcons.size(); i++)
            System.out.println((i + 1) + ". " + garcons.get(i).getNome());
        int garcomEscolha = lerOpcao(scanner, garcons.size());

        Mesa mesa = mesas.get(mesaEscolha - 1);
        Garcom garcom = garcons.get(garcomEscolha - 1);
        mesa.reservar();
        System.out.println("Cliente " + nome + " cadastrado na Mesa " + mesa.getNumero() + " com garçom " + garcom.getNome());
    }

    private static void fazerPedido(Scanner scanner) {
        System.out.print("Número da mesa: ");
        int numeroMesa = scanner.nextInt();
        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa == null || !mesa.isOcupada()) {
            System.out.println("Mesa inválida ou não ocupada.");
            return;
        }

        try {
            mesa.fazerPedido(numeroMesa);
        } catch (ExcecaoPedidoExiste e) {
            System.out.println("Erro ao criar o pedido: " + e.getMessage());
            return;
        }

        boolean adicionando = true;
        while (adicionando) {
            System.out.println("1. Bebida\n2. Prato\n3. Finalizar Pedido");
            int opcao = lerOpcao(scanner, 3);

            switch (opcao) {
                case 1 -> mostrarMenuItens(scanner, mesa, "bebida");
                case 2 -> mostrarMenuItens(scanner, mesa, "prato");
                case 3 -> { adicionando = false; System.out.println("Pedido finalizado."); }
            }
        }
    }

    private static void mostrarMenuItens(Scanner scanner, Mesa mesa, String tipo) {
        List<ItemMenu> itens = new ArrayList<>();
        for (ItemMenu item : menu) {
            if ((tipo.equals("bebida") && item instanceof Bebida) || (tipo.equals("prato") && item instanceof Prato)) {
                itens.add(item);
                System.out.println(itens.size() + ". " + item.getNome() + " - R$ " + item.getPreco());
            }
        }
        int escolha = lerOpcao(scanner, itens.size());
        mesa.pedirItem(itens.get(escolha - 1));
        System.out.println("Item adicionado.");
    }

    private static void exibirStatusMesa(Scanner scanner) {
        System.out.print("Número da mesa: ");
        int numeroMesa = scanner.nextInt();
        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa != null) mesa.descreverMesa();
        else System.out.println("Mesa inválida.");
    }

    private static void encerrarContaLiberarMesa(Scanner scanner) {
        System.out.print("Número da mesa: ");
        int numeroMesa = scanner.nextInt();
        Mesa mesa = buscarMesa(numeroMesa);
        if (mesa != null && mesa.isOcupada()) {
            if (mesa.getPedido() != null) {
                System.out.println("Total a pagar: R$ " + mesa.getValorTotal());
                mesa.liberar();
                System.out.println("Conta encerrada. Mesa liberada.");
            } else System.out.println("Nenhum pedido realizado.");
        } else System.out.println("Mesa inválida ou não ocupada.");
    }

    private static Mesa buscarMesa(int numero) {
        for (Mesa mesa : mesas) if (mesa.getNumero() == numero) return mesa;
        return null;
    }

    private static int lerNumeroInteiro(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Digite um número inteiro: ");
                scanner.next();
            }
        }
    }

    private static double lerNumeroDecimal(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um valor numérico válido (ex: 25,50): ");
                scanner.next();
            }
        }
    }
}
