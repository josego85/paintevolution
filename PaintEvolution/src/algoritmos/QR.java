/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;

/**
 * Clase QR.
 * @author proyectosbeta
 */
public class QR {
    // Variables de clase.
    private String nombre;
    private int ancho;
    private int alto;
    
    /**
     * Constructor con tres parametros.
     * @param nombre
     * @param ancho
     * @param alto 
     */
    public QR(String nombre, int ancho, int alto){
       setNombre(nombre);   
       setAncho(ancho);
       setAlto(alto);
    }
   
    /**
     * Metodo que devuelve la imagen QR.
     * @return 
     */
    public BufferedImage devolverImagenQR(){
        // Objetos.
        ByteArrayOutputStream stream1 = QRCode.from(getNombre()).withSize(getAncho(), getAlto()).stream();
        byte[] toByteArray = stream1.toByteArray();
        BufferedImage imagen = null;
        
        try {
            imagen = ImageIO.read( new ByteArrayInputStream(toByteArray ));
        }catch (IOException ex){
             Logger.getLogger(QR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}