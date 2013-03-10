/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

/**
 * Clase AES.
 * @author Rodo
 */

public class AES { 
    // Variables de clase.
    private String cadena;
    
    /**
     * Constructor con 1 parametro.
     * @param cadena 
     */
    public AES(String cadena){
        setCadena(cadena);
    }

    /**
     * Metodo publico que encripta un texto.
     * @return 
     */
    public String encriptar (){ 
        // Objetos.
        AESKey aesKey = new AESKey();
        AESEncriptacion tmp = new AESEncriptacion(aesKey);
        
        aesKey = tmp.generaKey();
        AESEncriptacion ejemplo = new AESEncriptacion(aesKey);
        String valorEncriptado = ejemplo.encripta(getCadena());
        return valorEncriptado;
    } 
    
    /**
     * Metodo publico que desencripta un texto.
     * @return 
     */
    public String desencriptar(){
        // Objetos.
        AESKey aesKey = new AESKey();
        AESEncriptacion tmp = new AESEncriptacion(aesKey);
        
        aesKey = tmp.generaKey();
        AESEncriptacion ejemplo = new AESEncriptacion(aesKey);
        String valorEncriptado = ejemplo.encripta(getCadena());
        String desencriptado = ejemplo.desencriptar(valorEncriptado);
        return desencriptado;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
}