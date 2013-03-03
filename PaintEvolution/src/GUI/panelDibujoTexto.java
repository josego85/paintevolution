/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Auxiliar.Constantes;
import Auxiliar.FiltroArchivo;
import Auxiliar.Texto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import util.ImprimirImagenes;

/**
 *
 * @author proyectosbeta
 */
public class panelDibujoTexto extends javax.swing.JPanel implements Serializable{
    // Objetos.
    private BufferedImage imagenInsertada;
    private BufferedImage imagenPrincipal;
    private BufferedImage imagenTemporalImprimir;
    private static String rutaImagenTemporal;
    private ArrayList<String> listaImagenesTemporalesImprimir;
    private static ArrayList<ArrayList> arrayFilasSeleccionadas;
    private static String[] nombreColumnas;
    private static ArrayList<String> arrayPosicionesTexto;
    
    /*
     * Constante del ancho de una imagen con 50 pixeles.
     */
    private final static int ANCHO_IMAGEN_REDIMENCIONADA = 50; 
    
    /*
     * Constante del ancho de una imagen con 50 pixeles.
     */
    private final static int ALTO_IMAGEN_REDIMENCIONADA = 50; 
    
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
     * Donde se manipula el objeto texto con sus atributos.
     * @since 1.6
     */
    private Texto texto;
    
    /**
     * Lista de Texto a dibujar.
     * @since 1.6
     */
    private LinkedList<Texto> listaTexto = new LinkedList<Texto>();
    
    /**
     * El nombre del archivo que se usa para guardar la imagen.
     * @since 1.6
     */
    private File nombreArchivo;
    
    private int coordenadaX;             // x coord - set from drag.
    private int coordenadaY;             // y coord - set from drag.
    private int dragFromX;         
    private int dragFromY;         
    private boolean isMouseDrag;
    
    
    /**
     * Creates new form panelDibujoTexto
     */
    public panelDibujoTexto(String rutaImagenTemporal, ArrayList<ArrayList> arrayFilasSeleccionadas,
            String[] nombreColumnas, ArrayList<String> arrayPosicionesTexto) {
        /*
         * Se guarda la ruta de la imagen temporal para luego usar,
         * al crear un Texto con registros de la base de datos.
         */
        this.rutaImagenTemporal = rutaImagenTemporal;
        this.habilitarDibujarTexto = false;
        texto = null;
        setModoDibujar(NULO);
        
        /**
         * Se guardan las filas seleccionadas con sus campos correspondientes.
         */
        this.arrayFilasSeleccionadas = arrayFilasSeleccionadas;
        
        /**
         * Se guarda el array nombreColumnas.
         */
        this.nombreColumnas = nombreColumnas;
        
        /**
         * Se guarda en un array las posiciones de los textos.
         */
        this.arrayPosicionesTexto = arrayPosicionesTexto;
        
        initComponents();
        
        /*
         * Imagen insertada es null porque el usuario deberia de elegir manualmente
         * mediante el boton InsertarImagen.
         */ 
        imagenInsertada = null;
        
        try{         
            //System.out.println("El archivo temporal de la imagen es:  " + rutaImagenTemporal);
            imagenPrincipal = ImageIO.read(new File(rutaImagenTemporal));
            int height = imagenPrincipal.getHeight(this);
            int width = imagenPrincipal.getWidth(this);
            setPreferredSize(new Dimension(width, height));
        }catch(IOException ex){
            // Manejador de excepcines.
            System.out.println("Problemas imagen");
       }
       coordenadaX = 50;   // x coord - set from drag
       coordenadaY = 50;   // y coord - set from drag
       dragFromX = 0;    // pressed this far inside ball's
       dragFromY = 0;    // bounding box.
       isMouseDrag = false;   
       
       // Se crea el array que contendrà las imagenes temporales a imprimir.
       listaImagenesTemporalesImprimir = new ArrayList<String>();
       
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imagenPrincipal, 0, 0, null);  
         
        
        crearTextoPrototipo();
        
        dibujarTexto(g);
        
