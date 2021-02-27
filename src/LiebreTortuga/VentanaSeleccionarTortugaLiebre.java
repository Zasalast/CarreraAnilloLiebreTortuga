package LiebreTortuga;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import static java.awt.Color.green;

public class VentanaSeleccionarTortugaLiebre extends JFrame implements ActionListener {
    PanelCanvasTortugaLiebre jp_numeros;
    PanelControlTortugaLiebre jp_Panel_controles;
    PanelContenedorTortugaLiebre jp_principal;
    JButton btn_inicio, btn_salair;
    JTextField capacidad_anillo;
    TimerTask timerTask ;
    public VentanaSeleccionarTortugaLiebre(String title, int ancho, int alto, boolean bloqueo_ventana, boolean Visible_ventana) throws HeadlessException {
        super(title);
        setLayout(new BorderLayout(2, 2));
        setSize(ancho + 10, alto + 40);//ancho , alto
        setBackground(Color.GRAY);///color fondo
        setLocationRelativeTo(null);//centro de pantallla
        setResizable(bloqueo_ventana);//cambiar tamaño de pantalla
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jp_numeros = new PanelCanvasTortugaLiebre("Informafión General");

        jp_Panel_controles = new PanelControlTortugaLiebre("Control");
        components();

        jp_principal = new PanelContenedorTortugaLiebre("Información General");
        PanelBorderLAyout();
        this.setVisible(Visible_ventana);
    }

    void components() {
        btn_inicio = new JButton("Iniciar");
        btn_inicio.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_inicio);



        btn_salair = new JButton("Salir");
        btn_salair.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_salair);
        //  jp_Panel_controles.AddComponentes(Calificacion);
        JTextFieldsComponents();
        btn_salair.setEnabled(false);
    }

    void JTextFieldsComponents() {
        Border bt_horas;
        bt_horas = BorderFactory.createLineBorder(green, 1);
//
        bt_horas = BorderFactory.createTitledBorder("Número Etapas");
        capacidad_anillo = new JTextField("");
        capacidad_anillo.setBorder(bt_horas);
        capacidad_anillo.setEditable(false);

    }

    JLabel Calificacion;
    public void PanelBorderLAyout() {

        //jp_principal.addJComponents(Calificacion);
        jp_principal.addJPanel(jp_Panel_controles.componente(), BorderLayout.PAGE_END);
        jp_principal.addJPanel(jp_numeros, BorderLayout.CENTER);




        jp_numeros.AddComponentes(capacidad_anillo);
        this.add(jp_principal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_inicio) {
            VentanaPrincipalLiebreTortuga v1 = new VentanaPrincipalLiebreTortuga("Carrera Liebre vs Tortuga Seleccionar Etapas", 600, 200, false, true);
this.setVisible(false);
        }

        if (e.getSource() == btn_salair) {
        }
    }}