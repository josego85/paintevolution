/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import javax.swing.table.DefaultTableModel;

/**
 * Clase ModeloDefaultTableCampoPosicionImagenDinamica que hereda de DefaultTableModel
 * para que solo se pueda editar las columnas 1 y 2 en la tabla de la imagen
 * dinamica.
 * @author proyectosbeta
 */
public class ModeloDefaultTableCampoPosicionImagenDinamica extends DefaultTableModel{
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Solo si son columna 1 y 2 se puede editar.
        if(columnIndex == 1 || columnIndex == 2){
            return true;
        }
        return false;
    } 
}