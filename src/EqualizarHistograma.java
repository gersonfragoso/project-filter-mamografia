import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class EqualizarHistograma {
    public static BufferedImage equalizaHistograma(BufferedImage imagemEntrada) {
        int largura = imagemEntrada.getWidth();
        int altura = imagemEntrada.getHeight();

        // Calcular o histograma
        int[] histograma = new int[256];
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int valorCinza = new Color(imagemEntrada.getRGB(x, y)).getRed();
                histograma[valorCinza]++;
            }
        }

        // Calcular a função de distribuição cumulativa (CDF)
        int[] cdf = new int[256];
        cdf[0] = histograma[0];
        for (int i = 1; i < 256; i++) {
            cdf[i] = cdf[i - 1] + histograma[i];
        }

        // Normalizar a CDF para o intervalo [0, 255]
        int valorMinimo = Arrays.stream(cdf).filter(valor -> valor > 0).min().orElse(0);
        int[] cdfNormalizada = new int[256];
        for (int i = 0; i < 256; i++) {
            cdfNormalizada[i] = (int) (((cdf[i] - valorMinimo) / (double) (largura * altura - valorMinimo)) * 255);
        }

        // Aplicar a CDF normalizada na imagem original para obter a imagem equalizada
        BufferedImage imagemEqualizada = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int valorCinza = new Color(imagemEntrada.getRGB(x, y)).getRed();
                int novoValorCinza = cdfNormalizada[valorCinza];
                Color novaCor = new Color(novoValorCinza, novoValorCinza, novoValorCinza);
                imagemEqualizada.setRGB(x, y, novaCor.getRGB());
            }
        }

        return imagemEqualizada;
    }
}
