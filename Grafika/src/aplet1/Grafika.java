/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplet1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author wukat
 */
public class Grafika extends Applet {

    Samochód S;
    Lokomotywa L;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        setBackground(Color.orange); // ustawienie koloru tła okna apletu
        setSize(500, 400); // zmiana rozmiaru okna apletu
        S = new Samochód();
        L = new Lokomotywa();
    }

    @Override
    public void paint(Graphics g) {
        int[] x = {0, getWidth() / 2, getWidth()};
// nowa tablica jest tworzona na podstawie opisu jej elementów,
        int[] y = {0, 60, 0};
// oddzielonych przecinkami i zawartych w nawiasach klamrowych
        Wielobok T = new Wielobok(x, y);
// wielobok T jest trójkątem równoramiennym o wysokości 60 punktów
        //klasy Graphics i Font trzeba importować
        g.clearRect(0, 0, getWidth(), getHeight());
//wypełnienie obszaru apletu kolorem tła
        Font f = new Font("SanSerif", Font.ITALIC, 18);
        //utworzenie czcionki o podanej nazwie, stylu i rozmiarze
        g.setFont(f);
        g.setColor(Color.blue);
        String s = "RYSOWANIE WIELOBOKÓW";
        T.rysuj(g, Color.WHITE);
        g.drawString(s, (getWidth() - g.getFontMetrics().stringWidth(s)) / 2, 20);
        S.rysuj(g, Color.red);
        L.rysuj(g, Color.black);
    }
    // TODO overwrite start(), stop() and destroy() methods
}
