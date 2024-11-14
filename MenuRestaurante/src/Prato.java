enum TipoPrato {
    Entrada, Principal, Sobremesa
}

// Classe Prato que herda de ItemMenu
class Prato extends ItemMenu {
    private final TipoPrato tipoPrato; // Ex: Entrada, Principal, Sobremesa

    public Prato(String nome, double preco, TipoPrato tipoPrato) {
        super(nome, preco);
        this.tipoPrato = tipoPrato;
    }

    @Override
    public void descrever() {
        System.out.println("Prato: " + getNome() + " - Tipo: " + tipoPrato + " - Preço: R$ " + getPreco());
        System.out.println("Ingredientes: " + String.join(", ", getIngredientes()));
    }
}
