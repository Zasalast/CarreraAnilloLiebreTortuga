package LiebreTortuga;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import static java.awt.Color.green;

public class VentanaPrincipalLiebreTortuga extends JFrame implements ActionListener {
    PanelCanvasTortugaLiebre jp_numeros,jp_Informe;
    PanelControlTortugaLiebre jp_Panel_controles;
    PanelContenedorTortugaLiebre jp_principal;
    JButton btn_inicio, Terminar;

    TimerTask timerTask ;
    public VentanaPrincipalLiebreTortuga(String title, int ancho, int alto, boolean bloqueo_ventana, boolean Visible_ventana) throws HeadlessException {
        super(title);
        setLayout(new BorderLayout(2, 2));
        setSize(ancho + 10, alto + 40);//ancho , alto
        setBackground(Color.GRAY);///color fondo
        setLocationRelativeTo(null);//centro de pantallla
        setResizable(bloqueo_ventana);//cambiar tamaño de pantalla
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jp_numeros = new PanelCanvasTortugaLiebre("Interfaz Grafica");
        jp_Informe = new PanelCanvasTortugaLiebre("Informe Ejecución");
        jp_Panel_controles = new PanelControlTortugaLiebre("Control");
        components();

        jp_principal = new PanelContenedorTortugaLiebre();
        PanelBorderLAyout();
        this.setVisible(Visible_ventana);
    }

    void components() {
        btn_inicio = new JButton("Iniciar");
        btn_inicio.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_inicio);



        Terminar = new JButton("Terminar");
        Terminar.addActionListener(this);
        jp_Panel_controles.AddComponentes(Terminar);

        Terminar.setEnabled(false);
    }



    JLabel Calificacion;
    public void PanelBorderLAyout() {
        Calificacion = new JLabel("00:00:00");
        //jp_principal.addJComponents(Calificacion);
        jp_principal.addJPanel(jp_Panel_controles.componente(), BorderLayout.PAGE_END);
        jp_principal.addJPanel(jp_numeros, BorderLayout.CENTER);
        jp_principal.addJPanel(jp_Informe, BorderLayout.PAGE_START);

         jp_Informe.AddComponentes(Calificacion);

        //jp_numeros.AddComponentes(capacidad_anillo);
        this.add(jp_principal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_inicio) {

        }

        if (e.getSource() == Terminar) {
        }
    }
}