package LiebreTortuga;

import javax.swing.*;

public class ListaLabel extends JPanel implements  Runnable {
    private Nodo inicio;
    private Nodo ultimo;
    private int tamanio;

    public ListaLabel(int x,int alto,int ancho,int posicion_liebre,int posicion_tortuga) {
        this.setSize(ancho,alto);

        for (int i = 0; i <x ; i++) {
            addInicio("Piedra","Piedra");
        }
        // addInicio("Liebre","Liebre");
        //  addInicio("Tortuga","Tortuga");
        addUbicacion(posicion_liebre,"Liebre","Liebre");
        addUbicacion(posicion_tortuga,"Tortuga","Tortuga");
    }
    public boolean esVacia(){
        return inicio == null;
    }
    public int getTamanio(){
        return tamanio;
    }

    public void agregarAlFinal(String valor,String name){

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

    public void editarPorPosicion(int posicion , String url){
        if(posicion>=0 && posicion<tamanio){
            if(posicion == 0){
                inicio.setUrl_imagen2(url);
            }
            else{
                Nodo aux = inicio;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getSiguiente();
                }
                aux.setUrl_imagen2(url);
            }
        }
    }

    public void listar(){
        if (!esVacia()) {
            Nodo aux = inicio;
            int i = 0;
            do{
                aux = aux.getSiguiente();
                this.add(aux);
                i++;
            }while(aux != inicio);
        }
    }
    @Override
    public void run() {

    }
}
