import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class Janela extends JFrame {
    private JLabel labelImagem;

    public Janela(Imagem imagem) {
        setTitle("Flood Fill");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        labelImagem = new JLabel(new ImageIcon(imagem.getImagem()));
        add(labelImagem, BorderLayout.CENTER);
    }

    public void atualizarImagem(BufferedImage novaImagem) {
        labelImagem.setIcon(new ImageIcon(novaImagem));
        repaint();
    }
}