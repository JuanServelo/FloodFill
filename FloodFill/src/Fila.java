class Fila<T> {
    private No<T> frente, tras;

    public void enfileirar(T dado) {
        No<T> novo = new No<>(dado);
        if (tras != null) {
            tras.proximo = novo;
        }
        tras = novo;
        if (frente == null) {
            frente = novo;
        }
    }

    public T desenfileirar() {
        if (estaVazia()) return null;
        T dado = frente.dado;
        frente = frente.proximo;
        if (frente == null) {
            tras = null;
        }
        return dado;
    }

    public boolean estaVazia() {
        return frente == null;
    }
}
