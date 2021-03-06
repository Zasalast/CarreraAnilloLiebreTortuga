package LiebreTortuga;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelControlTortugaLiebre extends JPanel {
    String nombre_panel="";

    public PanelControlTortugaLiebre(String nombre_panel) {
        this.nombre_panel = nombre_panel;
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 1, 1));
    }

    public PanelControlTortugaLiebre() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 1, 1));
    }

    public  JPanel componente(){
        Border jp_b_Controles;
        jp_b_Controles = BorderFactory.createTitledBorder(nombre_panel);
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 1, 1));
        this.setBackground(Color.GRAY);
        this.setBorder(jp_b_Controles);
        return  this;
    }
    public  void AddComponentes(Component gr){
        this.add(gr);
    }
    public void addJPanel(JPanel panel, String borderLayouts){
        this.add(panel,borderLayouts);
    }
}