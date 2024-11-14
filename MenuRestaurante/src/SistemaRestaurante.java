import java.util.*;

public class SistemaRestaurante {
    private static final List<ItemMenu> menu = new ArrayList<>();
    private static final List<Mesa> mesas = new ArrayList<>();
    private static final List<Garcom> garcons = new ArrayList<>();

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
            System.out.println("7. Exibir Ganhos do Garçom");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = lerOpcao(scanner, 7);

            switch (opcao) {
                case 1 -> areaDeCadastramento(scanner);
                case 2 -> areaDeExclusao(scanner);
                case 3 -> cadastrarCliente(scanner);
                case 4 -> fazerPedido(scanner);
                case 5 -> exibirStatusMesa(scanner);
                case 6 -> encerrarContaLiberarMesa(scanner);
                case 7 -> exibirGorjetasEGanhos(scanner);
                case 8 -> {
                    System.out.println("Encerrando sistema.");
                    return;
                }
            }
        }
    }

    private static void areaDeCadastramento(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Área de Cadastramento ---");
            System.out.println("1. Nova Mesa");
            System.out.println("2. Nova Bebida");
            System.out.println("3. Novo Prato");
            System.out.println("4. Novo Garçom");
            System.out.println("5. Voltar ao Menu Principal");
            int opcao = lerOpcao(scanner, 5);

            switch (opcao) {
                case 1 -> cadastrarNovaMesa(scanner);
                case 2 -> cadastrarNovaBebida(scanner);
                case 3 -> cadastrarNovoPrato(scanner);
                case 4 -> cadastrarNovoGarcom(scanner);
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static void areaDeExclusao(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Área de Exclusão ---");
            System.out.println("1. Excluir Mesa");
            System.out.println("2. Excluir Bebida");
            System.out.println("3. Excluir Prato");
            System.out.println("4. Excluir Garçom");
            System.out.println("5. Voltar ao Menu Principal");
            int opcao = lerOpcao(scanner, 5);

            switch (opcao) {
                case 1 -> excluirMesa(scanner);
                case 2 -> excluirBebida(scanner);
                case 3 -> excluirPrato(scanner);
                case 4 -> excluirGarcom(scanner);
                case 5 -> {
                    return;
                }
            }
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

        boolean alcoolica;
        while (true) {
            System.out.print("A bebida é alcoólica? (1 para Sim / 2 para Não): ");
            String escolha = scanner.next();
            if (escolha.equals("1")) {
                alcoolica = true;
                break;
            } else if (escolha.equals("2")) {
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
        String tipo = scanner.next();
        while (!Objects.equals(tipo, "Entrada") && !Objects.equals(tipo, "Principal") && !Objects.equals(tipo, "Sobremesa")) {
            System.out.println("Insira um tipo válido: Entrada, Principal, Sobremesa");
            System.out.print("Tipo: ");
            tipo = scanner.next();
        }
        TipoPrato tipoCorrigido;
        if (Objects.equals(tipo, "Entrada")) {
            tipoCorrigido = TipoPrato.Entrada;
        } else if (Objects.equals(tipo, "Principal")) {
            tipoCorrigido = TipoPrato.Principal;
        } else {
            tipoCorrigido = TipoPrato.Sobremesa;
        }

        Prato prato = new Prato(nome, preco, tipoCorrigido);
        menu.add(prato);
        System.out.println("Novo prato cadastrado com sucesso.");
    }

    private static void cadastrarNovoGarcom(Scanner scanner) {
        System.out.print("Nome do garçom: ");
        String nome = scanner.next();

        double salario = lerNumeroDecimal(scanner, "Salário: ");

        Turno turno = selecionarTurno(scanner);

        Garcom garcom = new Garcom(nome, salario, turno);
        garcons.add(garcom);
        System.out.println("Novo garçom cadastrado com sucesso.");
    }

    private static Turno selecionarTurno(Scanner scanner) {
        while (true) {
            System.out.println("Selecione o turno do garçom:");
            System.out.println("1. Manhã");
            System.out.println("2. Tarde");
            System.out.println("3. Noite");
            int opcao = lerOpcao(scanner, 3);

            switch (opcao) {
                case 1 -> {
                    return Turno.Manhã;
                }
                case 2 -> {
                    return Turno.Tarde;
                }
                case 3 -> {
                    return Turno.Noite;
                }
                default -> System.out.println("Opção inválida. Escolha entre 1 e 3.");
            }
        }
    }

    private static void excluirMesa(Scanner scanner) {
        if (mesas.isEmpty()) {
            System.out.println("Não há mesas para excluir.");
            return;
        }
        System.out.println("Escolha a mesa para excluir:");
        for (int i = 0; i < mesas.size(); i++)
            System.out.println((i + 1) + ". Mesa " + mesas.get(i).getNumero());
        int escolha = lerOpcao(scanner, mesas.size());
        if (escolha > 0 && escolha <= mesas.size()) {
            mesas.remove(escolha - 1);
            System.out.println("Mesa excluída.");
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void excluirBebida(Scanner scanner) {
        List<Bebida> bebidas = new ArrayList<>();
        for (ItemMenu item : menu) {
            if (item instanceof Bebida) bebidas.add((Bebida) item);
        }
        if (bebidas.isEmpty()) {
            System.out.println("Não há bebidas para excluir.");
            return;
        }
        System.out.println("Escolha a bebida para excluir:");
        for (int i = 0; i < bebidas.size(); i++)
            System.out.println((i + 1) + ". " + bebidas.get(i).getNome());
        int escolha = lerOpcao(scanner, bebidas.size());
        if (escolha > 0 && escolha <= bebidas.size()) {
            menu.remove(bebidas.get(escolha - 1));
            System.out.println("Bebida excluída.");
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void excluirPrato(Scanner scanner) {
        List<Prato> pratos = new ArrayList<>();
        for (ItemMenu item : menu) {
            if (item instanceof Prato) pratos.add((Prato) item);
        }
        if (pratos.isEmpty()) {
            System.out.println("Não há pratos para excluir.");
            return;
        }
        System.out.println("Escolha o prato para excluir:");
        for (int i = 0; i < pratos.size(); i++)
            System.out.println((i + 1) + ". " + pratos.get(i).getNome());
        int escolha = lerOpcao(scanner, pratos.size());
        if (escolha > 0 && escolha <= pratos.size()) {
            menu.remove(pratos.get(escolha - 1));
            System.out.println("Prato excluído.");
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void excluirGarcom(Scanner scanner) {
        if (garcons.isEmpty()) {
            System.out.println("Não há garçons para excluir.");
            return;
        }
        System.out.println("Escolha o garçom para excluir:");
        for (int i = 0; i < garcons.size(); i++)
            System.out.println((i + 1) + ". " + garcons.get(i).getNome());
        int escolha = lerOpcao(scanner, garcons.size());
        if (escolha > 0 && escolha <= garcons.size()) {
            garcons.remove(escolha - 1);
            System.out.println("Garçom excluído.");
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void cadastrarCliente(Scanner scanner) {
        if (mesas.isEmpty()) {
            System.out.println("Não há mesas disponíveis para associar.");
            return;
        }
        if (garcons.isEmpty()) {
            System.out.println("Não há garçons disponíveis para associar.");
            return;
        }
        System.out.print("Nome do cliente: ");
        String nome = scanner.next();
        System.out.println("Escolha uma mesa para associar:");
        for (int i = 0; i < mesas.size(); i++) {
            if (!mesas.get(i).isOcupada()) {
                System.out.println((i + 1) + ". Mesa " + mesas.get(i).getNumero());
            }
        }
        int mesaEscolha = lerOpcao(scanner, mesas.size());
        Mesa mesa = mesas.get(mesaEscolha - 1);
        System.out.println("Escolha um garçom:");
        for (int i = 0; i < garcons.size(); i++) {
            System.out.println((i + 1) + ". " + garcons.get(i).getNome());
        }
        int garcomEscolha = lerOpcao(scanner, garcons.size());
        Garcom garcom = garcons.get(garcomEscolha - 1);
        mesa.reservar(garcom);
        System.out.println("Cliente " + nome + " associado à mesa " + mesa.getNumero());
    }

    private static void fazerPedido(Scanner scanner) {
        System.out.println("Escolha a mesa para fazer o pedido:");
        for (int i = 0; i < mesas.size(); i++) {
            System.out.println((i + 1) + ". Mesa " + mesas.get(i).getNumero());
        }
        System.out.println((mesas.size() + 1) + ". Voltar ao Menu Principal");

        int mesaEscolha = lerOpcao(scanner, mesas.size() + 1);
        if (mesaEscolha == mesas.size() + 1) return;

        Mesa mesa = mesas.get(mesaEscolha - 1);

        try {
            while (true) {
                System.out.println("\nEscolha o tipo de item para adicionar ao pedido:");
                System.out.println("1. Bebida\n2. Prato\n3. Voltar ao Menu Principal");
                int tipoItem = lerOpcao(scanner, 3);

                if (tipoItem == 3) return;

                List<ItemMenu> itensDisponiveis = new ArrayList<>();
                if (tipoItem == 1) {
                    System.out.println("\n--- Bebidas Disponíveis ---");
                    for (ItemMenu item : menu) {
                        if (item instanceof Bebida) {
                            itensDisponiveis.add(item);
                            System.out.println(itensDisponiveis.size() + ". " + item.getNome() + " - R$ " + item.getPreco());
                        }
                    }
                } else if (tipoItem == 2) {
                    System.out.println("\n--- Pratos Disponíveis ---");
                    for (ItemMenu item : menu) {
                        if (item instanceof Prato) {
                            itensDisponiveis.add(item);
                            System.out.println(itensDisponiveis.size() + ". " + item.getNome() + " - R$ " + item.getPreco());
                        }
                    }
                }

                System.out.println((itensDisponiveis.size() + 1) + ". Voltar ao Menu Principal");

                int itemEscolha = lerOpcao(scanner, itensDisponiveis.size() + 1);
                if (itemEscolha == itensDisponiveis.size() + 1) return;

                ItemMenu itemEscolhido = itensDisponiveis.get(itemEscolha - 1);
                mesa.pedirItem(itemEscolhido); // Lança a exceção se a mesa não estiver ocupada
                System.out.println("Item " + itemEscolhido.getNome() + " adicionado ao pedido.");
            }
        } catch (ExcecaoMesaNaoOcupada e) {
            System.out.println(e.getMessage());
        }
    }

    private static void exibirStatusMesa(Scanner scanner) {
        System.out.println("Escolha a mesa para ver o status:");
        for (int i = 0; i < mesas.size(); i++) {
            System.out.println((i + 1) + ". Mesa " + mesas.get(i).getNumero());
        }
        System.out.println((mesas.size() + 1) + ". Voltar ao Menu Principal");

        int mesaEscolha = lerOpcao(scanner, mesas.size() + 1);
        if (mesaEscolha == mesas.size() + 1) return;

        Mesa mesa = mesas.get(mesaEscolha - 1);

        if (!mesa.isOcupada()) {
            System.out.println("Mesa está vazia.");
            return;
        }

        System.out.println("\n--- Status da Mesa " + mesa.getNumero() + " ---");
        System.out.println("Garçom Responsável: " + (mesa.getGarcomResponsavel() != null ? mesa.getGarcomResponsavel().getNome() : "Nenhum"));

        if (mesa.getItensPedido() == null || mesa.getItensPedido().isEmpty()) {
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
        for (int i = 0; i < mesas.size(); i++) {
            System.out.println((i + 1) + ". Mesa " + mesas.get(i).getNumero());
        }
        System.out.println((mesas.size() + 1) + ". Voltar ao Menu Principal");

        int mesaEscolha = lerOpcao(scanner, mesas.size() + 1);
        if (mesaEscolha == mesas.size() + 1) return;

        Mesa mesa = mesas.get(mesaEscolha - 1);

        if (!mesa.isOcupada()) {
            System.out.println("Mesa já está vazia.");
            return;
        }

        double gorjeta = 0.0;
        while (true) {
            System.out.print("Deseja adicionar gorjeta? (1 para Sim / 2 para Não): ");
            String escolha = scanner.next();
            if (escolha.equals("1")) {
                System.out.print("Digite o valor da gorjeta: ");
                gorjeta = lerNumeroDecimal(scanner, "");
                break;
            } else if (escolha.equals("2")) {
                break;
            } else {
                System.out.println("Entrada inválida. Digite 1 para Sim ou 2 para Não.");
            }
        }

        mesa.getGarcomResponsavel().adicionarGorjeta(gorjeta);

        System.out.println("\n--- Encerrando Conta da Mesa " + mesa.getNumero() + " ---");
        System.out.println("Total da Conta: R$ " + mesa.getTotalConta());
        System.out.println("Gorjeta: R$ " + gorjeta);
        mesa.liberar();
        System.out.println("Mesa " + mesa.getNumero() + " foi liberada.");
    }

    private static void exibirGorjetasEGanhos(Scanner scanner) {
        if (garcons.isEmpty()) {
            System.out.println("Não há garçons cadastrados.");
            return;
        }
        System.out.println("Escolha o garçom para ver as gorjetas e o ganho total:");
        for (int i = 0; i < garcons.size(); i++) {
            System.out.println((i + 1) + ". " + garcons.get(i).getNome());
        }
        int escolha = lerOpcao(scanner, garcons.size());
        Garcom garcom = garcons.get(escolha - 1);
        System.out.println("Garçom: " + garcom.getNome());
        System.out.println("Gorjeta Total: R$ " + garcom.getGorjetaTotal());
        System.out.println("Ganho Total (Salário + Gorjeta): R$ " + garcom.calcularGanhoTotal());
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

    private static double lerNumeroDecimal(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Digite um número válido: ");
                scanner.next();
            }
        }
    }

    private static int lerNumeroInteiro(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Digite um número válido: ");
                scanner.next();
            }
        }
    }

    private static void setupMenu() {
        // Pratos
        menu.add(new Prato("Niguiri de Salmão", 25.50, TipoPrato.Principal));
        menu.add(new Prato("Sashimi de Atum", 30.00, TipoPrato.Principal));
        menu.add(new Prato("Temaki de Salmão", 18.00, TipoPrato.Principal));
        menu.add(new Prato("Hot Roll", 22.00, TipoPrato.Entrada));
        menu.add(new Prato("Uramaki Califórnia", 20.00, TipoPrato.Principal));
        menu.add(new Prato("Yakissoba de Frango", 28.00, TipoPrato.Principal));
        menu.add(new Prato("Shimeji na Manteiga", 15.00, TipoPrato.Entrada));
        menu.add(new Prato("Harumaki de Legumes", 12.00, TipoPrato.Entrada));
        menu.add(new Prato("Tempura de Camarão", 35.00, TipoPrato.Principal));
        menu.add(new Prato("Tartar de Salmão", 27.00, TipoPrato.Entrada));
        menu.add(new Prato("Mochi", 10.00, TipoPrato.Sobremesa));

        // Bebidas
        menu.add(new Bebida("Chá Verde", 8.00, false));
        menu.add(new Bebida("Sake Importado", 45.00, true));
        menu.add(new Bebida("Sakerinha de Frutas Vermelhas", 18.00, true));
        menu.add(new Bebida("Água com Gás", 5.00, false));
        menu.add(new Bebida("Refrigerante", 6.00, false));
        menu.add(new Bebida("Suco Natural de Laranja", 10.00, false));
        menu.add(new Bebida("Sapporo (Cerveja Importada)", 20.00, true));
        menu.add(new Bebida("Coquetel de Uva com Soda", 15.00, false));
        menu.add(new Bebida("Matcha Latte", 12.00, false));
        menu.add(new Bebida("Limonada com Gengibre", 9.00, false));

        // Mesas e Garçons
        mesas.add(new Mesa(1, 4));
        mesas.add(new Mesa(2, 6));
        mesas.add(new Mesa(3, 2));

        garcons.add(new Garcom("Carlos", 1500, Turno.Noite));
        garcons.add(new Garcom("Maria", 1450, Turno.Tarde));
        garcons.add(new Garcom("João", 1400, Turno.Manhã));
    }
}