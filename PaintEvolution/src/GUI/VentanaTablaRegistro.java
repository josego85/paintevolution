/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import baseDatos.ModeloDefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author proyectosbeta
 */
public class VentanaTablaRegistro extends javax.swing.JFrame {
    // Objetos.
    private static String rutaImagenTemporal;
    private static String[] nombreColumnas;
    private static ArrayList listaNombreColumnas;
    private static ResultSet resultSetRegistros;
    private ModeloDefaultTableModel modeloDefaultTableModel;
    
    
    /**
     * Creates new form VentanaTablaRegistro
     */
    public VentanaTablaRegistro(String rutaImagenTemporal, ArrayList listaNombreColumnas, 
            ResultSet resultSetRegistros) {
        /*
         * Los valores de los parametros se guardan en variables de clase,
         * porque lueg se los va a usar.
         */
        VentanaTablaRegistro.rutaImagenTemporal = rutaImagenTemporal;
        VentanaTablaRegistro.listaNombreColumnas = listaNombreColumnas;
        VentanaTablaRegistro.resultSetRegistros = resultSetRegistros;
        
        initComponents();

        /*
         * Agregamos el JScrollPane al contenedor.
         */ 
        //getContentPane().add(scrollPane, BorderLayout.CENTER);

        
        /*
         * En este array se cargan las columnas que viene de la listaNombreColumnas.
         */
        VentanaTablaRegistro.nombreColumnas = cargarColumnas();
        
        /* 
         * Se crea una instancia del modelo con las nombre de las columnas
         * que se le pasa al constructor y la cantidad de columnas.
         */ 
        modeloDefaultTableModel = new ModeloDefaultTableModel(nombreColumnas, 
            nombreColumnas.length -1);
        
        // Se agrega filas al modelo.
        agregarFilas();
                
        /*
         * Se coloca el modelo a la tabla.
         */
        jTableSeleccionarDatos.setModel(modeloDefaultTableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSeleccionarDatos = new javax.swing.JTable();
        jButtonSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar registros");

        jTableSeleccionarDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableSeleccionarDatos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButtonSiguiente.setText("Siguiente");
        jButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSiguiente)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButtonSiguiente)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteActionPerformed
        ArrayList<ArrayList> arrayFilasSeleccionadas = devolverArrayFilasSeleccionadas();
        
        if(!arrayFilasSeleccionadas.isEmpty()){
            // Ir a la ventanaCrearTexto.
            VentanaCrearTexto ventanaCrearTexto = new VentanaCrearTexto(rutaImagenTemporal, 
            nombreColumnas, arrayFilasSeleccionadas);
            ventanaCrearTexto.setLocationRelativeTo(this);
            ventanaCrearTexto.setVisible(true);
        
            // Se cierra la VentanaTablaRegistro.
            this.dispose();
        }else{
            String mensaje = "Seleccione por lo menos un registro para imprimir!!!";
            JOptionPane.showMessageDialog(this, mensaje,
                "Aviso", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSiguienteActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaTablaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaTablaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaTablaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaTablaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaTablaRegistro(rutaImagenTemporal, listaNombreColumnas, 
                    resultSetRegistros).setVisible(true);
            }
        });
    }
    
    /**
     * Metodo privado que carga en un array la lista de nombres de la columna,
     * incluyendo el campo "imprimir" que contiene el checkbox.
     * @return 
     */
    private String[] cargarColumnas(){
        String[] nombreColumnas_temp = new String[listaNombreColumnas.size() + 1];
        
        for(int i = 0; i < listaNombreColumnas.size(); i++){
            nombreColumnas_temp[i] = (String)listaNombreColumnas.get(i);
        }
        nombreColumnas_temp[listaNombreColumnas.size()] = "imprimir";
        return nombreColumnas_temp;
    }
    
    /**
     * Metodo privado que agrega todos los registros en el modeloDefaultTableModel.
     */
    private void agregarFilas(){
        // Objetos.
        Object datos[] = new Object[nombreColumnas.length];     // Numero de columnas de la tabla.

        // 
        /**
         * Eliminar filas vacias del modeloDefaultTableModel.
         * De alguna manera crea filas vacias como columnasNmbres haya de la consulta 
         * de la base de datos. 
         * En este caso se resta la cantidad total por 1, porque se le agrego la
         * columna "imprimir".
         * Esta parte de borrar las filas, es un codigo sucio que se tiene
         * que ver de alguna manera de que el modelo no tenga ningun valor por
         * defecto.
         */
        for(int i = 0; i < nombreColumnas.length - 1; i++){
            this.modeloDefaultTableModel.removeRow(0);
        }

        try {
            while (resultSetRegistros.next()) {
                for (int i = 0; i < nombreColumnas.length; i++) {
                    if(i < nombreColumnas.length -1 ){
                        datos[i] = resultSetRegistros.getObject(i + 1);
                    }else{
                        //datos[i] = Boolean.TRUE;
                        datos[i] = Boolean.FALSE;
                    }
	        }
                this.modeloDefaultTableModel.addRow(datos);
	    }
	}catch(Exception e) {
            System.out.println("El error es: " + e.getMessage());
        }finally{
            if(resultSetRegistros != null){
                try{
                    // Se cierra el ResultSet (resultSetRegistros).
                    resultSetRegistros.close();
                    
                    //System.out.println("Se cierra el ResultSet de las tablas!!!");
                }catch(SQLException ex){ 
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }catch(Exception ex){  
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Metodo privado que devuelve en un arrayList todas las filas seleccionadas
     * por el usuario.
     * @return 
     */
    private ArrayList<ArrayList> devolverArrayFilasSeleccionadas(){
        // Objetos.
        ArrayList<ArrayList> arrayFilas = new ArrayList(); 
        Object objeto;
        Boolean seImprime;
        
        // Variables.
        int cantidadRegistros = modeloDefaultTableModel.getRowCount();
        int cantidadColumnas = modeloDefaultTableModel.getColumnCount();

        System.out.println("La cantidad de filas es: " + cantidadRegistros);
        
        for(int i = 0; i < cantidadRegistros; i++ ){
            // Objetos.
            ArrayList<Object> arrayFila = new ArrayList();

            for(int j = 0; j < cantidadColumnas; j++){
                objeto = modeloDefaultTableModel.getValueAt(i, j);
                if (objeto instanceof Boolean) {
                    seImprime = (Boolean) objeto;
                    if (seImprime.booleanValue()) {
                        /*
                         * La ultima columna pertenece al campo boolean,
                         * y como no queremos guarda ese valor de ese campo, 
                         * entonces cantidadColumnas - 1.
                         */
                        for(int z = 0; z < cantidadColumnas - 1; z++){
                            arrayFila.add((Object) modeloDefaultTableModel.getValueAt(i, z));
                        }
                        /*
                         * Se agrega al arrayFilas el arrayFila.
                         */
                       arrayFilas.add(arrayFila);  
                    }
                }
            }
        }
        return arrayFilas;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSeleccionarDatos;
    // End of variables declaration//GEN-END:variables
}
