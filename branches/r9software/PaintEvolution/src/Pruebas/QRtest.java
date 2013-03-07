package Pruebas;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class QRtest{
    private static OutputStream outputStream;
    public static void main(String args[]){

File file = QRCode.from("Hello World").file();
ByteArrayOutputStream stream = QRCode.from("Hello World").stream();
 
QRCode.from("Hello World").to(ImageType.JPG).file();
QRCode.from("Hello World").to(ImageType.JPG).stream();
 
QRCode.from("Hello World").withSize(250, 250).file();
        ByteArrayOutputStream stream1 = QRCode.from("Hello World").withSize(250, 250).stream();
        

        byte[] toByteArray = stream1.toByteArray();

BufferedImage image = null;
        try {
            image = ImageIO.read( new ByteArrayInputStream( toByteArray ) );
            ImageIO.write(image,"jpg", new File("filename.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(QRtest.class.getName()).log(Level.SEVERE, null, ex);
        }

 
    }
}