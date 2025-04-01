class Pilha<T> {
    private No<T> topo;

    public void empilhar(T dado) {
        No<T> novo = new No<>(dado);
        novo.proximo = topo;
        topo = novo;
    }

    public T desempilhar() {
        if (estaVazia()) return null;
        T dado = topo.dado;
        topo = topo.proximo;
        return dado;
    }

    public boolean estaVazia() {
        return topo == null;
    }
}