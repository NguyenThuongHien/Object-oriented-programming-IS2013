/*
<applet code = "Grafika.class" width = "300" height = "300">
    </applet>
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
    Lokomotywa L, L1;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        setBackground(Color.orange); // ustawienie koloru tła okna apletu
        setSize(500, 400); // zmiana rozmiaru okna apletu
        S = new Samochód();
        S.skaluj(2, 1.5);
        S.przesun(100, 0);
        L = new Lokomotywa();
        L1 = new Lokomotywa();
        L1.przesun(100, 100);
        L1.obrot(150, 350, 210);
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
        L1.rysuj(g, Color.green);
    }
    // TODO overwrite start(), stop() and destroy() methods
}
