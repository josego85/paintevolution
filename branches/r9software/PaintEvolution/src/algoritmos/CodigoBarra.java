/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.awt.image.BufferedImage;
import net.sourceforge.jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Interleaved25;

/**
 *
 * @author proyectosbeta
 */
public class CodigoBarra {
    // Variables de clase.
    private String valorCodigoBarra;
    
   /**
    * Constructor con 1 parametro.
    * @param valorCodigoBarra 
    */
    public CodigoBarra(String valorCodigoBarra){
        setValorCodigoBarra(valorCodigoBarra);
    }
    
    /**
     * Metodo publico que devuelve un BufferedImage del codigo de barra.
     */
    public BufferedImage devolverImagenCodigoBarra(){
        JBarcodeBean barcode = new JBarcodeBean();
        BufferedImage bufferedImage = null;

        // nuestro tipo de codigo de barra
        barcode.setCodeType(new Interleaved25());
        //barcode.setCodeType(new Code39());

        // Nuestro valor a codificar y algunas configuraciones mas.
        barcode.setCode(getValorCodigoBarra());
        barcode.setCheckDigit(true);

        bufferedImage = barcode.draw(new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB));

        // guardar en disco como png
        //File file = new File("codebar.png");
        //ImageIO.write(bufferedImage, "png", file);
        
        return bufferedImage;
    }

    public String getValorCodigoBarra() {
        return valorCodigoBarra;
    }

    public void setValorCodigoBarra(String valorCodigoBarra) {
        this.valorCodigoBarra = valorCodigoBarra;
    }
}