
package Auxiliar;

/**
 *
 * @author gaby
 */
public class Texto {
    // Variables
    //private String tipoFuentePredeterminada = "Dialog";
    private String contenidoTexto;
    private int tipo;
    private int estilo;
    private int tamanio;

    // Constructor por defecto
    public Texto(String contenidoTexto, int tipo, int estilo, int tamanio){
        setContenidoTexto(contenidoTexto);
        setTipo(tipo);
        setEstilo(estilo);
        setTamaño(tamanio);
    }


    // Metodos get y set
    public String getContenidoTexto() {
        return contenidoTexto;
    }

    public void setContenidoTexto(String contenidoTexto) {
        this.contenidoTexto = contenidoTexto;
    }

    public int getEstilo() {
        return estilo;
    }

    public void setEstilo(int estilo) {
        this.estilo = estilo;
    }

    public int getTamaño() {
        return tamanio;
    }

    public void setTamaño(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}