import java.awt.*;
import java.awt.image.BufferedImage;

public class ConverterCinza {
    public static BufferedImage converterParaEscalaCinza(BufferedImage imagemEntrada) {
        int largura = imagemEntrada.getWidth();
        int altura = imagemEntrada.getHeight();
        BufferedImage imagemCinza = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                Color cor = new Color(imagemEntrada.getRGB(x, y));
                int valorCinza = (int) (0.299 * cor.getRed() + 0.587 * cor.getGreen() + 0.114 * cor.getBlue());
                imagemCinza.setRGB(x, y, new Color(valorCinza, valorCinza, valorCinza).getRGB());
            }
        }

        return imagemCinza;
    }
}
