/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import baseDatos.ModeloDefaultTableCampoPosicionImagenDinamica;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author proyectosbeta
 */
public class VentanaImprimirImagenDinamica extends javax.swing.JFrame implements TableModelListener {
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase.
    ////////////////////////////////////////////////////////////////////////////
    private PanelDibujoImagenDinamica panelDibujoImagenDinamica;
    private static String rutaImagenTemporal;
    private static String[] nombreColumnas;
    private static ArrayList<ArrayList> arrayFilasSeleccionadas;
    private ModeloDefaultTableCampoPosicionImagenDinamica modeloTablaCamposPosiciones;
    
    /**
     * FALTA COMENTAR
     *
     * @since 1.6
     */
    private Toolkit toolKit;
    
    /*
     * El icono de la aplicacion.
     * @since 1.6
     */
    private Image iconoAplicacion;
    
    /*
     * Constante que indica con cuantos pixeles empieza
     * el ancho del prototipo.
     */
    private final static int COMIENZA_ANCHO_PROTOTIPO = 0;
    
    /*
     * Constante que indica con cuantos pixeles empieza
     * el alto del prototipo.
     */
    private final static int COMIENZA_ALTO_PROTOTIPO = 50;
    
    /*
     * Constante que indica cuanto pixeles incrementa
     * el ancho del prototipo.
     */
    private final static int INCREMENTO_ANCHO_PROTOTIPO = 0;
    
    /*
     * Constante que indica cuanto pixeles incrementa
     * el alto del prototipo.
     */
    private final static int INCREMENTO_ALTO_PROTOTIPO = 80;
    
