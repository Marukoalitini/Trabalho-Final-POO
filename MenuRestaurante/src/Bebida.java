// Classe Bebida que herda de ItemMenu
class Bebida extends ItemMenu {
    private boolean alcoolica;

    public Bebida(String nome, double preco, boolean alcoolica) {
        super(nome, preco);
        this.alcoolica = alcoolica;
    }

    @Override
    public void descrever() {
        String tipoBebida = alcoolica ? "Alcoólica" : "Não Alcoólica";
        System.out.println("Bebida: " + getNome() + " - Tipo: " + tipoBebida + " - Preço: R$ " + getPreco());
        System.out.println("Ingredientes: " + String.join(", ", getIngredientes()));
    }
}
