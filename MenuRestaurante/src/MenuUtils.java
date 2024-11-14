import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuUtils {

    // Método para salvar pratos e bebidas em seus respectivos CSVs
    public static void salvarItensEmCSV(List<ItemMenu> itens) {
        ArrayList<Prato> pratos = new ArrayList<>();
        ArrayList<Bebida> bebidas = new ArrayList<>();

        // Separa os itens em pratos e bebidas
        for (ItemMenu item : itens) {
            if (item instanceof Prato) {
                pratos.add((Prato) item);
            } else if (item instanceof Bebida) {
                bebidas.add((Bebida) item);
            }
        }

        // Salva os pratos e bebidas em seus respectivos arquivos CSV
        salvarPratosEmCSV(pratos);
        salvarBebidasEmCSV(bebidas);
    }

    public static void salvarPratosEmCSV(ArrayList<Prato> pratos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pratos.csv"))) {
            writer.write("Nome,Preço,Tipo\n"); // Cabeçalho
            for (Prato prato : pratos) {
                // Escrevendo cada prato no formato CSV
                writer.write(prato.getNome() + "," + prato.getPreco() + "," + prato.getTipo() + "\n");
            }
            System.out.println("Pratos salvos em pratos.csv com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void salvarBebidasEmCSV(ArrayList<Bebida> bebidas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bebidas.csv"))) {
            writer.write("Nome,Preço,Tipo,Ingredientes\n"); // Cabeçalho
            for (Bebida bebida : bebidas) {

                // Escrevendo cada bebida no formato CSV
                writer.write(bebida.getNome() + "," + bebida.getPreco() + "," + (bebida.getAlcool() ? "Alcoólica" : "Não Alcoólica") + "\n");
            }
            System.out.println("Bebidas salvas em bebidas.csv com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Prato> lerPratosDoCSV(String arquivo) {
        List<Prato> pratos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            reader.readLine(); // Lê o cabeçalho e ignora
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                String nome = dados[0];
                double preco = Double.parseDouble(dados[1]);
                String tipoPrato = dados[2];

                // Agora o prato é criado com os ingredientes
                Prato prato = new Prato(nome, preco, TipoPrato.valueOf(tipoPrato));
                pratos.add(prato);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pratos;
    }

    // Método para ler bebidas de um arquivo CSV
    public static List<Bebida> lerBebidasDoCSV(String arquivo) {
        List<Bebida> bebidas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            reader.readLine(); // Lê o cabeçalho e ignora
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                String nome = dados[0];
                double preco = Double.parseDouble(dados[1]);
                String tipoBebida = dados[2];

                // Agora a bebida é criada com os ingredientes
                Bebida bebida = new Bebida(nome, preco, tipoBebida.equals("Alcoólica"));
                bebidas.add(bebida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bebidas;
    }

    // Metodo para carregar ambos os tipos de itens (Pratos e Bebidas) e armazenar em listas
    public static void carregarMenu(List<ItemMenu> menu) {
        // Carrega pratos e bebidas
        List<Prato> pratos = lerPratosDoCSV("pratos.csv");
        List<Bebida> bebidas = lerBebidasDoCSV("bebidas.csv");

        // Adiciona os pratos e bebidas na lista de menu fornecida
        menu.addAll(pratos);
        menu.addAll(bebidas);

        // Aqui você pode manipular ou exibir os itens, por exemplo:
        System.out.println("Itens carregados no menu:");
        for (ItemMenu item : menu) {
            if (item instanceof Prato) {
                Prato prato = (Prato) item;
                System.out.println("Prato: " + prato.getNome() + " - " + prato.getPreco());
            } else if (item instanceof Bebida) {
                Bebida bebida = (Bebida) item;
                System.out.println("Bebida: " + bebida.getNome() + " - " + bebida.getPreco());
            }
        }
    }
}
