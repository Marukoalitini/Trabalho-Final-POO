// Classe Prato que herda de ItemMenu
class Prato extends ItemMenu {
    private String tipoPrato; // Ex: Entrada, Principal, Sobremesa

    public Prato(String nome, double preco, String tipoPrato) {
        super(nome, preco);
        this.tipoPrato = tipoPrato;
    }

    @Override
    public void descrever() {
        System.out.println("Prato: " + getNome() + " - Tipo: " + tipoPrato + " - Preço: R$ " + getPreco());
        System.out.println("Ingredientes: " + String.join(", ", getIngredientes()));
    }
}

