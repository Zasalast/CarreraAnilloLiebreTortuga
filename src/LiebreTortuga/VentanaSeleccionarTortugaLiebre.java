package LiebreTortuga;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import static java.awt.Color.green;

public class VentanaSeleccionarTortugaLiebre extends JFrame implements ActionListener {
    PanelControlTortugaLiebre jp_numeros;
    PanelControlTortugaLiebre jp_Panel_controles;
    PanelContenedorTortugaLiebre jp_principal;
    JButton btn_inicio,btn_inicio_aleatorio, btn_salair;
    JTextField capacidad_anillo,posicion_liebre,posicion_tortuga;
    TimerTask timerTask ;
    private boolean bandera=true;

    public VentanaSeleccionarTortugaLiebre(String title, int ancho, int alto, boolean bloqueo_ventana, boolean Visible_ventana) throws HeadlessException {
        super(title);
        setLayout(new BorderLayout(1, 1));
        setSize(ancho, alto);//ancho , alto
        setBackground(Color.GRAY);///color fondo
        setLocationRelativeTo(null);//centro de pantallla
        setResizable(bloqueo_ventana);//cambiar tamaño de pantalla
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        jp_Panel_controles = new PanelControlTortugaLiebre("Control");
        components();

        jp_principal = new PanelContenedorTortugaLiebre("Información General");
        PanelBorderLAyout();
        this.setVisible(Visible_ventana);
    }

    void components() {
        btn_inicio = new JButton("Carrera Controlada");
        btn_inicio.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_inicio);

        btn_inicio_aleatorio = new JButton("Carrera Sin Control");
        btn_inicio_aleatorio.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_inicio_aleatorio);

        btn_salair = new JButton("Salir");
        btn_salair.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_salair);
        //  jp_Panel_controles.AddComponentes(Calificacion);

        btn_salair.setEnabled(true);
    }

    void JTextFieldsComponents() {
            Border bt_capacidad_anillo;
        bt_capacidad_anillo = BorderFactory.createLineBorder(green, 1);
//
        bt_capacidad_anillo = BorderFactory.createTitledBorder("Número Etapas");
        capacidad_anillo = new JTextField("10");
        capacidad_anillo.setBorder(bt_capacidad_anillo);
        capacidad_anillo.setVisible(false);
        capacidad_anillo.setPreferredSize(new Dimension((this.getWidth()-15), (this.getHeight()/4)));


        Border bt_posicion_liebre;
        bt_posicion_liebre = BorderFactory.createLineBorder(green, 1);
//
        bt_posicion_liebre = BorderFactory.createTitledBorder("posición Liebre");
        posicion_liebre = new JTextField("1");
        posicion_liebre.setBorder(bt_posicion_liebre);
        posicion_liebre.setVisible(false);
        posicion_liebre.setPreferredSize(new Dimension((this.getWidth()-15), (this.getHeight()/4)));


        Border bt_posicion_tortuga;
        bt_posicion_tortuga = BorderFactory.createLineBorder(green, 1);
//
        bt_posicion_tortuga = BorderFactory.createTitledBorder("posición Tortuga");
        posicion_tortuga = new JTextField("2");
        posicion_tortuga.setBorder(bt_posicion_tortuga);
        posicion_tortuga.setVisible(false);
        posicion_tortuga.setPreferredSize(new Dimension((this.getWidth()-15), (this.getHeight()/4)));
    }

    JLabel Calificacion;
    public void PanelBorderLAyout() {

        //jp_principal.addJComponents(Calificacion);
        jp_principal.addJPanel(jp_Panel_controles.componente(), BorderLayout.PAGE_END);

        this.add(jp_principal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_inicio) {
            iniciar_carrera();
        } else if (e.getSource() == btn_inicio_aleatorio) {
            incioSinControl();
        } else      if (e.getSource() == btn_salair) {
            System.exit(1);
        }
    }

void iniciar_carrera(){
        if (bandera){
            this.setSize(600,400);
            jp_numeros = new PanelControlTortugaLiebre("Informafión General");

            JTextFieldsComponents();
            posicion_tortuga.setVisible(true);
            posicion_liebre.setVisible(true);
            capacidad_anillo.setVisible(true);
            btn_inicio_aleatorio.setVisible(false);
            jp_numeros.AddComponentes(capacidad_anillo);
            jp_numeros.AddComponentes(posicion_liebre);
            jp_numeros.AddComponentes(posicion_tortuga);
            btn_inicio.setText("Iniciar Carrera");

            jp_principal.addJPanel(jp_numeros, BorderLayout.CENTER);
            bandera=false;

           // jp_numeros.setSize(this.getWidth(),(this.getHeight()-80));
            this.setVisible(true);
        }else {
              VentanaPrincipalLiebreTortuga v1 = new VentanaPrincipalLiebreTortuga("Carrera Liebre vs Tortuga Seleccionar Etapas", 900, 650, false, true,Integer.parseInt(capacidad_anillo.getText()),Integer.parseInt(posicion_liebre.getText()),Integer.parseInt(posicion_tortuga.getText()));
                this.setVisible(false);
        }
}

void incioSinControl(){
    VentanaPrincipalLiebreTortuga v1 = new VentanaPrincipalLiebreTortuga("Carrera Liebre vs Tortuga Seleccionar Etapas", 900, 650, false, true,(((int) (Math.random()*20))),((int) (Math.random()*5)),((int) (Math.random()*5)));
    this.setVisible(false);
}

}