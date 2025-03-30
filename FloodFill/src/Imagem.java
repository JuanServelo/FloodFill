import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Imagem {
    private BufferedImage imagem;

    public Imagem(String caminho) throws IOException {
        this.imagem = ImageIO.read(new File(caminho));
    }

    public void salvar(String caminho) throws IOException {
        ImageIO.write(imagem, "png", new File(caminho));
    }

    public void pintarPixel(int x, int y, Color cor) {
        imagem.setRGB(x, y, cor.getRGB());
    }

    public Color getCorPixel(int x, int y) {
        return new Color(imagem.getRGB(x, y));
    }

    public int getLargura() {
        return imagem.getWidth();
    }

    public int getAltura() {
        return imagem.getHeight();
    }

    public BufferedImage getImagem() {
        return imagem;
    }
}