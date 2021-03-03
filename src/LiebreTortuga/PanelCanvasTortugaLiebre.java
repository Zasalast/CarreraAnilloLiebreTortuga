package LiebreTortuga;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelCanvasTortugaLiebre extends JPanel implements  Runnable{
    boolean bandera=false;
    boolean bandera1=false;

    public PanelCanvasTortugaLiebre(String title ) {
        componente(title);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
    }


    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
        repaint();

    }

    public boolean isBandera1() {return bandera1; }

    public void setBandera1(boolean bandera1) {
        this.bandera1 = bandera1;    repaint();
    }

    public  void componente(String title ){
        Border jp_b_Controles;
        jp_b_Controles = BorderFactory.createTitledBorder(title);
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(Color.WHITE);
        this.setBorder(jp_b_Controles);
    }

        @Override
    public void run() {
        Graphics g=null;
        super.paint(g);
            for (int i = 0; i < (int) (Math.random()*20); i++) {
                g.drawOval((int) (Math.random()*280),(int) (Math.random()*280),(int) (Math.random()*20),(int) (Math.random()*20));
paint(g);
            }
 }
    public  void AddComponentes(Component gr){
        this.add(gr);
    }}