package LiebreTortuga;

import javax.swing.*;

public class NodoImagen extends ImageIcon  {
    private int valor;
    String url_imagen2;
    private String name;
    private NodoImagen siguiente;
    private JLabel jlabel;
    private EscalarImagenLabel Esc_imagen_label=new EscalarImagenLabel();
    public NodoImagen(String name) {
        this.setUrl_imagen2(name);
        this.setName(name);
      //  this.setImage(getImage().getScaledInstance(getIconWidth(), getIconHeight(), Image.SCALE_DEFAULT));
   //     this.setHorizontalAlignment((SwingConstants.CENTER));//Se Pinta en el centro del JLabel
     //   this.setPreferredSize(new Dimension(100, 40));
       // this.setText(name);
        Esc_imagen_label=new EscalarImagenLabel();
       // Esc_imagen_label.PonerLaImagen("/images/liebretortuga/"+name+".png",this);
      //  this.setIcon( Esc_imagen_label.PonerLaImagen("/images/liebretortuga/"+name+".png",this));
        this.getClass().getResource("/images/liebretortuga/"+name+".png");
      //  this.getImage().getScaledInstance(getIconWidth(), getIconHeight(), Image.SCALE_DEFAULT);


    }
    public NodoImagen(String url, String name) {
        this.setUrl_imagen2(url);
        this.setName(name);
      //  Esc_imagen_label.PonerLaImagen("/images/liebretortuga/"+name+".png",this);

     //   this.setHorizontalAlignment((SwingConstants.CENTER));//Se Pinta en el centro del JLabel
       // this.setPreferredSize(new Dimension(100, 40));
        //this.setText(name);
        Esc_imagen_label=new EscalarImagenLabel();
       // Esc_imagen_label.PonerLaImagen("/images/liebretortuga/"+name+".png",this);
        //this.setIcon( Esc_imagen_label.PonerLaImagen("/images/liebretortuga/"+name+".png",this));

    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public NodoImagen getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoImagen siguiente) {
        this.siguiente = siguiente;
    }

    public String getUrl_imagen2() {

        return url_imagen2;
    }

    public void setUrl_imagen2(String url_imagen2) {
        this.url_imagen2 = url_imagen2;
    }

    public JLabel getJlabel() {
        return jlabel;
    }

    public void setJlabel(JLabel jlabel) {
        this.jlabel = jlabel;
    }

    //@Override
    public String getName() {
        return name;
    }

    //@Override
    public void setName(String name) {
        this.name = name;
    }
}
