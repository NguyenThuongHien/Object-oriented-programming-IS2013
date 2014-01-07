/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplet1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author wukat
 */
public class Wielobok extends Polygon {

    Wielobok(int[] x, int[] y) {
        super(x, y, Math.min(x.length, y.length));
        //wywołanie konstruktora klasy Polygon
    }

    void rysuj(Graphics g, Color k) {
        Color b = g.getColor();
        g.setColor(k);
 //zapamiętanie i zmiana bieżącego koloru dla kanwy graficznej
        g.fillPolygon(this);
// narysowanie wieloboku o kształcie obiektu klasy Wielobok (Polygon)
        g.setColor(b); // przywrócenie bieżącego koloru kanwy
    }
    
    void przesun(int dx, int dy) {
        super.translate(dx, dy);
    }
    
    void skaluj(double sx, double sy) {
        for (int x : super.xpoints) 
            x = (int) Math.round(x * sx);
        for (int y : super.ypoints) 
            y = (int) Math.round(y * sy);
    }
    
    void obrot(int x0, int y0, int alfa) {
        przesun(-x0, -y0);
        int [] x = super.xpoints;
        int [] y = super.ypoints;
        int tempX;
        for (int i = 0; i < super.npoints; i++) {
            tempX = (int) Math.round(x[i] * Math.cos(alfa * Math.PI / 180) - y[i] * Math.sin(alfa * 2 * Math.PI / 180));
            y[i] = (int) Math.round(x[i] * Math.cos(alfa * Math.PI / 180) + y[i] * Math.sin(alfa * 2 * Math.PI / 180));
            x[i] = tempX;
        }
        przesun(x0, y0);
    }
}
