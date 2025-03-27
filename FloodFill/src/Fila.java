interface FilaInterface<T> {
    void adicionar(T elemento);
    T remover();
    T primeiroElemento();
    boolean isEmpty();
    int tamanho();
}

public class Fila<T> implements FilaInterface<T> {

    private static class Nodo<T> {
        T dado;
        Nodo<T> proximo;

        public Nodo(T dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    private Nodo<T> frente;
    private Nodo<T> tras;
    private int tamanho;

    public Fila() {
        this.frente = this.tras = null;
        this.tamanho = 0;
    }

    public void adicionar(T elemento) {
        Nodo<T> novoNodo = new Nodo<>(elemento);
        if (isEmpty()) {
            frente = tras = novoNodo;
        } else {
            tras.proximo = novoNodo;
            tras = novoNodo;
        }
        tamanho++;
    }

    public T remover() {
        if (isEmpty()) {
            throw new IllegalStateException("Fila vazia!");
        }
        T dado = frente.dado;
        frente = frente.proximo;
        if (frente == null) { // Se a fila ficou vazia, resetamos 'tras'
            tras = null;
        }
        tamanho--;
        return dado;
    }

    public T primeiroElemento() {
        if (isEmpty()) {
            throw new IllegalStateException("Fila vazia!");
        }
        return frente.dado;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public int tamanho() {
        return tamanho;
    }

}