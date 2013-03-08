/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

/**
 *
 * @author Rodo
 */
import java.security.*; 
import javax.crypto.*; 
import javax.crypto.spec.*; 
import java.io.*; 
import java.util.*; 

public class AES { 
    public static void encriptar (char[] pass, File desde, File hacia) throws Exception{ 
        byte[] pass16 = getClave16 (pass); 
        Arrays.fill (pass, (char)0); 

        byte[] iv = "1234567890123456".getBytes("UTF-8"); 
        IvParameterSpec extra = new IvParameterSpec(iv); 

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
        SecretKeySpec key = new SecretKeySpec (pass16, "AES"); 
        Arrays.fill (pass16, (byte)0); 
        c.init (Cipher.ENCRYPT_MODE, key, extra); 

        FileInputStream in = new FileInputStream(desde); 
        FileOutputStream out = new FileOutputStream(hacia); 
        out.write (iv); 
        byte[] buffer = new byte[4096]; 
        int i; 
        while ((i = in.read(buffer)) != -1) { 
            out.write(c.update(buffer, 0, i)); 
        } 
        out.write (c.doFinal()); 
        out.close(); 
        in.close(); 
    } 

    public static void main (String[] args)throws Exception { 
        String s = "miclavesereta"; 
        AES.encriptar (s.toCharArray(), new File("C://Users//Rodo//Pictures//fondo.jpg"), new File ("C://Users//Rodo//Pictures//fondo_prote.jpg")); 
        AES.desencriptar (s.toCharArray(), new File("C://Users//Rodo//Pictures//fondo_prote.jpg"), new File ("C://Users//Rodo//Pictures//fondo_copia.jpg")); 
    } 

    public static void desencriptar (char[] pass, File desde, File hacia)throws Exception{ 
        byte[] pass16 = getClave16 (pass); 
        Arrays.fill (pass, (char)0); 
        byte[] iv = "1234567890123456".getBytes("UTF-8"); 
        IvParameterSpec extra = new IvParameterSpec(iv); 

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
        SecretKeySpec key = new SecretKeySpec (pass16, "AES"); 
        Arrays.fill (pass16, (byte)0); 
        c.init (Cipher.DECRYPT_MODE, key, extra); 

        FileInputStream in = new FileInputStream(desde); 
        int n;//* 
        int total = 0; 
        while (total < iv.length){ 
            n = in.read (iv, total, iv.length - total); 
            if (n == -1){ 
                throw new Exception ("No se pudo desencriptar."); 
            } 
            total += n; 
        }//*/ 

        FileOutputStream out = new FileOutputStream(hacia); 
        byte[] buffer = new byte[4096]; 
        while ((n = in.read(buffer)) != -1){ 
            out.write(c.update(buffer, 0, n)); 
        } 
        out.write(c.doFinal()); 
        out.close(); 
        in.close(); 
    } 

    public static byte[] getClave16 (char[] clave){ 
        MessageDigest md = null; 
        try { 
            md = MessageDigest.getInstance ("SHA-1"); 
        }catch(Exception e){} 
        byte[] msg = new byte[clave.length]; 
        for (int i=0; i<clave.length; ++i) { 
            msg[i] = (byte)clave[i]; 
        } 
        md.update(msg); 

        byte[] msg2 = md.digest(); 
        Arrays.fill (msg, (byte)0); 
        System.out.println ("" + System.currentTimeMillis()); 
        for (int i=0; i<70000; ++i){ 
            msg = new byte[msg2.length + 1]; 
            System.arraycopy (msg2, 0, msg, 0, msg2.length); 
            msg[msg2.length] = (byte)(i%128); 
            md.update (msg); 
            Arrays.fill (msg2, (byte)0); 
            msg2 = md.digest(); 
            Arrays.fill (msg, (byte)0); 
        } 
        byte[] clave16 = new byte[16]; 
        for (int i=0; i<16; ++i){ 
            clave16[i] = msg2[i]; 
        } 
        Arrays.fill (msg2, (byte)0); 

        return clave16; 
    }
}