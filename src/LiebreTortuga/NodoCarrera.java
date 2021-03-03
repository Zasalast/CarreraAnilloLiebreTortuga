package LiebreTortuga;

public class NodoCarrera {

    private int valor;

    private NodoCarrera siguiente;
  public void NodoCarrera(){
        this.valor = 0;
        this.siguiente = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public NodoCarrera getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCarrera siguiente) {
        this.siguiente = siguiente;
    }
}
