import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CarregarImagem {
    public static BufferedImage carregarImagem(String caminho) {
        try {
            return ImageIO.read(new File(caminho));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