    /**
     * La ventana RedimensionarImagen.
     * * @since 1.6
     */
    private VentanaRedimensionarImagen ventanaRedimensionarImagen;     
    
    
    /**
     * Creates new form VentanaImprimirImagenDinamica
     */
    public VentanaImprimirImagenDinamica(String rutaImagenTemporal, String[] nombreColumnas,
            ArrayList<ArrayList> arrayFilasSeleccionadas) {
        /*
         * Se guarda la ruta de la imagen temporal para luego usar,
         * al crear un Texto con registros de la base de datos.
         */
        VentanaImprimirImagenDinamica.rutaImagenTemporal = rutaImagenTemporal;
        
        /*
         * Se guarda el nombre de las columnas.
         * Obs: que viene la columna Imprimir, y deberia suprimirse
         * para no colocar en el combobox.
         */
        VentanaImprimirImagenDinamica.nombreColumnas = nombreColumnas;
        
        /**
         * Se guardan las filas seleccionadas con sus campos correspondientes.
         */
        VentanaImprimirImagenDinamica.arrayFilasSeleccionadas = arrayFilasSeleccionadas;
        
        initComponents();

        // Modelo para jTableCamposPosiciones.
        modeloTablaCamposPosiciones = new ModeloDefaultTableCampoPosicionImagenDinamica(); 

        // Se agrega a la tabla (jTableCamposPosiciones) el modelo.
        jTableCamposPosiciones.setModel(modeloTablaCamposPosiciones);
        
        cargarDatosTabla();
        
        /*
         * Guarda en un array las posiciones de los Texto (campos) que
         * el usuario habia seleccionado anteriormente.
         */
        ArrayList<String> arrayPosicionesTexto = crearArrayPosicionesTexto();
        
        /*
         * Guarda en un array los algortimos que coiciden con las posiciones de 
         * los textos.
         */
        ArrayList<String> arrayAlgoritmos = crearArrayAlgoritmos();
        
        // Centrar la VentanaImprimirImagenDinamica.
        setLocationRelativeTo(null);

        // Deshabilitar la opcion de Maximizar ventana.
        setResizable(true);
        
        /*
         * Para que el canvas contenga 350 de ancho se tiene que poner la ventana
         * VentanaImprimirImagenDinamica a 487 de ancho. Para que el canvas contenga 700 de alto 
         * se tiene que poner la ventana VentanaImprimirImagenDinamica a 977 de alto. 
         */
        setSize(1075, 487);
        
        // Agregar el icono de la aplicacion.
        toolKit = Toolkit.getDefaultToolkit();
        iconoAplicacion = toolKit.getImage(getClass().getResource("/imagenes/iconos/paintEvolution.png"));
        this.setIconImage(iconoAplicacion);
        
        // Crea el objeto de Mesa de Dibujo.
        panelDibujoImagenDinamica = new PanelDibujoImagenDinamica(rutaImagenTemporal, 
            arrayFilasSeleccionadas, nombreColumnas, arrayPosicionesTexto, arrayAlgoritmos);
        
        /*
         * Establece un esquema para la mesa de dibujo y agrega a la
         * VentanaImprimirImagenDinamica.
         */ 
        getContentPane().add(panelDibujoImagenDinamica, java.awt.BorderLayout.CENTER);
        
        // No se puede mover las columnas de posicion de jTableCamposPosiciones.
        jTableCamposPosiciones.getTableHeader().setReorderingAllowed(false); 
        
        // Escuchador del modeloTablaCamposPosiciones.
        modeloTablaCamposPosiciones.addTableModelListener(this);
        
        pack();
        
        // Escuchador que se cierra la ventana.
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
 
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                try{
                    // Elimina los archivos de las imagenes temporales.
                    panelDibujoImagenDinamica.getImprimirImagenesTemporales().eliminarArchivosTemporalesImpresion();
                }catch (Exception e){
                    
                }finally{
                    System.exit(0);
                }
            }
        });
    }
    
    /**
     * Metodo privado que carga datos al modelo (modeloTablaCamposPosiciones).
     */
    private void cargarDatosTabla(){
        // Variables.
        int x = COMIENZA_ANCHO_PROTOTIPO;
        int y = COMIENZA_ALTO_PROTOTIPO;

        // Agregar columnas al modeloTablaCamposPosiciones.
        modeloTablaCamposPosiciones.addColumn("Campos");
        modeloTablaCamposPosiciones.addColumn("Posiciones(x,y)");
        modeloTablaCamposPosiciones.addColumn("Algoritmos");
        
        /**
         * Como el array de String trae el campo imprimir,
         * restamos la longitud total con 1, porque
         * no queremos usar en la tabla porque no es necesario.
         */
        for(int i = 0; i < nombreColumnas.length - 1; i++){ 
            x = x + INCREMENTO_ANCHO_PROTOTIPO;
            y = y + INCREMENTO_ALTO_PROTOTIPO;
            Object nuevo[] = new Object[3];
            nuevo[0] = nombreColumnas[i];
            nuevo[1] = "" + x + "," + y;
            nuevo[2] = "Ninguno";           // Valor por defecto.
            this.modeloTablaCamposPosiciones.addRow(nuevo); 
        } 
        
        // Crear ComboBox con los algoritmos QR, AES, Codigo de barras.
        String[] itemsAlgorimos = {"Ninguno", "QR", "AES", "Codigo de barra"};
        JComboBox comboBox = new JComboBox(itemsAlgorimos);
        DefaultCellEditor defaultCellEditor = new DefaultCellEditor(comboBox);
        jTableCamposPosiciones.getColumnModel().getColumn(2).setCellEditor(defaultCellEditor);
    }

    /**
     * Metodo privado que devuelve en un arrayList de las posiciones "x" e "y"
     * de los textos.
     */
    private ArrayList<String> crearArrayPosicionesTexto(){
        // Objetos.
        ArrayList<String> arrayPosicionesTexto = new ArrayList<String>();
        DefaultTableModel modeloTabla_temp = (DefaultTableModel)jTableCamposPosiciones.getModel();
        
        for(int i = 0; i < modeloTabla_temp.getRowCount(); i++){   
            arrayPosicionesTexto.add((String)modeloTabla_temp.getValueAt(i, 1).toString());
        } 
        return arrayPosicionesTexto;
    }
    
    /**
     * Metodo privado que devuelve en un arrayList los algoritmos-
     */
    private ArrayList<String> crearArrayAlgoritmos(){
        // Objetos.
        ArrayList<String> arrayAlgoritmos = new ArrayList<String>();
        DefaultTableModel modeloTabla_temp = (DefaultTableModel)jTableCamposPosiciones.getModel();
        
        for(int i = 0; i < modeloTabla_temp.getRowCount(); i++){   
            arrayAlgoritmos.add((String)modeloTabla_temp.getValueAt(i, 2).toString());
        } 
        return arrayAlgoritmos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelOpciones = new javax.swing.JPanel();
        jPanelTablaPosiciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCamposPosiciones = new javax.swing.JTable();
        jPanelBotones = new javax.swing.JPanel();
        jButtonInsertarImagen = new javax.swing.JButton();
        jButtonRedimensionarImagenInsertada = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Imprimir imagenes");

        jPanelOpciones.setLayout(new java.awt.BorderLayout());

        jPanelTablaPosiciones.setPreferredSize(new java.awt.Dimension(300, 295));

        jTableCamposPosiciones.setAutoCreateRowSorter(true);
        jTableCamposPosiciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Campos", "Posiciones (x,y)", "Algoritmos"
            }
        ));
        jScrollPane1.setViewportView(jTableCamposPosiciones);

        javax.swing.GroupLayout jPanelTablaPosicionesLayout = new javax.swing.GroupLayout(jPanelTablaPosiciones);
        jPanelTablaPosiciones.setLayout(jPanelTablaPosicionesLayout);
        jPanelTablaPosicionesLayout.setHorizontalGroup(
            jPanelTablaPosicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTablaPosicionesLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanelTablaPosicionesLayout.setVerticalGroup(
            jPanelTablaPosicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTablaPosicionesLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        jPanelOpciones.add(jPanelTablaPosiciones, java.awt.BorderLayout.NORTH);

        jButtonInsertarImagen.setText("Imagen");
        jButtonInsertarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertarImagenActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonInsertarImagen);

        jButtonRedimensionarImagenInsertada.setText("Redimensionar Imagen");
        jButtonRedimensionarImagenInsertada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRedimensionarImagenInsertadaActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonRedimensionarImagenInsertada);

        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonImprimir);

        jPanelOpciones.add(jPanelBotones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelOpciones, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInsertarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertarImagenActionPerformed
        // Busca una imagen para luego insertar en el panelDibujoTexto.
        panelDibujoImagenDinamica.abrirImagen();
    }//GEN-LAST:event_jButtonInsertarImagenActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        panelDibujoImagenDinamica.prepararImagenesTemporales();
        
        // Se cierra VentanaImprimirImagenDinamica.
        //this.dispose();
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonRedimensionarImagenInsertadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRedimensionarImagenInsertadaActionPerformed
        // Abre la ventanaRedimensionarImagen.
        mostrarVentanaRedimensionarImagen();
        
        if(ventanaRedimensionarImagen.isRedimensionarImagen()){
             panelDibujoImagenDinamica.setSeRedimensionarImagen(true);
             
             // Dibuja en el panelDibujoImagenDinamica.
             panelDibujoImagenDinamica.repaint();
        }  
    }//GEN-LAST:event_jButtonRedimensionarImagenInsertadaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaImprimirImagenDinamica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaImprimirImagenDinamica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaImprimirImagenDinamica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaImprimirImagenDinamica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaImprimirImagenDinamica(rutaImagenTemporal, nombreColumnas,
                    arrayFilasSeleccionadas).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonInsertarImagen;
    private javax.swing.JButton jButtonRedimensionarImagenInsertada;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelTablaPosiciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCamposPosiciones;
    // End of variables declaration//GEN-END:variables

    @Override
    public void tableChanged(TableModelEvent e) {
        // Variables.
        int fila = e.getFirstRow();
	int columna = e.getColumn();
        
        /*
         * Solo se verifica a la columna 1.
         * En la columna dos, se cambia el valor del algoritmo.
         */
        if(columna == 1){
            String valorCeldaCambiada = String.valueOf(jTableCamposPosiciones.getValueAt(fila, columna));
   
            if(validarCoordenadasXeY(valorCeldaCambiada)){
                // Actualiza las posiciones de Texto en el panel de dibujo texto.
                panelDibujoImagenDinamica.actualizarPosicionTexto(fila, valorCeldaCambiada);

                /*
                 * Se redibuja en el panel de Dibujo text para que se visualice los cambios.
                 */
                panelDibujoImagenDinamica.repaint();
            }else{
                 JOptionPane.showMessageDialog(this, "Por favor introduzca correctamente "
                     + "x e y separados entre coma y que x >= 0 e y >= 17. Ej: 150,95", 
                     "Error", JOptionPane.ERROR_MESSAGE);

                 /**
                  * x comienza por 100 y se incrementa con 50.
                  * y comienza por 50 y se incrementa con 45.
                  * O sea, la primera file seria 150,75 y la segunda 200,100, y
                  * asi sucesivamente. Estos son los valores de las coordenadas por
                  * defecto.
                  */
                 int x = (COMIENZA_ANCHO_PROTOTIPO + INCREMENTO_ANCHO_PROTOTIPO * (fila + 1));
                 int y = (COMIENZA_ALTO_PROTOTIPO + INCREMENTO_ALTO_PROTOTIPO * (fila + 1));
                 String valorCelda = "" + x + "," + y; 
                 modeloTablaCamposPosiciones.setValueAt(valorCelda, fila, columna);
            }  
        }else if (columna == 2){
             String valorCeldaCambiada = String.valueOf(jTableCamposPosiciones.getValueAt(fila, columna));
             
             // Actualiza el valor del algoritmo.
             panelDibujoImagenDinamica.actualizarArrayAlgoritmos(fila, valorCeldaCambiada);
        }
    }
    
    /**
     * Metodo privado que devuelve true o false si el usuario introdujo correctamente 
     * en la celda numeros y una coma para separar en ellos. Ejemplo x,y --> 100,30
     * @param coordenadasXeY 
     */
    private boolean validarCoordenadasXeY(String coordenadasXeY){
        // Objetos.
        StringTokenizer stringTokenizer = new StringTokenizer(coordenadasXeY, 
            ",");
        
        // Variables.
        int x, y;
        
        try{
            x = Integer.parseInt(stringTokenizer.nextToken());
            y = Integer.parseInt(stringTokenizer.nextToken());
            
            /**
             * x puede valer desde 0 a 
             * y puede valer desde 17 a 
             */
            if(x >= 0 && y >= 17){
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * Metodo privado que muestra la ventana Texto.
     * @since 1.6
     */
    private void mostrarVentanaRedimensionarImagen() {
        if (ventanaRedimensionarImagen == null) {
            ventanaRedimensionarImagen = new VentanaRedimensionarImagen(null, true);
            ventanaRedimensionarImagen.setLocationRelativeTo(this);
        }
        ventanaRedimensionarImagen.setVisible(true);
    }
}