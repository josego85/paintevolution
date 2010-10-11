package Auxiliar;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *@(#)FiltroArchivo.java
 *
 * @author fires
 * @version 1.00
 * @since 1.6
 */
/**
 * Clase Filtro de los archivos. Solo filtra archivos *.png.
 * @since 1.6
 */
public class FiltroArchivo extends FileFilter {
     ////////////////////////////////////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Constructor por defecto y vacio.
     * @since 1.6
     */
    public FiltroArchivo(){
    }


    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Para saber si el archivo de entrada tiene un formato png.
     * 
     * @param archivo El archivo de entrada
     * @return True si la extension final es png
     * @since 1.6
     */
    @Override
    public boolean accept(File archivo) {
        if (archivo.isDirectory()) {
            return true;
        }

        String s = archivo.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if ("png".equals(extension)){
                    return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Devuelve siempre la extension png.
     *
     * @return Devuelve *.png
     * @since 1.6
     */
    @Override
    public String getDescription() {
        return "*.png";
    }
}