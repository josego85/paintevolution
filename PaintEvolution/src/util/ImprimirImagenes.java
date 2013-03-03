/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;  
import java.util.Collection;  
      
  
import javax.print.Doc;  
import javax.print.DocFlavor;  
import javax.print.DocPrintJob;  
import javax.print.PrintException;  
import javax.print.PrintService;  
import javax.print.PrintServiceLookup;  
import javax.print.SimpleDoc;  
import javax.print.attribute.HashPrintRequestAttributeSet;  
import javax.print.attribute.PrintRequestAttributeSet;  
import javax.print.attribute.standard.JobName;  
import javax.print.attribute.standard.MediaSizeName; 

/**
 *
 * @author proyectosbeta
 */
public class ImprimirImagenes implements Printable{
    // Objetos.
    protected ArrayList imageList = new ArrayList<Object>();  
  
    /**
     * 
     */
    public ImprimirImagenes(){  
    }  
          
    @SuppressWarnings("unchecked")  
    public void print(Collection images, String printerName, String requestNumber){  
        this.imageList.clear();  
        this.imageList.addAll(images);  
        printData(printerName, requestNumber);  
    }  
  
    protected void printData(String printerName, String requestNumber){  
        String printer = printerName;             
              
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;  
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(null, null);  
        PrintService service = null;  
              
        for (int i = 0; i < printService.length; i++){  
            String p_name = printService[i].getName();  
            if (p_name.equals(printer)){  
                service = printService[i];  
            }  
        }                
  
        try{  
            DocPrintJob pj = service.createPrintJob();  
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();  
            aset.add(new JobName(requestNumber, null));  
            aset.add(MediaSizeName.NA_LETTER);  
            Doc doc = new SimpleDoc(this, flavor, null);  
            pj.print(doc, aset);  
        }catch(PrintException pe){  
             System.err.println(pe);  
        }  
    }  
  
    public int print(Graphics g, PageFormat f, int pageIndex){  
        if (pageIndex >= imageList.size()){  
            return Printable.NO_SUCH_PAGE;  
        }  
        RenderedImage image = (RenderedImage) imageList.get(pageIndex);  
  
        if (image != null){  
            Graphics2D g2 = (Graphics2D) g;  
            g2.translate(f.getImageableX(), f.getImageableY());  
  
            int imgWidth = (int)image.getWidth();  
            int imgHeight = (int)image.getHeight();  
            double xRatio = (double) f.getImageableWidth() / (double) imgWidth;  
            double yRatio = (double) f.getImageableHeight() / (double) imgHeight;  
  
            g2.scale(xRatio, yRatio);  
  
            AffineTransform at = AffineTransform.getTranslateInstance(f.getImageableX(), f.getImageableY());  
            g2.drawRenderedImage(image, at);  
                  
            return Printable.PAGE_EXISTS;  
        }else{  
            return Printable.NO_SUCH_PAGE;  
        }  
    }  
}