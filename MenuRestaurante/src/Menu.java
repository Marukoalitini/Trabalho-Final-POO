import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("------ Bem Vindo ao Menu! ------");
        System.out.println("1. Adicionar Garçom");
        System.out.println("2. Adicionar Mesa");
        System.out.println("3. Reservar Mesa");
        System.out.println("4. Liberar Mesa");
        System.out.println("5. Fazer Pedido");
        System.out.println("6. Adicionar Item ao Pedido");
        System.out.println("7. Descrever Pedido");
        System.out.println("8. Descrever Mesa");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");


        opcao = scanner.nextInt();


        if (opcao == 1) {
            System.out.print("Nome do garçom: ");
            String nome = scanner.next();
            System.out.print("Salário do garçom: ");
            double salario = scanner.nextDouble();
            System.out.print("Turno (Manhã, Tarde, Noite): ");
            String turno = scanner.next();
            Turno turno = Turno.valueOf(turno);
            Garcom garcom = new Garcom(nome, salario, turno);
            restaurante.adicionarGarcom(garcom);
            System.out.println("Garçom adicionado com sucesso.");

        } else if (opcao == 2) {
            System.out.print("Número da mesa: ");
            int numero = scanner.nextInt();
            System.out.print("Capacidade da mesa: ");
            int capacidade = scanner.nextInt();
            Mesa mesa = new Mesa(numero, capacidade);
            restaurante.adicionarMesa(mesa);
            System.out.println("Mesa adicionada com sucesso.");

        } else if (opcao == 3) {
            System.out.print("Número da mesa para reservar: ");
            int numero = scanner.nextInt();
            Garcom garcom = restaurante.escolherGarcom();
            Mesa mesa = restaurante.buscarMesa(numero);
            if (mesa != null && garcom != null) {
                mesa.reservar(garcom);
                System.out.println("Mesa reservada com sucesso.");
            } else {
                System.out.println("Mesa ou garçom não encontrado.");
            }

        } else if (opcao == 4) {
            System.out.print("Número da mesa para liberar: ");
            int numero = scanner.nextInt();
            Mesa mesa = restaurante.buscarMesa(numero);
            if (mesa != null) {
                mesa.liberar();
                System.out.println("Mesa liberada com sucesso.");
            } else {
                System.out.println("Mesa não encontrada.");
            }

        } else if (opcao == 5) {
            System.out.print("Número da mesa para fazer pedido: ");
            int numero = scanner.nextInt();
            System.out.print("ID do pedido: ");
            int idPedido = scanner.nextInt();
            Mesa mesa = restaurante.buscarMesa(numero);
            if (mesa != null && mesa.isOcupada()) {
                mesa.fazerPedido(idPedido);
                System.out.println("Pedido criado com sucesso.");
            } else {
                System.out.println("Mesa não ocupada ou inexistente.");
            }

        } else if (opcao == 6) {
            System.out.print("Número da mesa para adicionar item ao pedido: ");
            int numero = scanner.nextInt();
            System.out.print("Nome do item: ");
            String nomeItem = scanner.next();
            System.out.print("Preço do item: ");
            double precoItem = scanner.nextDouble();
            System.out.print("Tipo do item (1 para Prato, 2 para Bebida): ");
            int tipo = scanner.nextInt();

            ItemMenu item;
            if (tipo == 1) {
                System.out.print("Tipo de prato (Entrada, Principal, Sobremesa): ");
                String tipoPrato = scanner.next();
                item = new Prato(nomeItem, precoItem, TipoPrato.valueOf(tipoPrato));
            } else {
                System.out.print("Bebida é alcoólica? (true/false): ");
                boolean alcoolica = scanner.nextBoolean();
                item = new Bebida(nomeItem, precoItem, alcoolica);
            }

            Mesa mesa = restaurante.buscarMesa(numero);
            if (mesa != null && mesa.isOcupada() && mesa.getPedido() != null) {
                mesa.pedirItem(item);
                System.out.println("Item adicionado ao pedido.");
            } else {
                System.out.println("Mesa não ocupada, sem pedido, ou inexistente.");
            }

        } else if (opcao == 7) {
            System.out.print("Número da mesa para descrever o pedido: ");
            int numero = scanner.nextInt();
            Mesa mesa = restaurante.buscarMesa(numero);
            if (mesa != null && mesa.getPedido() != null) {
                mesa.getPedido().descreverPedido();
            } else {
                System.out.println("Pedido não encontrado.");
            }

        } else if (opcao == 8) {
            System.out.print("Número da mesa para descrever: ");
            int numero = scanner.nextInt();
            Mesa mesa = restaurante.buscarMesa(numero);
            if (mesa != null) {
                mesa.descreverMesa();
            } else {
                System.out.println("Mesa não encontrada.");
            }

        } else if (opcao == 9) {
            System.out.println("Saindo...");
        } else {
            System.out.println("Opção inválida.");
        }

    }
}