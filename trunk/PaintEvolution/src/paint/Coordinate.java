package paint;

import Auxiliar.Constantes;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author fires
 */
public class Coordinate implements Serializable{
    private int x1,y1,x2,y2;
    private Color foreColor;
    private float tamañoBorde;


    public Coordinate (int inx1, int iny1, int inx2, int iny2, Color color, float tamañoBorde){
        x1 = inx1;
        y1 = iny1;
        x2 = inx2;
        y2 = iny2;
        foreColor = color;
        this.setTamañoBorde(tamañoBorde);
    }

    public Color colour(){
        return foreColor;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1(){
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public float getTamañoBorde() {
        return tamañoBorde;
    }

    public void setTamañoBorde(float tamañoBorde) {
        if(tamañoBorde >= Constantes.MINIMO_GROSOR_BORDE
                && tamañoBorde <= Constantes.MAXIMO_GROSOR_BORDE){
           this.tamañoBorde = tamañoBorde;
        }
    }
}