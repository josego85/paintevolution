package Auxiliar;

import java.io.File;
import javax.swing.filechooser.FileFilter;



/**
 *
 * @author fires
 */
public class FiltroArchivo extends FileFilter {
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

    public String getDescription() {
        return "*.png";
    }

}
