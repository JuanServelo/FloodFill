import java.util.Arrays;
import java.util.Objects;

public class Lista<E> {
    private Object[] lista;
    private int capacidade = 10;
    private int tamanho = 0;

    public Lista() {
        lista = new Object[capacidade];
    }

    public Lista(int capacidade) {
        this.capacidade = capacidade;
        lista = new Object[capacidade];
    }

    public boolean adicionar(E e) {
        if (estaCheia()) {
            aumentarCapacidade();
        }
        lista[tamanho++] = e;
        return true;
    }

    public boolean adicionar(int index, E e) {
        verificaIndice(index);
        if (estaCheia()) {
            aumentarCapacidade();
        }
        System.arraycopy(lista, index, lista, index + 1, tamanho - index);
        lista[index] = e;
        tamanho++;
        return true;
    }

    public void remover(int index) {
        verificaIndice(index);
        E elemento = (E) lista[index];
        System.arraycopy(lista, index + 1, lista, index, tamanho - index - 1);
        lista[--tamanho] = null;
    }

    public boolean removerVariosElementos(E e) {
        boolean removido = false;
        int index;
        while ((index = indexOf(e)) != -1) {
            remover(index);
            removido = true;
        }
        return removido;
    }

    public boolean removerUmElemento(E e) {
        int index = indexOf(e);
        if (index != -1) {
            remover(index);
            return true;
        }
        return false;
    }

    public E get(int index) {
        verificaIndice(index);
        return (E) lista[index];
    }

    public Object[] getLista() {
        return Arrays.copyOf(lista, tamanho);
    }

    public boolean set(int index, E e) {
        verificaIndice(index);
        lista[index] = e;
        return true;
    }

    public int indexOf(E e) {
        for (int i = 0; i < tamanho; i++) {
            if (Objects.equals(lista[i], e)){
                return i;
            }
        }
        return -1;
    }

    private void verificaIndice(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }
    }

    private void aumentarCapacidade() {
        capacidade *= 2;
        lista = Arrays.copyOf(lista, capacidade);
    }

    private boolean estaCheia() {
        return tamanho == capacidade;
    }

}