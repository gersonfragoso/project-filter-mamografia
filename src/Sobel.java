import java.awt.*;
import java.awt.image.BufferedImage;

public class Sobel {
    public static BufferedImage detectarBordas(BufferedImage imagemEntrada) {
        int largura = imagemEntrada.getWidth();
        int altura = imagemEntrada.getHeight();

        BufferedImage imagemSaida = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);

        // Sobel para detecção de bordas
        for (int y = 1; y < altura - 1; y++) {
            for (int x = 1; x < largura - 1; x++) {
                int gx = 0, gy = 0;
                /*
                −1 0 1
                −2 0 2
                −1 0 1
                 */

                gx += (new Color(imagemEntrada.getRGB(x - 1, y - 1)).getRed() * (-1));
                gx += (new Color(imagemEntrada.getRGB(x, y - 1)).getRed() * 0);
                gx += (new Color(imagemEntrada.getRGB(x + 1, y - 1)).getRed() * 1);
                gx += (new Color(imagemEntrada.getRGB(x - 1, y)).getRed() * (-2));
                gx += (new Color(imagemEntrada.getRGB(x, y)).getRed() * 0);
                gx += (new Color(imagemEntrada.getRGB(x + 1, y)).getRed() * 2);
                gx += (new Color(imagemEntrada.getRGB(x - 1, y + 1)).getRed() * (-1));
                gx += (new Color(imagemEntrada.getRGB(x, y + 1)).getRed() * 0);
                gx += (new Color(imagemEntrada.getRGB(x + 1, y + 1)).getRed() * 1);

                gy += (new Color(imagemEntrada.getRGB(x - 1, y - 1)).getRed() * (-1));
                gy += (new Color(imagemEntrada.getRGB(x, y - 1)).getRed() * (-2));
                gy += (new Color(imagemEntrada.getRGB(x + 1, y - 1)).getRed() * (-1));
                gy += (new Color(imagemEntrada.getRGB(x - 1, y)).getRed() * 0);
                gy += (new Color(imagemEntrada.getRGB(x, y)).getRed() * 0);
                gy += (new Color(imagemEntrada.getRGB(x + 1, y)).getRed() * 0);
                gy += (new Color(imagemEntrada.getRGB(x - 1, y + 1)).getRed() * 1);
                gy += (new Color(imagemEntrada.getRGB(x, y + 1)).getRed() * 2);
                gy += (new Color(imagemEntrada.getRGB(x + 1, y + 1)).getRed() * 1);

                int magnitude = (int) Math.sqrt(gx * gx + gy * gy);
                magnitude = Math.min(255, magnitude);

                Color corBorda = new Color(magnitude, magnitude, magnitude);
                imagemSaida.setRGB(x, y, corBorda.getRGB());
            }
        }

        return imagemSaida;
    }
}
