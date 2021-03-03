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
import java.util.TimerTask;
import javax.swing.ImageIcon;
import static java.awt.Color.green;

public class VentanaPrincipalLiebreTortuga extends JFrame implements ActionListener , WindowListener,
        ChangeListener {
    PanelCanvasTortugaLiebre jp_numeros,jp_Informe;
    PanelControlTortugaLiebre jp_Panel_controles,jp_Panel_controles_btns,jp_Panel_controles_silder;
    PanelContenedorTortugaLiebre jp_principal;
    private HoraActual fech;
    private CronometroTortugaLiebre cronos;
    JButton btn_inicio,btn_pausar,btn_reanudar, btn_terminar,btn_salir;
    int posicion_conejo = 0;
    int posicion_tortuga = 0;
    int numero_recorrido = 0;
    int nun_recorrido = 2;
    String url_imagen ="/images/liebretortuga/";
    TimerTask timerTask ;
    ImageIcon[] images = new ImageIcon[nun_recorrido];
    JLabel picture;
    JLabel Calificacion;
    private Thread   cronoshilo,relohilo;
    int delay;
    Timer timer;
    boolean parar_carrera = false;
    JSlider silder1, silder2;

    //Set up animation parameters.
    static final int FPS_MIN = 100;
    static final int FPS_MAX = 400;
    static final int FPS_INIT = 100;

    boolean bandera_llegada = true;
    int velocidad_ejecucion,velocidad_ejecucion1;


    private JTextArea informe_carrera_JTextArea, informe_JTextArea;
    private JScrollPane pane, pane_estudiante;

    public VentanaPrincipalLiebreTortuga(String title, int ancho, int alto, boolean bloqueo_ventana, boolean Visible_ventana,int capacidad_anillo,int  posicion_liebre,int posicion_tortuga) throws HeadlessException {
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
        jp_Panel_controles_btns = new PanelControlTortugaLiebre();
        jp_Panel_controles_silder = new PanelControlTortugaLiebre();
        JSilderComponents();
        components();
        JLabelComponents();
        JTextAreaComponents();

        jp_principal = new PanelContenedorTortugaLiebre();
        PanelBorderLAyout();
        this.setVisible(Visible_ventana);
        delay = 1000;
        timer = new Timer(delay, this);
        timer.setInitialDelay(delay * 7); //We pause animation twice per cycle
        //by restarting the timer
        timer.setCoalesce(true);
    }

    protected void actualizarMovimiento(int posicion_tortuga) {
        //Get the image if we haven't already.
        if (images[posicion_tortuga] == null) {
            images[posicion_tortuga] = createImageIcon(url_imagen+"/T"
                    + posicion_tortuga
                    + ".gif");
        }

        //Set the image.
        if (images[posicion_tortuga] != null) {
            picture.setIcon(images[posicion_tortuga]);
        } else { //image not found
            picture.setText("image #" + posicion_tortuga + " not found");
        }
    }

void terminarCarrera(){
    timer.stop();
    parar_carrera= true;
    relohilo.stop();
    cronoshilo.stop();
}

    void iniciar(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                timer.start();
                parar_carrera = false;

            }
        });
        relohilo.start();
        cronoshilo.start();
        if (posicion_tortuga == (nun_recorrido - 1)) {
            posicion_tortuga = 0;
        } else {
            posicion_tortuga++;
        }

        actualizarMovimiento(posicion_tortuga); //display the next picture

        if ( posicion_tortuga==(nun_recorrido - 1)
                || posicion_tortuga==(nun_recorrido/2 - 1) ) {
            // timer.restart();
        }
    }

    void JSilderComponents() {
        silder1 = new JSlider(JSlider.HORIZONTAL,
                FPS_MIN, FPS_MAX, FPS_INIT);
        silder1.addChangeListener(this);
        silder1.setPaintTicks(true);
        silder1.setMajorTickSpacing(100);
        silder1.setPaintLabels(true);
        silder1.setBorder(BorderFactory.createTitledBorder("Velocidad Tortuga"));
        silder2 = new JSlider(JSlider.HORIZONTAL,
                FPS_MIN, FPS_MAX, FPS_INIT);
        silder2.addChangeListener(this);

        silder2.setPaintTicks(true);
        silder2.setMajorTickSpacing(100);
        silder2.setPaintLabels(true);
        silder2.setBorder(BorderFactory.createTitledBorder("Velocidad Liebre"));
    }
    void JTextAreaComponents() {

        informe_carrera_JTextArea = new JTextArea();

        informe_carrera_JTextArea.setLineWrap(true);

        informe_carrera_JTextArea.setEditable(false);
        informe_carrera_JTextArea.setBounds(10, 5, 100, 100);
        pane_estudiante = new JScrollPane(informe_JTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        informe_JTextArea = new JTextArea();

        informe_JTextArea.setLineWrap(true);

        informe_JTextArea.setEditable(false);
        informe_JTextArea.setBounds(10, 5, 100, 100);
        pane = new JScrollPane(informe_JTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = VentanaPrincipalLiebreTortuga.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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
        jp_Panel_controles_silder.AddComponentes(silder2);
        jp_Panel_controles.addJPanel(jp_Panel_controles_silder, BorderLayout.EAST);
        jp_Panel_controles.addJPanel(jp_Panel_controles_btns, BorderLayout.WEST);



    }
    ImageIcon imagen_piedras,imagen_tortuga,imagen_liebre;;

void JLabelComponents(){
        ImageIcon IMA1 = new ImageIcon(getClass().getResource(url_imagen+"T0.png"));
    picture = new JLabel(IMA1);
    picture.setHorizontalAlignment(JLabel.CENTER);
    picture.setAlignmentX(Component.CENTER_ALIGNMENT);
    picture.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(10,10,10,10)));
    actualizarMovimiento(0);

    //ImageIcon IMA1 = new ImageIcon(getClass().getResource("/" + FIGURAS2 + "/" + coger1));


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
        Calificacion = new JLabel("00:00:00");
        //jp_principal.addJComponents(Calificacion);
        jp_principal.addJPanel(jp_Panel_controles.componente(), BorderLayout.PAGE_END);
        jp_principal.addJPanel(jp_numeros, BorderLayout.CENTER);
        jp_principal.addJPanel(jp_Informe, BorderLayout.PAGE_START);
        jp_numeros.AddComponentes(picture);
        jp_numeros.AddComponentes(pane);
        jp_Informe.AddComponentes(cronos);
        jp_Informe.AddComponentes(fech);
       // jp_Informe.AddComponentes(pane);

        //jp_numeros.AddComponentes(capacidad_anillo);
        this.add(jp_principal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_inicio) {
            iniciar();

        }else if (e.getSource() == btn_pausar) {

        }else if (e.getSource() == btn_reanudar) {

        }else if (e.getSource() == btn_terminar) {

        }

        if (e.getSource() == btn_salir) {
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
            // if (!silder1.getValueIsAdjusting()) {
            int fps = (int)silder1.getValue();
            setVelocidad_ejecucion(fps);

            // }
        }

        if (e.getSource() == silder2) {
            silder2 = (JSlider)e.getSource();

            int fps = (int)silder2.getValue();
            setVelocidad_ejecucion1(fps);


        }

    }

    public int getVelocidad_ejecucion() {
        return velocidad_ejecucion;
    }

    public void setVelocidad_ejecucion(int velocidad_ejecucion) {
        this.velocidad_ejecucion = velocidad_ejecucion;
    }

    public int getVelocidad_ejecucion1() {
        return velocidad_ejecucion1;
    }

    public void setVelocidad_ejecucion1(int velocidad_ejecucion1) {
        this.velocidad_ejecucion1 = velocidad_ejecucion1;
    }
}