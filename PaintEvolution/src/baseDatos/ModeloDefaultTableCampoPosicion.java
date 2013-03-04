/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import javax.swing.table.DefaultTableModel;

/**
 * Clase ModeloDefaultTableCampoPosicion que hereda de DefaultTableModel
 * para que solo se pueda editar la columna 1.
 * @author proyectosbeta
 */
public class ModeloDefaultTableCampoPosicion extends DefaultTableModel{
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Solo si es columna 1 se puede editar.
        if(columnIndex == 1){
            return true;
        }
        return false;
    } 
}