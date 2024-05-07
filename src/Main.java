import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        String caminhoImagem = "src/imagensTeste/mamografia4.png";
        CarregarImagem CarregadorImagem = new CarregarImagem();
        ConverterCinza ConversorEscalaCinza = new ConverterCinza();
        EqualizarHistograma EqualizadorHist = new EqualizarHistograma();
        Sobel sobelBordas = new Sobel();
        Limiarizacao limiar = new Limiarizacao();
        BufferedImage imagemOriginal = CarregadorImagem.carregarImagem(caminhoImagem);
        BufferedImage imagemCinza = ConversorEscalaCinza.converterParaEscalaCinza(imagemOriginal);
        BufferedImage imagemEqualizada = EqualizadorHist.equalizaHistograma(imagemCinza);
        BufferedImage imagemBordas = sobelBordas.detectarBordas(imagemEqualizada);
        BufferedImage imagemLimiarizada = limiar.aplicarLimiarizacao(imagemCinza, 128);

        mostrarImagem(imagemOriginal, imagemCinza, imagemEqualizada, imagemBordas, imagemLimiarizada);
    }

    public static void mostrarImagem(BufferedImage original, BufferedImage cinza, BufferedImage equalizada,
                                     BufferedImage bordas, BufferedImage limiarizada) {
        JFrame frame = new JFrame("Imagens Processadas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));

        JPanel panelSuperior = new JPanel(new FlowLayout());
        JPanel panelInferior = new JPanel(new FlowLayout());

        JLabel originalLabel = new JLabel(new ImageIcon(original.getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        JLabel cinzaLabel = new JLabel(new ImageIcon(cinza.getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        JLabel equalizadaLabel = new JLabel(new ImageIcon(equalizada.getScaledInstance(300, 300, Image.SCALE_SMOOTH)));

        JLabel bordasLabel = new JLabel(new ImageIcon(bordas.getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        JLabel limiarizadaLabel = new JLabel(new ImageIcon(limiarizada.getScaledInstance(300, 300, Image.SCALE_SMOOTH)));

        originalLabel.setText("Original");
        cinzaLabel.setText("Escala de Cinza");
        equalizadaLabel.setText("Equalização de Histograma");

        bordasLabel.setText("Detecção de Bordas (Sobel)");
        limiarizadaLabel.setText("Limiarização (128)");

        panelSuperior.add(originalLabel);
        panelSuperior.add(cinzaLabel);
        panelSuperior.add(equalizadaLabel);

        panelInferior.add(bordasLabel);
        panelInferior.add(limiarizadaLabel);

        frame.add(panelSuperior);
        frame.add(panelInferior);
        frame.pack();
        frame.setVisible(true);
    }

}
