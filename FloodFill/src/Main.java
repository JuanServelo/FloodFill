import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Imagem imagem = new Imagem("foto.png");
                Janela janela = new Janela(imagem);
                janela.setVisible(true);

                //preencher com pilha
                //Preenchimento.preencherComPilha(imagem, 10, 90, Color.RED, janela);

                //preencher com fila
                Preenchimento.preencherComFila(imagem, 10, 90, Color.RED, janela);
                imagem.salvar("saida_pilha.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}