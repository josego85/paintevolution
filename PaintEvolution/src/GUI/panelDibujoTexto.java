/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Auxiliar.Constantes;
import Auxiliar.Texto;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author proyectosbeta
 */
public class panelDibujoTexto extends javax.swing.JPanel implements Serializable, Printable{
    // Objetos.
    private BufferedImage imagen;
    private static String rutaImagenTemporal;
    
    /**
     * Constantes:
     * - Texto: 8
     * - Nulo: 0
     * @since 1.6
     */
    private final static int TEXTO = 8, NULO = 0;
    
    /**
     * Para saber que objeto o texto se va a dibujar.
     * @since 1.6
     */
    private int modoDibujar;
    
    /**
     * True es para habilitar si se va a dibujar texto.
     * @since 1.6
     */
    private boolean habilitarDibujarTexto;
    
    /**
     * La ventana Texto.
     * * @since 1.6
     */
    private VentanaTexto ventanaTexto;  
    
    /**
     * Donde se manipula el objeto texto con sus atributos.
     * @since 1.6
     */
    private Texto texto;
    
    /**
     * Las coordenadas de inicio y fin de "x".
     * @since 1.6
     */
    private int coordenadasInicioX, coordenadasFinX;

    /**
     * Las coordenadas de inicio y fin de "y".
     * @since 1.6
     */
    private int coordenadasInicioY, coordenadasFinY;
    
    /**
     * Guarda las coordenadas iniciales y finales de "x" e "y" que usan el
     * lapiz y pincel.
     * @since 1.6
     */
    private int lineaX1, lineaX2, lineaY1, lineaY2;
    
    /**
     * Lista de Texto a dibujar.
     * @since 1.6
     */
    private LinkedList<Texto> listaTexto = new LinkedList<Texto>();
    
    /**
     * El cursor actual que se esta usando.
     * @since 1.6
     */
    private Cursor cursorActual;
    
    /**
     * Creates new form panelDibujoTexto
     */
    public panelDibujoTexto(String rutaImagenTemporal) {
        /*
         * Se guarda la ruta de la imagen temporal para luego usar,
         * al crear un Texto con registros de la base de datos.
         */
        this.rutaImagenTemporal = rutaImagenTemporal;
        this.habilitarDibujarTexto = false;
        this.ventanaTexto = null;
        texto = null;
        setModoDibujar(NULO);
        
        initComponents();
        
        try{         
            System.out.println("El archivo temporal de la imagen es:  " + rutaImagenTemporal);
            imagen = ImageIO.read(new File(rutaImagenTemporal));
            int height = imagen.getHeight(this);
            int width = imagen.getWidth(this);
            setPreferredSize(new Dimension(width, height));
        }catch(IOException ex){
            // handle exception...
            System.out.println("Problemas imagen");
       }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imagen, 0, 0, null);  
        
        // Dibujar Texto anterior.
        dibujarTexto(g);
        
