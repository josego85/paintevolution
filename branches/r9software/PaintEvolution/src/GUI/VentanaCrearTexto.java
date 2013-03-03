/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author proyectosbeta
 */
public class VentanaCrearTexto extends javax.swing.JFrame{
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase.
    ////////////////////////////////////////////////////////////////////////////
    private panelDibujoTexto panelDibujoTexto;
    private static String rutaImagenTemporal;
    private static String[] nombreColumnas;
    private static ArrayList<ArrayList> arrayFilasSeleccionadas;
    private DefaultTableModel modelTabla;
    
    /**
     * Cursor actual.
     * @since 1.6
     */
    private Cursor cursorActual;
    
    /**
     * Cursor predeterminado.
     * @since 1.6
     */
    private Cursor cursorPredeterminado;
    
    /**
     * Imagen para el cursor pencil.
     * @since 1.6
     */
     private Image pencilImg;
     
     /**
     * FALTA COMENTAR
     *
     * @since 1.6
     */
    private Toolkit toolKit;
    
    /**
     * Creates new form VentanaCrearTexto
     */
    public VentanaCrearTexto(String rutaImagenTemporal, String[] nombreColumnas,
            ArrayList<ArrayList> arrayFilasSeleccionadas) {
        /*
         * Se guarda la ruta de la imagen temporal para luego usar,
         * al crear un Texto con registros de la base de datos.
         */
        this.rutaImagenTemporal = rutaImagenTemporal;
        
        /*
         * Se guarda el nombre de las columnas.
         * Obs: que viene la columna Imprimir, y deberia suprimirse
         * para no colocar en el combobox.
         */
        this.nombreColumnas = nombreColumnas;
        
        /**
         * Se guardan las filas seleccionadas con sus campos correspondientes.
         */
        this.arrayFilasSeleccionadas = arrayFilasSeleccionadas;
        
        initComponents();
        
        // Crea el objeto de Mesa de Dibujo.
        panelDibujoTexto = new panelDibujoTexto(rutaImagenTemporal);
        
        // Establece un esquema para la mesa de dibujo y agrega a la ventana
        // principal
        getContentPane().add(panelDibujoTexto, java.awt.BorderLayout.EAST);
       
        //setContentPane(panelDibujoTexto);
        
        Cursor cursorLetra = new Cursor(Cursor.TEXT_CURSOR);
        cursorActual = cursorLetra;
        //panelDibujoTexto.setCursorActual(cursorActual);
        panelDibujoTexto.setModoDibujar(0);
        
        // Se carga en la lista de campos seleccionados.
        cargarListColumnasSelecionadas();

         // Modelo para la tabla de PosicionesTexto..
        modelTabla = new DefaultTableModel(); 
        
        // Se agrega a la tabla el modelo.
        jTablePosicionTexto.setModel(modelTabla);
        
        /*
         * Se carga en la lista las posiciones para colocar el Texto de cada campos
         * correspondiente.
         */
        cargarPosiciones();

        pack();
    }
    
    /**
     * Metodo privado que muestra la ventana Impresora.
     * @since 1.6
     */
    private void mostrarVentanaImpresora() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable((Printable) panelDibujoTexto);

        // Dialogo de configuracion de impresora.
        if( printJob.printDialog() ) {
            try {
                printJob.print();
            } catch(java.awt.print.PrinterException e){
                System.out.println("El servicio de impresion esta desactivado.");
            }
            catch( Exception e ) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 
     */
    private void cargarListColumnasSelecionadas(){
        // Objetos.
        DefaultListModel listModelo = new DefaultListModel(); 
        
        /**
         * Como el array de String trae el campo imprimir,
         * restamos la longitud total con 1, porque
         * no queremos usar en la lista.
         */
        for(int i = 0; i < nombreColumnas.length - 1; i++){ 
            listModelo.addElement(nombreColumnas[i]); 
        } 
        // Se agrega al jListCamposSeleccionados los campos seleccionados.
        jListCamposSeleccionados.setModel(listModelo); 
    }
    
    /*
     * 
     */
    private void cargarPosiciones(){
        // Agrego la columna Posicion.
        modelTabla.addColumn("Posición");

        /**
         * Como el array de String trae el campo imprimir,
         * restamos la longitud total con 1, porque
         * no queremos usar en la lista porque tiene
         * que coincidir con el jListCamposSeleccionados.
         */
        for(int i = 0; i < nombreColumnas.length - 1; i++){ 
            Object nuevo[]= {"100,20"};
            modelTabla.addRow(nuevo); 
        } 
        // Se agrega al jTablePosicionTexto las posiciones iniciales.
        jTablePosicionTexto.setModel(modelTabla);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelCrearTexto = new javax.swing.JPanel();
        jButtonInsertarImagen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCamposSeleccionados = new javax.swing.JList();
        jLabelCamposSeleccionados = new javax.swing.JLabel();
        jButtonImprimir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePosicionTexto = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());

        jButtonInsertarImagen.setText("Imagen");
        jButtonInsertarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertarImagenActionPerformed(evt);
            }
        });

        jListCamposSeleccionados.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListCamposSeleccionados);

        jLabelCamposSeleccionados.setText("Campos seleccionados:");

        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jTablePosicionTexto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane2.setViewportView(jTablePosicionTexto);

        javax.swing.GroupLayout jPanelCrearTextoLayout = new javax.swing.GroupLayout(jPanelCrearTexto);
        jPanelCrearTexto.setLayout(jPanelCrearTextoLayout);
        jPanelCrearTextoLayout.setHorizontalGroup(
            jPanelCrearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrearTextoLayout.createSequentialGroup()
                .addGroup(jPanelCrearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonInsertarImagen)
                    .addComponent(jLabelCamposSeleccionados)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCrearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImprimir))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanelCrearTextoLayout.setVerticalGroup(
            jPanelCrearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrearTextoLayout.createSequentialGroup()
                .addGroup(jPanelCrearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCrearTextoLayout.createSequentialGroup()
                        .addComponent(jLabelCamposSeleccionados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCrearTextoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(76, 76, 76)
                .addGroup(jPanelCrearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonInsertarImagen)
                    .addComponent(jButtonImprimir))
                .addGap(446, 446, 446))
        );

        getContentPane().add(jPanelCrearTexto);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        // Se muestra la ventana de la impresora.
        mostrarVentanaImpresora();
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonInsertarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertarImagenActionPerformed
        // Busca una imagen para luego insertar en el panelDibujoTexto.
        panelDibujoTexto.abrirImagen();
    }//GEN-LAST:event_jButtonInsertarImagenActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaCrearTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCrearTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCrearTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCrearTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCrearTexto(rutaImagenTemporal, nombreColumnas,
                        arrayFilasSeleccionadas).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonInsertarImagen;
    private javax.swing.JLabel jLabelCamposSeleccionados;
    private javax.swing.JList jListCamposSeleccionados;
    private javax.swing.JPanel jPanelCrearTexto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTablePosicionTexto;
    // End of variables declaration//GEN-END:variables
}
