package LiebreTortuga;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static java.awt.Color.green;

public class VentanaPrincipalLiebreTortuga extends JFrame implements ActionListener,Runnable , WindowListener,
        ChangeListener
{private Thread V_principal;
    private ListaLabel rc;
    private boolean bandera=true;
    private PanelCanvasTortugaLiebre jp_numeros,jp_Informe;
    private PanelControlTortugaLiebre jp_Panel_controles,jp_Panel_controles_btns,jp_Panel_controles_silder;
    private PanelContenedorTortugaLiebre jp_principal;
    private HoraActual fech;
    private CronometroTortugaLiebre cronos;
    private JButton btn_inicio,btn_pausar,btn_reanudar, btn_terminar,btn_salir;
    private JLabel chronos;
   private Thread   cronoshilo,relohilo;

    private JSlider silder1;
    private static final int FPS_MIN = 100;
    private static final int FPS_MAX = 400;
    private static final int FPS_INIT = 100;

    private static  int inicial_tortuga ;
    private static  int inicial_maximo;
    private static  int inicial_liebre;

    private boolean bandera_llegada = true;
    private int velocidad_ejecucion ;


     JTextArea   informe_JTextArea;
    private JScrollPane pane ;

  public VentanaPrincipalLiebreTortuga(String title, int ancho, int alto, boolean bloqueo_ventana, boolean Visible_ventana,int capacidad_anillo,int  posicion_liebre,int posicion_tortuga) throws HeadlessException {
        super(title);
        setLayout(new BorderLayout(1, 1));
        setSize(ancho , alto );//ancho , alto
        setBackground(Color.GRAY);///color fondo
        setLocationRelativeTo(null);//centro de pantallla
        setResizable(bloqueo_ventana);//cambiar tamaño de pantalla
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jp_numeros = new PanelCanvasTortugaLiebre("Interfaz Grafica");
        jp_Informe = new PanelCanvasTortugaLiebre("Informe Ejecución");
        jp_Panel_controles = new PanelControlTortugaLiebre("Control");
        jp_Panel_controles_btns = new PanelControlTortugaLiebre();
        jp_Panel_controles_silder = new PanelControlTortugaLiebre();
           JSilderComponents();
        components();
        JLabelComponents();
        JTextAreaComponents();
      this.inicial_tortuga=posicion_tortuga;
              this.inicial_maximo=capacidad_anillo;
      this.inicial_liebre=posicion_liebre;
      rc=new ListaLabel(jp_numeros.getHeight()-150,jp_numeros.getWidth()-400);
        jp_principal = new PanelContenedorTortugaLiebre();
        PanelBorderLAyout();
        this.setVisible(Visible_ventana);
    }



void terminarCarrera(){

    btn_inicio.setEnabled(false);
    relohilo.stop();
    cronoshilo.stop();
}

    void iniciar(){

        rc.initialComponents(inicial_maximo ,inicial_liebre,inicial_tortuga);
        V_principal=new Thread(this);
        V_principal.start();
        relohilo.start();
        cronoshilo.start();

        btn_inicio.setEnabled(false);
        btn_pausar.setEnabled(true);

        btn_terminar.setEnabled(true);
        reglasActualizacion();
        this.setVisible(true);
    }

    void JSilderComponents() {
        silder1 = new JSlider(JSlider.HORIZONTAL,
                FPS_MIN, FPS_MAX, FPS_INIT);
        silder1.addChangeListener(this);
        silder1.setPaintTicks(true);
        silder1.setMajorTickSpacing(100);
        silder1.setPaintLabels(true);
        silder1.setBorder(BorderFactory.createTitledBorder("Velocidad Tortuga"));
    }
    void JTextAreaComponents() {

        informe_JTextArea = new JTextArea();
        informe_JTextArea.setLineWrap(true);
        informe_JTextArea.setEditable(false);
        informe_JTextArea.setBounds(10, 5, 100, 100);
        pane = new JScrollPane(informe_JTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    void components() {
        Font font = new Font("Serif", Font.ITALIC, 15);
        btn_inicio = new JButton("Iniciar");
        btn_inicio.setFont(font);
        btn_inicio.addActionListener(this);
        jp_Panel_controles_btns.AddComponentes(btn_inicio);

        btn_pausar = new JButton("Pausar");
        btn_pausar.addActionListener(this);
        jp_Panel_controles_btns.AddComponentes(btn_pausar);
        btn_pausar.setEnabled(false);
        btn_pausar.setFont(font);

        btn_reanudar = new JButton("Reanudar");
        btn_reanudar.addActionListener(this);
        jp_Panel_controles_btns.AddComponentes(btn_reanudar);
        btn_reanudar.setEnabled(false);
        btn_reanudar.setFont(font);

        btn_terminar = new JButton("Terminar");
        btn_terminar.addActionListener(this);
        jp_Panel_controles_btns.AddComponentes(btn_terminar);
        btn_terminar.setFont(font);
        btn_terminar.setEnabled(false);

        btn_salir = new JButton("Salir");
        btn_salir.addActionListener(this);
        jp_Panel_controles_btns.AddComponentes(btn_salir);
        btn_salir.setFont(font);
        jp_Panel_controles_silder.AddComponentes(silder1);

        jp_Panel_controles.addJPanel(jp_Panel_controles_silder, BorderLayout.EAST);
        jp_Panel_controles.addJPanel(jp_Panel_controles_btns, BorderLayout.WEST);
    }

void JLabelComponents(){
    Border bt_horas_actual;
    bt_horas_actual = BorderFactory.createLineBorder(green, 1);
    bt_horas_actual = BorderFactory.createTitledBorder(bt_horas_actual,"Hora");

    fech=new HoraActual();
    fech.setBorder(bt_horas_actual);
    relohilo=new Thread(fech);

    Border bt_cronometro;
    bt_cronometro = BorderFactory.createLineBorder(green, 1);
    bt_cronometro = BorderFactory.createTitledBorder(bt_cronometro,"Cronometro");
    cronos=new CronometroTortugaLiebre();
    cronos.setBorder(bt_cronometro);
    cronoshilo=new Thread(cronos);
}


    public void PanelBorderLAyout() {
        chronos = new JLabel("00:00:00");
        jp_principal.addJPanel(jp_Panel_controles.componente(), BorderLayout.PAGE_END);
        jp_principal.addJPanel(jp_numeros, BorderLayout.CENTER);
        jp_principal.addJPanel(jp_Informe, BorderLayout.PAGE_START);
        jp_principal.addJPanel(pane, BorderLayout.EAST);
        jp_Informe.AddComponentes(cronos);
        jp_Informe.AddComponentes(fech);
        jp_principal.add(rc,BorderLayout.CENTER);
        this.add(jp_principal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_inicio) {
            iniciar();

        }else if (e.getSource() == btn_pausar) {
            btn_pausar.setEnabled(false);
            btn_reanudar.setEnabled(true);
            btn_terminar.setEnabled(true);
          //  hilorc.suspend();
            relohilo.checkAccess();
            cronoshilo.checkAccess();
        }else if (e.getSource() == btn_reanudar) {

            btn_pausar.setEnabled(true);
            btn_reanudar.setEnabled(false);
            btn_terminar.setEnabled(true);
       //     hilorc.resume();
            relohilo.checkAccess();
            cronoshilo.checkAccess();
        }else if (e.getSource() == btn_terminar) {
            btn_pausar.setEnabled(false);
            btn_reanudar.setEnabled(false);
            btn_terminar.setEnabled(false);
        terminarCarrera();
        }

        if (e.getSource() == btn_salir) {
            System.exit(1);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == silder1) {
            silder1 = (JSlider)e.getSource();
            int fps = (int)silder1.getValue();
            setVelocidad_ejecucion(fps);
            rc.setVelocidad(fps);
        }
    }

    public int getVelocidad_ejecucion() {
        return velocidad_ejecucion;
    }

    public void setVelocidad_ejecucion(int velocidad_ejecucion) {
        this.velocidad_ejecucion = velocidad_ejecucion;
    }


    public  void reglasActualizacion() {

    }

    @Override
    public void run() {
        while ( rc.bandera_llegada) {
            if ( rc.getX_tortuga()== rc.getX_liebre()) {


                rc.bandera_llegada=rc.isBandera_llegada();
                terminarCarrera();
                informe_JTextArea.setText(informe_JTextArea.getText() + "\n" +
                        "Posiciòn Tortuga" +
                        rc.getX_tortuga() + "\n Posiciòn Liebre" +
                        rc.getX_liebre()
                );
                V_principal.stop();
                cronoshilo.stop();
                relohilo.stop();
            }else{
                informe_JTextArea.setText(informe_JTextArea.getText() + "\n" +
                        "Posiciòn Tortuga" +
                        rc.getX_tortuga() + "\n Posiciòn Liebre" +
                        rc.getX_liebre()
                );
            }

        }
    }
}