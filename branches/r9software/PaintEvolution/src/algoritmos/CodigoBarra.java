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
    
    public CodigoBarra(){
    }
    
    /**
     * 
     */
    public BufferedImage devolverImagenCodigoBarra(){
        JBarcodeBean barcode = new JBarcodeBean();
        BufferedImage bufferedImage = null;

        // nuestro tipo de codigo de barra
        barcode.setCodeType(new Interleaved25());
        //barcode.setCodeType(new Code39());

        // nuestro valor a codificar y algunas configuraciones mas
        barcode.setCode("1234554321987654321123456789");
        barcode.setCheckDigit(true);

        bufferedImage = barcode.draw(new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB));

        // guardar en disco como png
        //File file = new File("codebar.png");
        //ImageIO.write(bufferedImage, "png", file);
        
        return bufferedImage;
    }
}