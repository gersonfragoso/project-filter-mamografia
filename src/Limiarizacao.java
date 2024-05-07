import java.awt.*;
import java.awt.image.BufferedImage;

public class Limiarizacao {
    public static BufferedImage aplicarLimiarizacao(BufferedImage imagemEntrada, int limiar) {
        int largura = imagemEntrada.getWidth();
        int altura = imagemEntrada.getHeight();

        BufferedImage imagemSaida = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                Color cor = new Color(imagemEntrada.getRGB(x, y));
                int valorCinza = (cor.getRed() + cor.getGreen() + cor.getBlue()) / 3;

                if (valorCinza >= limiar) {
                    imagemSaida.setRGB(x, y, Color.WHITE.getRGB());
                } else {
                    imagemSaida.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }

        return imagemSaida;
    }
}
