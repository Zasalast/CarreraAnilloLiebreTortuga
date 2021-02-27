package LiebreTortuga;

import javax.swing.*;
import java.awt.*;

public class PanelContenedorTortugaLiebre extends JPanel{
    String nombre_panel="";

    public PanelContenedorTortugaLiebre(String nombre_panel) {
        this.nombre_panel = nombre_panel;
        this.setLayout(new BorderLayout(2, 2));
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }


    public PanelContenedorTortugaLiebre() {
        this.setLayout(new BorderLayout(2, 2));
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }

    public void addJPanel(JPanel panel, String borderLayouts){
        this.add(panel,borderLayouts);
    }

    public void addJComponents(Component components){
        this.add(components);
    }
}