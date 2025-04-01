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

                Preenchimento.preencherComPilha(imagem, 90, 10, Color.BLUE, janela);

                Preenchimento.preencherComFila(imagem, 10, 90, Color.RED, janela);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}