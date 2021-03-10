package LiebreTortuga;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.TimerTask;

import static java.awt.Color.RED;
import static java.awt.Color.green;

public class VentanaSeleccionarTortugaLiebre extends JFrame implements ActionListener {
      PanelControlTortugaLiebre jp_numeros;
     PanelControlTortugaLiebre jp_Panel_controles;
    private static  PanelContenedorTortugaLiebre jp_principal;
      JButton btn_inicio,btn_inicio_definida,btn_inicio_aleatorio, btn_salair;
       int inicial_maximo=0;
       GenerarAleatorio g_aleatorio=new GenerarAleatorio();
    MaskFormatter Formattern1 = null;
    MaskFormatter Formattern2 = null;
    Border bt_capacidad_anillo, bt_posicion_liebre, bt_posicion_tortuga;
    private JFormattedTextField capacidad_anillo,posicion_liebre,posicion_tortuga;
   // private TimerTask timerTask ;
    private   boolean bandera=true;
    private  int inicial_liebre=0,inicial_tortuga=0;
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
        btn_inicio_definida = new JButton("Iniciar Controlada");
        btn_inicio_definida.addActionListener(this);
        btn_inicio_definida.setVisible(false);
        jp_Panel_controles.AddComponentes(btn_inicio_definida);

        btn_inicio = new JButton("Carrera Controlada");
        btn_inicio.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_inicio);

        btn_inicio_aleatorio = new JButton("Carrera Sin Control");
        btn_inicio_aleatorio.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_inicio_aleatorio);

        btn_salair = new JButton("Salir");
        btn_salair.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_salair);
        btn_salair.setEnabled(true);
    }

    void JTextFieldsComponents() throws ParseException {
        Formattern1 = new MaskFormatter("##");
        Formattern1.setPlaceholderCharacter('0');
        Formattern2 = new MaskFormatter("#");
        Formattern2.setPlaceholderCharacter('0');

        bt_capacidad_anillo = BorderFactory.createLineBorder(green, 1);
        bt_capacidad_anillo = BorderFactory.createTitledBorder("Número Etapas");
        capacidad_anillo = new JFormattedTextField(Formattern1);
        capacidad_anillo.setText("16");
        capacidad_anillo.setBorder(bt_capacidad_anillo);
        capacidad_anillo.setVisible(false);
        capacidad_anillo.setPreferredSize(new Dimension((this.getWidth()-15), (this.getHeight()/4)));

        bt_posicion_liebre = BorderFactory.createLineBorder(green, 1);
        bt_posicion_liebre = BorderFactory.createTitledBorder("posición Liebre");
        posicion_liebre = new JFormattedTextField(Formattern2);
        posicion_liebre.setText("6");
        posicion_liebre.setBorder(bt_posicion_liebre);
        posicion_liebre.setVisible(false);
        posicion_liebre.setPreferredSize(new Dimension((this.getWidth()-15), (this.getHeight()/4)));

        bt_posicion_tortuga = BorderFactory.createLineBorder(green, 1);
        bt_posicion_tortuga = BorderFactory.createTitledBorder("posición Tortuga");
        posicion_tortuga = new JFormattedTextField(Formattern2);
        posicion_tortuga.setText("1");
        posicion_tortuga.setBorder(bt_posicion_tortuga);
        posicion_tortuga.setVisible(false);
        posicion_tortuga.setPreferredSize(new Dimension((this.getWidth()-15), (this.getHeight()/4)));
    }


    public void PanelBorderLAyout() {
        jp_principal.addJPanel(jp_Panel_controles.componente(), BorderLayout.PAGE_END);
        this.add(jp_principal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_inicio) {
            try {
                habilitarBotones();

            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        } else if (e.getSource() == btn_inicio_definida) {


            try {
                revisarRango();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }
        else if (e.getSource() == btn_inicio_aleatorio) {
            generarRangoAleatorio(20);

            incioSinControl();
        }else      if (e.getSource() == btn_salair) {
            System.exit(1);
        }
    }

void iniciar_carrera()  {

              VentanaPrincipalLiebreTortuga v1 = new VentanaPrincipalLiebreTortuga("Carrera Liebre vs Tortuga Seleccionar Etapas", 900, 650, false, true,Integer.parseInt(capacidad_anillo.getText()),Integer.parseInt(posicion_liebre.getText()),Integer.parseInt(posicion_tortuga.getText()));
                this.setVisible(false);

}

void incioSinControl(){
    VentanaPrincipalLiebreTortuga v1 = new VentanaPrincipalLiebreTortuga("Carrera Liebre vs Tortuga Seleccionar Etapas", 900, 650, false, true,inicial_maximo,inicial_liebre,inicial_tortuga);
    this.setVisible(false);
}
void habilitarBotones() throws ParseException {
    this.setSize(600,400);
    jp_numeros = new PanelControlTortugaLiebre("Informafión General");

    JTextFieldsComponents();
    posicion_tortuga.setVisible(true);
    posicion_liebre.setVisible(true);
    capacidad_anillo.setVisible(true);
    btn_inicio_definida.setVisible(true);
    btn_inicio_aleatorio.setVisible(false);
    btn_inicio.setVisible(false);
    jp_numeros.AddComponentes(capacidad_anillo);
    jp_numeros.AddComponentes(posicion_liebre);
    jp_numeros.AddComponentes(posicion_tortuga);
    btn_inicio_definida.setText("Iniciar Carrera");

    jp_principal.addJPanel(jp_numeros, BorderLayout.CENTER);


    // jp_numeros.setSize(this.getWidth(),(this.getHeight()-80));
    this.setVisible(true);
}
private  void generarRangoAleatorio(int x){
    inicial_maximo= g_aleatorio.GenerarEntero(x);
    inicial_tortuga= g_aleatorio.GenerarEntero(x/2);
    inicial_liebre= g_aleatorio.GenerarEntero(x);
    while((inicial_maximo >=  inicial_liebre) ){
                while( (inicial_liebre >  inicial_tortuga)){
                    if (inicial_liebre <=  inicial_tortuga) {
                        inicial_tortuga = g_aleatorio.GenerarEntero(4);
                        inicial_liebre= g_aleatorio.GenerarEntero(x);
                    }
                }if((inicial_liebre >  inicial_tortuga)){
            inicial_maximo= g_aleatorio.GenerarEntero(x);
        }

            }
}
      void revisarRango() throws ParseException {
        if(Integer.parseInt(capacidad_anillo.getText())<=20){
            bt_capacidad_anillo = BorderFactory.createLineBorder(Color.GREEN, 1);
            if(Integer.parseInt(posicion_liebre .getText())<=Integer.parseInt(capacidad_anillo.getText())) {
                bt_posicion_liebre = BorderFactory.createLineBorder(Color.GREEN, 1);

                if(Integer.parseInt(posicion_tortuga .getText())<Integer.parseInt(posicion_liebre.getText())) {
                    bt_posicion_tortuga = BorderFactory.createLineBorder(Color.GREEN, 1);
                    iniciar_carrera();
                }else{
                    bt_posicion_tortuga = BorderFactory.createLineBorder(Color.RED, 1);
                }
            }else{
                bt_posicion_liebre = BorderFactory.createLineBorder(Color.RED, 1);
            }
        }else{
          bt_capacidad_anillo = BorderFactory.createLineBorder(Color.RED, 1);}
    }
}