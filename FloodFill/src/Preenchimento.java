import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.IIOImage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


class Preenchimento {
    public static void preencherComPilha(Imagem img, int x, int y, Color novaCor, Janela janela) {
        new SwingWorker<Void, BufferedImage>() {
            ArrayList<BufferedImage> frames = new ArrayList<>();

            @Override
            protected Void doInBackground() {
                Pilha<Ponto> pilha = new Pilha<>();
                Color corOriginal = img.getCorPixel(x, y);
                if (corOriginal.equals(novaCor)) return null;

                pilha.empilhar(new Ponto(x, y));

                while (!pilha.estaVazia()) {
                    Ponto p = pilha.desempilhar();
                    if (p.x < 0 || p.x >= img.getLargura() || p.y < 0 || p.y >= img.getAltura()) continue;
                    if (!img.getCorPixel(p.x, p.y).equals(corOriginal)) continue;

                    img.pintarPixel(p.x, p.y, novaCor);
                    frames.add(cloneImage(img.getImagem()));
                    publish(img.getImagem());

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    pilha.empilhar(new Ponto(p.x + 1, p.y));
                    pilha.empilhar(new Ponto(p.x - 1, p.y));
                    pilha.empilhar(new Ponto(p.x, p.y + 1));
                    pilha.empilhar(new Ponto(p.x, p.y - 1));
                }
                criarGif(frames, "preenchimento.gif");
                return null;
            }

            @Override
            protected void process(java.util.List<BufferedImage> chunks) {
                janela.atualizarImagem(chunks.get(chunks.size() - 1));
            }
        }.execute();
    }

    public static void preencherComFila(Imagem img, int x, int y, Color novaCor, Janela janela) {
        new SwingWorker<Void, BufferedImage>() {
            ArrayList<BufferedImage> frames = new ArrayList<>();

            @Override
            protected Void doInBackground() {
                Fila<Ponto> fila = new Fila<>();
                Color corOriginal = img.getCorPixel(x, y);
                if (corOriginal.equals(novaCor)){
                    return null;
                }

                fila.enfileirar(new Ponto(x, y));

                while (!fila.estaVazia()) {
                    Ponto p = fila.desenfileirar();
                    if (p.x < 0 || p.x >= img.getLargura() || p.y < 0 || p.y >= img.getAltura()) continue;
                    if (!img.getCorPixel(p.x, p.y).equals(corOriginal)) continue;

                    img.pintarPixel(p.x, p.y, novaCor);
                    frames.add(cloneImage(img.getImagem()));
                    publish(img.getImagem());

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    fila.enfileirar(new Ponto(p.x + 1, p.y));
                    fila.enfileirar(new Ponto(p.x - 1, p.y));
                    fila.enfileirar(new Ponto(p.x, p.y + 1));
                    fila.enfileirar(new Ponto(p.x, p.y - 1));
                }
                criarGif(frames, "preenchimento_fila.gif");
                return null;
            }

            @Override
            protected void process(java.util.List<BufferedImage> chunks) {
                janela.atualizarImagem(chunks.get(chunks.size() - 1));
            }
        }.execute();
    }

    private static BufferedImage cloneImage(BufferedImage img) {
        BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics g = copy.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return copy;
    }

    private static void criarGif(ArrayList<BufferedImage> frames, String caminho) {
        try {
            ImageWriter writer = ImageIO.getImageWritersByFormatName("gif").next();
            ImageOutputStream output = new FileImageOutputStream(new File(caminho));
            writer.setOutput(output);
            writer.prepareWriteSequence(null);
            for (BufferedImage frame : frames) {
                writer.writeToSequence(new IIOImage(frame, null, null), null);
                Thread.sleep(100);
            }
            writer.endWriteSequence();
            writer.dispose();
            output.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}