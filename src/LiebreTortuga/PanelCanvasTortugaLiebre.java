package LiebreTortuga;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelCanvasTortugaLiebre extends JPanel implements  Runnable{
    boolean bandera=false;
    boolean bandera1=false;


    public PanelCanvasTortugaLiebre(String title ) {
        componente(title);
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
//        g.setColor(Color.BLACK);
//        g.drawOval(150, 50, 80, 80);      //Ojo izquierdo
//        g.fillOval(190, 70, 25, 25);  //pupila ojo izquierdo
//        g.drawOval(250, 50, 80, 80);   //Ojo derecho
//        g.fillOval(280, 60, 50, 50);     //Pupila ojo derecho
//        // g.drawString("CARITA", 600, 350);
//
//        g.drawOval(100, 20, 300, 300);
//        for (int i = 0; i < 10; i++) {
//            g.fillOval(200, 170, 100, 120*(i*5));//Boca
            paint(g);
//
//        }
//
    }
    public  void AddComponentes(Component gr){
        this.add(gr);
    }}