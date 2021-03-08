package LiebreTortuga;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class EscalarImagenLabel {
    public Icon PonerLaImagen(String direccion, JLabel label) {
        ImageIcon Icon = new ImageIcon(getClass().getResource(direccion));
        int ancho = 100 / 2;
        int alto = 100 / 2;
        ImageIcon Iconos = new ImageIcon(Icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return Iconos;
    }
}
