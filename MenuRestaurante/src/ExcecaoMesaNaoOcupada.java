public class ExcecaoMesaNaoOcupada extends Exception {
    public ExcecaoMesaNaoOcupada(String mensagem) {
        super(mensagem);
    }
    public ExcecaoMesaNaoOcupada() {
        super("A mesa não está ocupada.");
    }
}
