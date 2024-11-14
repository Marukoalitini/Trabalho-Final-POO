class ExcecaoPedidoExiste extends Exception {
    public ExcecaoPedidoExiste(String mensagem) {
        super(mensagem);
    }

    public ExcecaoPedidoExiste() {
        super("Pedido já existe para esta mesa.");
    }
}
