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
    private HoraActual fech;
    private CronometroTortugaLiebre cronos;
    JButton btn_inicio,btn_pausar,btn_reanudar, btn_terminar,btn_salir;
    int posicion_conejo = 0;
    int posicion_tortuga = 0;
    int numero_recorrido = 0;
    int nun_recorrido = 2;
    String url_imagen ="images/liebretortuga/";
    TimerTask timerTask ;
    ImageIcon[] images = new ImageIcon[nun_recorrido];
    JLabel picture;
    JLabel Calificacion;
    private Thread   cronoshilo,relohilo;
    int delay;
    Timer timer;
    boolean parar_carrera = false;
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
        JLabelComponents();
        jp_principal = new PanelContenedorTortugaLiebre();
        PanelBorderLAyout();
        this.setVisible(Visible_ventana);
        delay = 1000;
        timer = new Timer(delay, this);
        timer.setInitialDelay(delay * 7); //We pause animation twice per cycle
        //by restarting the timer
        timer.setCoalesce(true);
    }

    protected void actualizarMovimiento(int frameNum) {
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
        jp_Panel_controles.AddComponentes(btn_inicio);

        btn_pausar = new JButton("Pausar");
        btn_pausar.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_pausar);
        btn_pausar.setEnabled(false);
        btn_pausar.setFont(font);
        btn_reanudar = new JButton("Reanudar");
        btn_reanudar.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_reanudar);
        btn_reanudar.setEnabled(false);
        btn_reanudar.setFont(font);
        btn_terminar = new JButton("Terminar");
        btn_terminar.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_terminar);
        btn_terminar.setFont(font);
        btn_terminar.setEnabled(false);

        btn_salir = new JButton("Salir");
        btn_salir.addActionListener(this);
        jp_Panel_controles.AddComponentes(btn_salir);
        btn_salir.setFont(font);



    }
void JLabelComponents(){
    picture = new JLabel();
    picture.setHorizontalAlignment(JLabel.CENTER);
    picture.setAlignmentX(Component.CENTER_ALIGNMENT);
    picture.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(10,10,10,10)));
    actualizarMovimiento(0);


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

        jp_Informe.AddComponentes(cronos);
        jp_Informe.AddComponentes(fech);
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
}