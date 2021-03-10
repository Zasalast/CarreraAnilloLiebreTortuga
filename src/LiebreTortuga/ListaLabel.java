package LiebreTortuga;

import javax.swing.*;
//import java.awt.*;
import java.awt.Graphics;

import static java.lang.Thread.sleep;

public  class ListaLabel extends JPanel implements  Runnable {
    private Nodo inicio;
    private Nodo ultimo;
    private int x_liebre,x_tortuga;//una bandera de ubicacion de liebre y turtuga
   private String url_liebre="Liebre",url_tortuga="Tortuga";
    private int tamanio;
    private int velocidad=100;
    boolean bandera_llegada = true;
    ImageIcon fondo;
    private EscalarImagenLabel Esc_imagen_label=new EscalarImagenLabel();
    Thread hilorc= new Thread(this);
    VentanaPrincipalLiebreTortuga vpl;
    public ListaLabel(int alto,int ancho) {

        this.setSize(ancho-200,alto-200);

        fondo = new ImageIcon(getClass().getResource("/images/liebretortuga/"+"T2"+".gif"));

        // addInicio("Liebre","Liebre");
        //  addInicio("Tortuga","Tortuga");

        this.setVisible(true);

    }
    public boolean esVacia(){
        return inicio == null;
    }
    public int getTamanio(){
        return tamanio;
    }
    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getUrl_liebre() {
        return url_liebre;
    }

    public void setUrl_liebre(String url_liebre) {
        this.url_liebre = url_liebre;
    }

    public String getUrl_tortuga() {
        return url_tortuga;
    }

    public void setUrl_tortuga(String url_tortuga) {
        this.url_tortuga = url_tortuga;
    }

    public void agregarAlFinal(String valor, String name){

        Nodo nuevo = new Nodo(name);

        nuevo.setUrl_imagen2(valor);

        if (esVacia()) {
            inicio = nuevo;
            ultimo = nuevo;
            ultimo.setSiguiente(inicio);
        } else{
            ultimo.setSiguiente(nuevo);
            nuevo.setSiguiente(inicio);
            ultimo = nuevo;
        }
        tamanio++;
    }
    public void addInicio(String url,String name) {
        Nodo nuevo = new Nodo(url, name);
        nuevo.setUrl_imagen2(url);
        if (esVacia()) {
            inicio = nuevo;
            ultimo = nuevo;
            ultimo.setSiguiente(inicio);
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
            ultimo.setSiguiente(inicio);
        }
        tamanio++;
    }
    public void addUbicacion(int posicion, String valor,String name){
        if(posicion>=0 && posicion<=tamanio){
            Nodo nuevo = new Nodo(name);
            nuevo.setUrl_imagen2(valor);
            if(posicion == 0){
                nuevo.setSiguiente(inicio);
                inicio = nuevo;
                ultimo.setSiguiente(inicio);
            }
            else{
                if(posicion == tamanio){
                    ultimo.setSiguiente(nuevo);
                    nuevo.setSiguiente(inicio);
                    ultimo = nuevo;
                }
                else{
                    Nodo aux = inicio;
                    for (int i = 0; i < (posicion-1); i++) {
                        aux = aux.getSiguiente();
                    }
                    Nodo siguiente = aux.getSiguiente();
                    aux.setSiguiente(nuevo);
                    nuevo.setSiguiente(siguiente);
                }
            }
            tamanio++;
        }
    }

