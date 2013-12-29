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
}
