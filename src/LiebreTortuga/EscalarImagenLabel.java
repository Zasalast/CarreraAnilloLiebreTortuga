package LiebreTortuga;
import java.awt.Image;
import javax.swing.*;

public class EscalarImagenLabel {
    public Icon PonerLaImagen(String direccion, JLabel label) {
        ImageIcon Icon = new ImageIcon(getClass().getResource(direccion));
        int ancho = 100 / 2;
        int alto = 100 / 2;
        ImageIcon Iconos = new ImageIcon(Icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return Iconos;
    }
    public Icon PonerLaImagen1(String direccion, JPanel panel) {
        ImageIcon Icon = new ImageIcon(getClass().getResource(direccion));
        int ancho = panel.getWidth() / 2;
        int alto = panel.getWidth()  / 2;
        ImageIcon Iconos = new ImageIcon(Icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return Iconos;
    }
}