        if (modoDibujar == getTEXTO() && isHabilitarDibujarTexto()
            && ventanaTexto.isDibujaTexto()){
            texto.setPosicionInicialX(coordenadasInicioX);
            texto.setPosicionInicialY(coordenadasInicioY);
            
            Graphics2D g2 = (Graphics2D)g;
            FontRenderContext contextoFuente = g2.getFontRenderContext();
            Font fuente = new Font(texto.getTipo(), texto.getEstilo(), texto.getTamanio());
            TextLayout layout = new TextLayout( texto.getContenidoTexto(), fuente, contextoFuente );
            
            g2.setColor( texto.getColor());
            layout.draw( g2, getCoordenadasInicioX(), getCoordenadasInicioY());
            setHabilitarDibujarTexto(false);
        }
    }
    
    /**
     * Metodo que muestra la ventana Texto.
     * @since 1.6
     */
    public void mostrarVentanaTexto() {
        if (ventanaTexto == null) {
            ventanaTexto = new VentanaTexto(null, true);
            ventanaTexto.setLocationRelativeTo(this);
        }
        ventanaTexto.setVisible(true);
    }
    
    /**
     * Establece en true para poder dibujar texto; false para no dibujar texto.
     *
     * @param habilitarDibujarTexto En true para poder dibujar texto; false para no dibujar texto
     * @since 1.6
     */
    public void setHabilitarDibujarTexto(boolean habilitarDibujarTexto) {
        this.habilitarDibujarTexto = habilitarDibujarTexto;
    }

    /**
     * Devuelve para saber si se puede dibujar (true) o no (false) un texto.
     *
     * @return True cuando se puede dibujar un texto; caso contrario, false
     * @since 1.6
     */
    public boolean isHabilitarDibujarTexto() {
        return habilitarDibujarTexto;
    }
    
    /**
     * Establece el numero del modo a dibujar.
     *
     * @param modoDibujar El numero del modo a dibujar
     * @since 1.6
     */
    public void setModoDibujar(int modoDibujar) {
        this.modoDibujar = modoDibujar;
    }
    
    /**
     * Devuelve el numero de la constante TEXTO.
     *
     * @return El numero de la constante TEXTO
     * @since 1.6
     */
    public static int getTEXTO() {
        return TEXTO;
    }
    
    /**
     * Establece la coordenada final de x.
     *
     * @param coordenadasFinX La coordenada final de x
     * @since 1.6
     */
    public void setCoordenadasFinX(int coordenadasFinX) {
        if(coordenadasFinX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasFinX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
           this.coordenadasFinX = coordenadasFinX;
        }
    }
    
     /**
     * Establece la coordenada final de y.
     *
     * @param coordenadasFinY La coordenada final de y
     * @since 1.6
     */
    public void setCoordenadasFinY(int coordenadasFinY) {
        if(coordenadasFinY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasFinY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasFinY = coordenadasFinY;
        }
    }
    
    /**
     * Establece la coordenada inicial de x.
     *
     * @param coordenadasInicioX La coordenada inicial de x
     * @since 1.6
     */
    public void setCoordenadasInicioX(int coordenadasInicioX) {
        if(coordenadasInicioX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasInicioX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
            this.coordenadasInicioX = coordenadasInicioX;
        }
    }
    
    /**
     * Establece la coordenada inicial de y.
     *
     * @param coordenadasInicioY La coordenada inicial de y
     * @since 1.6
     */
    public void setCoordenadasInicioY(int coordenadasInicioY) {
        if(coordenadasInicioY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasInicioY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasInicioY = coordenadasInicioY;
        }
    }
    
    /**
     * Devuelve la coordenada inicial de x.
     *
     * @return La coordenada inicial de x
     * @since 1.6
     */
    public int getCoordenadasInicioX() {
        return coordenadasInicioX;
    }
    
    /**
     * Devuelve la coordenada inicial de y.
     *
     * @return La coordenada inicial de y
     * @since 1.6
     */
    public int getCoordenadasInicioY() {
        return coordenadasInicioY;
    }

    /**
     * Establece la coordenada inicial de x para el lapiz y pincel.
     *
     * @param lineaX1 La coordenada inicial de x para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaX1(int lineaX1) {
        this.lineaX1 = lineaX1;
    }

    /**
     * Establece la coordenada final de x para el lapiz y pincel.
     *
     * @param lineaX2 La coordenada final de x para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaX2(int lineaX2) {
        this.lineaX2 = lineaX2;
    }

    /**
     * Establece la coordenada inicial de y para el lapiz y pincel.
     *
     * @param lineaY1 La coordenada inicial de y para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaY1(int lineaY1) {
        this.lineaY1 = lineaY1;
    }

    /**
     * Establece la coordenada final de y para el lapiz y pincel.
     *
     * @param lineaY2 La coordenada final de y para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaY2(int lineaY2) {
        this.lineaY2 = lineaY2;
    }
    
    /**
     * Añada un objeto Texto a la lista de texto a dibujar.
     *
     * @param texto Una nuevo objeto Texto a dibujar
     * @since 1.6
     */
    public void agregarTexto(Texto texto){
        listaTexto.add(texto);
    }
    
    /**
     * Dibuja todos los objetos texto de la lista.
     *
     * @param g Dibuja todos los objetos texto de la lista
     * @since 1.6
     */
    public void dibujarTexto(Graphics g){
        for (Texto texto : listaTexto){
                texto.dibujar(g);
        }
    }
    
    /**
     * Devuelve el cursor actual.
     *
     * @return El cursor actual
     * @since 1.6
     */
    public Cursor getCursorActual() {
        return cursorActual;
    }
    
    /**
     * Establece el cursor actual.
     *
     * @param cursorActual El cursor actual
     * @since 1.6
     */
    public void setCursorActual(Cursor cursorActual) {
        this.cursorActual = cursorActual;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if(modoDibujar == getTEXTO()){
            mostrarVentanaTexto();
            if(ventanaTexto.isDibujaTexto()){
                texto = ventanaTexto.getTexto();
                setHabilitarDibujarTexto(true);
                //desHacerPila.push(texto);
                agregarTexto(texto);
            }else{
                setHabilitarDibujarTexto(false);
            }
        }
        repaint();
    }//GEN-LAST:event_formMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        //archivoGuardadoUltimaVersion = false;
        setCoordenadasFinX(evt.getX());
        setCoordenadasFinY(evt.getY());
        //GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setCursor(getCursorActual());
    }//GEN-LAST:event_formMouseEntered

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        //figuraArrastrandose = null;
        //GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }//GEN-LAST:event_formMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        //GUI_Principal.jLabelCoordenadasPuntero.setText("");
    }//GEN-LAST:event_formMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setCoordenadasInicioX(evt.getX());
        setLineaX1(evt.getX());
        setLineaX2(evt.getX());
        setCoordenadasInicioY(evt.getY());
        setLineaY1(evt.getY());
        setLineaY2(evt.getY());
        //GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        //GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
        //repaint();
    }//GEN-LAST:event_formMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}