        if (getImagenInsertada() != null){
            g.drawImage(getImagenInsertada(), coordenadaX, coordenadaY, 
                ANCHO_IMAGEN_REDIMENCIONADA, ALTO_IMAGEN_REDIMENCIONADA, null); 
        }
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
     * Abre la imagen con formato png.
     *
     * @since 1.6
     */
    public void abrirImagen(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FiltroArchivo());

        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.CANCEL_OPTION){
            return;
        }  

        nombreArchivo = fileChooser.getSelectedFile();

        if(nombreArchivo != null){
            try{
                //borrarTodo();
                BufferedImage image = ImageIO.read(nombreArchivo);
                imagenInsertada = ImageIO.read(nombreArchivo);
                setImagenInsertada(nombreArchivo);

                Graphics g = image.getGraphics();
                g.drawImage(image, 0, 0, this);
            }catch(Exception exp){
                 JOptionPane.showMessageDialog(null,"No se puede abrir el archivo",
                     "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                     Constantes.TITULO_PROGRAMA, JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            nombreArchivo = null;
	}
        repaint();
    }
    
    /**
     * Establece la imagen actual.
     *
     * @param imagen La imagen actual
     * @since 1.6
     */
    public void setImagenInsertada(Image imagenInsertada){
        this.imagenInsertada = (BufferedImage)imagenInsertada;          // Imagen insertada.
        repaint();                                              // Se dibuja la imagen insertada.
    }
    
    /**
     * Asigna la imagen actual mediante un archivo.
     *
     * @param file El archivo de entrada para guardar la imagen.
     * @throws IOException Error del archivo de entrada para guardar la imagen.
     * @since 1.6
     */
    public void setImagenInsertada(File file) throws IOException{
        setImagenInsertada(ImageIO.read(file));
        repaint();                                              // Se dibuja la nueva imagen
    }

    /**
     * Devuelve la imagen actual.
     *
     * @return La imagen actual
     * @since 1.6
     */
    public BufferedImage getImagenInsertada(){
        return imagenInsertada;
    }
    
    /**
     * Metodo publico que crea una imagen temporal de la imagen creada
     * en el panelDibujo.
     * @return 
     */
    private String crearImagenTemporal(){
        // Creamos una imagen temporal.
        File imagenTemporal = null; 
        
        try {
            imagenTemporal = File.createTempFile("ImagenTemporalImprimir.png", null);
           
            /*
            System.out.println("La ruta de la imagen temporal imprimir es: " + 
                imagenTemporal.getAbsolutePath());
            */
            
            // Se crea la ultima instancia de lo dibujado en el panelDibujoTexto.
            crearImagen();
            
            ImageIO.write(imagenTemporalImprimir, "png", imagenTemporal);
            
            listaImagenesTemporalesImprimir.add(imagenTemporal.getAbsolutePath());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return imagenTemporal.getAbsolutePath();
    }
    
    /**
     * Metodo publico que crea la imagen temporal para imprimir.
     * @since 1.6
     */
    private void crearImagen() {
        imagenTemporalImprimir = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = imagenTemporalImprimir.createGraphics();
        
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Dibuja la imagenPrincipal.
        g2.drawImage(imagenPrincipal, 0, 0, null);  

        // Dibuja tods los textos.
        dibujarTexto(g2);

        if (getImagenInsertada() != null){
            // Dibuja la imagenInsertada.
            g2.drawImage(getImagenInsertada(), coordenadaX, coordenadaY, 
                ANCHO_IMAGEN_REDIMENCIONADA, ALTO_IMAGEN_REDIMENCIONADA, null); 
        }
        g2.dispose();
    }
    
    /**
     * Establece la imagen actual.
     *
     * @param imagen La imagen actual
     * @since 1.6
     */
    private void setImagenTemporal(Image imagen){
        this.imagenTemporalImprimir = (BufferedImage) imagen;   // Imagen imagenTemporalImprimir.
        repaint();                                              // Se dibuja la imagenTemporalImprimir.
    }

    /**
     * Asigna la imagen actual mediante un archivo.
     *
     * @param file El archivo de entrada para guardar la imagen.
     * @throws IOException Error del archivo de entrada para guardar la imagen.
     * @since 1.6
     */
    private void setImagenTemporal(File file) throws IOException{
        setImagenTemporal(ImageIO.read(file));
        repaint();                                              // Se dibuja la imagenTemporalImprimir.
    }
    
    
    /**
     * 
     */
    private void insertarTextoImagen(String campo, int x, int y){
        Texto texto = new Texto(campo, "Dialog", 0 , 36, Color.BLACK, x, y);
        
        // Se agrega texto a la listaTexto.
        agregarTexto(texto); 
    }
    
    /**
     * 
     */
    private void insertarTextoImagenes(){
        // Objetos.
        Object objeto;
        
        for(int i = 0; i < arrayFilasSeleccionadas.size(); i++){
            ArrayList filaSeleccionada = arrayFilasSeleccionadas.get(i);
            Iterator iterador = filaSeleccionada.iterator();
            
            int x = 100;
            int y = 20;
            while(iterador.hasNext()){
                objeto = iterador.next();
                x = x + 50;
                y = y + 50;
                insertarTextoImagen(objeto.toString() , x , y);
  
            }
            // Crea la imagen temporal para imprimir.
            crearImagenTemporal();
            
            // Borra la lista de texto.
            listaTexto.clear();
        }
    }
    
    /**
     * 
     */
    public void prepararImagenesTemporales(){
        insertarTextoImagenes();
        
        imprimirImagenesTemporales();
    }
    
    /**
     * 
     */
    private void crearTextoPrototipo(){
        // Variables.
        int x = 0;
        int y = 0;
        
        /**
         * Como el array de String trae el campo imprimir,
         * restamos la longitud total con 1, porque
         * no queremos usar en la lista.
         */
        for(int i = 0; i < nombreColumnas.length - 1 ; i++){
            StringTokenizer stringTokenizer = new StringTokenizer(arrayPosicionesTexto.get(i).toString(), 
                ",");
            x = Integer.parseInt(stringTokenizer.nextToken());
            y = Integer.parseInt(stringTokenizer.nextToken());
            insertarTextoImagen(nombreColumnas[i], x, y);
        }
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
    }//GEN-LAST:event_formMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        /*
         * Si no existe ninguna imagenInsertada entonces no se hace el drag. 
         */
        if (imagenInsertada == null){
            return;
        }
        /*
         * True solo si el click esta encima de la imagenInsertada.
         */
        if (isMouseDrag) {   
            //--- Ball pos from mouse and original click displacement
            coordenadaX = evt.getX() - dragFromX;
            coordenadaY = evt.getY() - dragFromY;
            
            //--- Don't move the ball off the screen sides
            coordenadaX = Math.max(coordenadaX, 0);
            coordenadaX = Math.min(coordenadaX, getWidth() - ANCHO_IMAGEN_REDIMENCIONADA);
            
            //--- Don't move the ball off top or bottom
            coordenadaY = Math.max(coordenadaY, 0);
            coordenadaY = Math.min(coordenadaY, getHeight() - ALTO_IMAGEN_REDIMENCIONADA);
            
            /*
             * Se vuelve a pintar porque se cambio de posicion.
             */
            repaint();         
        }
        //GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }//GEN-LAST:event_formMouseDragged

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
    }//GEN-LAST:event_formMouseEntered

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        //figuraArrastrandose = null;
        //GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }//GEN-LAST:event_formMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        isMouseDrag = false;
        //GUI_Principal.jLabelCoordenadasPuntero.setText("");
    }//GEN-LAST:event_formMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        /*
         * Si no existe ninguna imagenInsertada entonces no se hace el drag.
         */
        if (getImagenInsertada() == null){
            return;
        }
        
        int x = evt.getX();   // Save the x coord of the click.
        int y = evt.getY();   // Save the y coord of the click.

	if(x >= coordenadaX && x <= (coordenadaX + ANCHO_IMAGEN_REDIMENCIONADA) 
                && y >= coordenadaY && y <= (coordenadaY + ALTO_IMAGEN_REDIMENCIONADA)){
	    isMouseDrag = true;
            dragFromX = x - coordenadaX;  // how far from left.
            dragFromY = y - coordenadaY;  // how far from top.
	}else{
             isMouseDrag = false;
        }
	evt.consume();
        //GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        //GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
        isMouseDrag = false;
        evt.consume();
    }//GEN-LAST:event_formMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * 
     */
    public void imprimirImagenesTemporales(){
        // Objetos.
        ImprimirImagenes imprimirImagenesTemporales = new ImprimirImagenes();
        
        imprimirImagenesTemporales.print(listaImagenesTemporalesImprimir, "Paint Evolution");   
    }      
}