    public int getValor(int posicion) throws Exception{
        if(posicion>=0 && posicion<tamanio){
            if (posicion == 0) {
                return inicio.getValor();
            }else{
                Nodo aux = inicio;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getSiguiente();
                }
                return aux.getValor();
            }
        } else {
            throw new Exception("Posicion inexistente en la lista.");
        }
    }
    public boolean buscar(int referencia){
        Nodo aux = inicio;
        boolean encontrado = false;
        do{
            if (referencia == aux.getValor()){
                encontrado = true;
            }
            else{
                aux = aux.getSiguiente();
            }
        }while(aux != inicio && encontrado != true);
        return encontrado;
    }
    public int getPosicion(int referencia) throws Exception{
        if (buscar(referencia)) {
            Nodo aux = inicio;
            int cont = 0;
            while(referencia != aux.getValor()){
                cont ++;
                aux = aux.getSiguiente();
            }
            return cont;
        } else {
            throw new Exception("Valor inexistente en la lista.");
        }
    }

    public int getX_liebre() {
        return x_liebre;
    }

    public void setX_liebre(int x_liebre) {
        this.x_liebre = x_liebre;
    }

    public int getX_tortuga() {
        return x_tortuga;
    }

    public void setX_tortuga(int x_tortuga) {
        this.x_tortuga = x_tortuga;
    }

    public void editarPorPosicion(int posicion, String url,String name){
        if(posicion>=0 && posicion<tamanio){
            if(posicion == 0){
                inicio.setUrl_imagen2(url);
                inicio.setName(name);
            }
            else{
                Nodo aux = inicio;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getSiguiente();
                }
                aux.setUrl_imagen2(url);
                aux.setName(name);
            }
        }
    }

    public void listar(){
        if (!esVacia()) {
            Nodo aux = inicio;
            int i = 0;
            do{
                aux = aux.getSiguiente();
                add(aux);
                repaint();
                this.setVisible(true);
                i++;
            }while(aux != inicio);
        }
    }Graphics g;
    public void paint(Graphics g) {
        g.drawImage(fondo.getImage(),0, 0,    this.getWidth(), this.getHeight(), this);
        super.paint(g);
       listar(g);
               g.fillOval(5*getX_liebre(),5*getX_tortuga(),20,20);
    }

    private void listar(Graphics g) {
        if (!esVacia()) {
            Nodo aux = inicio;
            ImageIcon    fondo1=new ImageIcon(getClass().getResource("/images/liebretortuga/"+aux.getUrl_imagen2()+".png"));

            int i = 0;
            do{
                aux = aux.getSiguiente();
            //    fondo=aux
                g.drawImage(fondo.getImage(), aux.getWidth(),aux.getHeight(),100,100, this);
                repaint();
                i++;
            }while(aux != inicio);
        }
    }

    void actualizarNodos(){
         listar();repaint();
        if (getX_tortuga()>=getTamanio()){
            setX_tortuga(0);
            setX_tortuga(getX_tortuga()+1);
            listar(); this.setVisible(true);
            repaint();
        }
         if (getX_liebre()>=getTamanio()){
             setX_liebre(0);
             setX_liebre(getX_liebre()+2);
             listar(); repaint();
             this.setVisible(true);
         }

        setX_tortuga(getX_tortuga()+1);
        setX_liebre(getX_liebre()+2);
        editarPorPosicion(getX_liebre(),url_liebre,"Liebre");
        editarPorPosicion(getX_tortuga(),url_tortuga,"Tortuga");
          listar();repaint();
         System.out.println(" Tortuga: "+getX_tortuga());
         System.out.println(" Liebre: "+getX_liebre());

    }

    @Override
    public  void run() {

        while (isBandera_llegada()) {
            if ( getX_tortuga()== getX_liebre()) {
                try {
                    sleep(6 * getVelocidad());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }  this.repaint();

                actualizarNodos();
                listar(); setVisible(true);
                this.repaint();
                setBandera_llegada(false);
                hilorc.stop();
            }else{
                try {
                    sleep(6 * getVelocidad());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.repaint();

                actualizarNodos();
                listar(); setVisible(true);
                this.repaint();
            }
        }
    }

    public boolean isBandera_llegada() {
        return bandera_llegada;
    }

    public void setBandera_llegada(boolean bandera_llegada) {
        this.bandera_llegada = bandera_llegada;
    }

    public void initialComponents(int inicial_maximo,int posicion_liebre,int posicion_tortuga) {
        setX_liebre(posicion_liebre);
        setX_tortuga(posicion_tortuga);
        for (int i = 0; i <inicial_maximo ; i++) {
            addInicio("Piedra","Piedra");

            listar(); setVisible(true);
            this.repaint();
        }
        addUbicacion(posicion_liebre,url_liebre,"Liebre");
        addUbicacion(posicion_tortuga,url_tortuga,"Tortuga");
        actualizarNodos();
        listar(); setVisible(true);
        this.repaint();
        hilorc.start();
    }
}
