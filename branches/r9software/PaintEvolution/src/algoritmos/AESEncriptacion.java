/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Rodo
 */
public class AESEncriptacion {
    private final String ALGORITMO = "AES";         // Algoritmo.
    private final int LONGITUD = 128;               // Longitud de la llave().
    private final String CODIFICACION = "UTF-8";    // Como se convertira a byte.
    private AESKey aesKey;

    public AESEncriptacion(AESKey aesKey) {
        this.aesKey = aesKey;
    }

    public AESKey generaKey() {
        KeyGenerator kgen = null;
        try {
            kgen = KeyGenerator.getInstance(ALGORITMO);
        }catch(NoSuchAlgorithmException ex) {
             error(ex);
        }
        kgen.init(LONGITUD);
        SecretKey skey = kgen.generateKey();
        aesKey = new AESKey();
        aesKey.setEncoded(HexToString(skey.getEncoded()));
        return aesKey;
    }
    
    /*
     * Convierto una cadena, q en este caso es mi key a un arreglo de byte
     * el cual se lo asigno junto con el algoritmo a la clase SecretKeySpec.
     * Obtengo una instancia de Chiper en base al algoritmo, esta me ayudara
     * a encriptar y desencriptar mis codigos
     * A mi variable chiper la pongo en modo encripcion y le paso mi key secreta
     * Obtengo un arreglo de bytes que representa a mi cadena encriptada (resultado
     * de la encripcion)
     * Para que pueda guardar por ejemplo en bd mi cadena encriptada, paso el arreglo
     * de bytes a una cadena, esto lo hago con ayuda del metodo hexToString.
     */
    public String encripta(String cadena) {
        String encriptado = null;
        try {
            byte[] raw = StringToHex(aesKey.getEncoded());
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(cadena.getBytes(CODIFICACION));
            encriptado = HexToString(encrypted);
        }catch (IllegalBlockSizeException ex) {
             error(ex);
        }catch (BadPaddingException ex) {
             error(ex);
        }catch (UnsupportedEncodingException ex) {
             error(ex);
        }catch (InvalidKeyException ex) {
             error(ex);
        }catch (NoSuchAlgorithmException ex) {
             error(ex);
        }catch (NoSuchPaddingException ex) {
             error(ex);
        }
        return encriptado;
    }

    /**
     * Por medio de un for recorro cada posicion del arreglo de bytes
     * En la variable aux obtengo la posicion deseada, indicando que es un hexadecimal
     * por medio de 0xff.
     * Como un hexadecimal lo representare con 2 poficiones ff, 0f, 12... etc
     * en caso de que el resultado fuera menor a 16 solo obtendria 1 posicion
     * lo que me daria problemas al momento de desencriptar
     * En caso de ser menor a 16, le concateno un 0 a mi cadena, luego simplemente
     * concateno mi variable aux, indicando que la guardate en un formato String
     * que representara un hexadecimal > Integer.toHexString(aux).
     * Al Final retorno una cadena que representa a mi arreglo de bytes, de esta forma
     * se podra guardar la informacion en una bd, o imprimirla de una forma entendible.
     */
    private String HexToString(byte[] arregloEncriptado) {
        String textoEncriptado = "";
        for (int i = 0; i < arregloEncriptado.length; i++) {
            int aux = arregloEncriptado[i] & 0xff;
            if (aux < 16) {
                textoEncriptado = textoEncriptado.concat("0");
            }
            textoEncriptado = textoEncriptado.concat(Integer.toHexString(aux));
            }
        return textoEncriptado;
    }

    /**
     * Creo un arreglo de bytes, del tamaño de mi cadena / 2
     * (por eso el agregar un 0 si hera menor a 16 en el metodo de HexToString)
     * Por medio de un for recorro a mi arreglo
     * Dentro del for en la variable index guardare la posicion * 2, esto para siempre
     * tomar pares 2, 4, 6, etc...
     * En la variable aux guardo un subString de index a index+2 (0-2,2-4) siempre sera en pares, 
     * no hay fallas por que el metodo HexToString me retorno una cadena par
     * En una variable int, guardo el parseo del segmento de cadena, con base 16 (Hexadecimal)
     * Para finalizar en la posocion correspondiente de mi arreglo de bytes hago un caso
     * para guardar mi variable entera.
     * Finalmente retornaria un arreglo de bytes identico al que recibio el metodo
     * HexToString, por tanto obtengo mi arreglo encriptado :)
     */
    private byte[] StringToHex(String encriptado) {
        byte[] enBytes = new byte[encriptado.length() / 2];
        
        for (int i = 0; i < enBytes.length; i++) {
            int index = i * 2;
            String aux = encriptado.substring(index, index + 2);
            int v = Integer.parseInt(aux, 16);
            enBytes[i] = (byte) v;
        }
        return enBytes;
    }
    
    /**
     * Obtengo un arreglo de bytes a partir de mi key
     * Se lo asigno junto con el algoritmo a la clase SecretKeySpec.
     * Obtengo una instancia de Chiper en base al algoritmo
     * A mi variable chiper la pongo en modo desencripcion y le paso mi key secret
     * Obtengo un arreglo de bytes a partir de mi cadena encriptada
     * Al final creo un String a partir de mi arreglo de bytes.
     * Esto no lo hice antes, como algunos ejemplos de la red, por que ?
     * por que de este modo si lo hiciera desde el metodo encriptar, habria algunos
     * caracteres que no se podrian representar, por lo tanto se perderia
     * la integridad de la informacion.
     * Es mas pueden intentar realizar el programa sin utilizar HexToString y StringToHex
     * haciendo las conversiones del tipo new String(byte[]) y no trabajaria su codigo
     */
    public String desencriptar(String encriptado) {
            String originalString = null;
        
            try {
                byte[] raw = StringToHex(aesKey.getEncoded());
                SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITMO);
                Cipher cipher = Cipher.getInstance(ALGORITMO);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                byte[] original = cipher.doFinal(StringToHex(encriptado));
                originalString = new String(original);

            }catch (IllegalBlockSizeException ex) {
                 error(ex);
            }catch (BadPaddingException ex) {
                 error(ex);
            }catch (InvalidKeyException ex) {
                 error(ex);
            }catch (NoSuchAlgorithmException ex) {
                 error(ex);
            }catch (NoSuchPaddingException ex) {
                 error(ex);
            }
            return originalString;
    }
    
    /**
     * 
     * @param ex 
     */
    private void error(Exception ex) {
        System.err.print(ex.getMessage());
    }
}