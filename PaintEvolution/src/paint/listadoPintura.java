/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package paint;

import java.awt.Color;

/**
 *
 * @author oocf
 */
public class listadoPintura {
    int inX,finX,inY,finY,tipo;
    Color color;
    
    public listadoPintura() {
        
    }
    public listadoPintura(int x1,int x2, int y1, int y2, Color c, int tip){
        this.inX=x1;
        this.finX=x2;
        this.inY=y1;
        this.finY=y2;
        this.color=c;
        this.tipo=tip;
    }
}
