/**
 * MyFrame.java
 *
 * @author - wukat
 * @data - 19 lis 2013
 */
package lab6;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

/**
 * @author wukat
 *
 */
public class MyPanel extends Panel {

    private final LinkedList<Shape> myFigures;

    /**
     * Constructor
     */
    public MyPanel() {
        this.myFigures = new LinkedList<>();
        // TODO Auto-generated constructor stub
        myFigures.add(new Kolo());
        myFigures.add(new Trojkat());
        myFigures.add(new Kwadrat());
        myFigures.add(new Kwadrat());
        myFigures.add(new Trojkat());
    }

    /**
     * Constructor
     *
     * @param layout
     */
    public MyPanel(LayoutManager layout) {
        super(layout);
        // TODO Auto-generated constructor stub
        this.myFigures = new LinkedList<>();
    }

    @Override
    public void paint(Graphics graphic) {
        graphic.clearRect(0, 0, 300, 300);
        for (Shape figure : myFigures) {
            figure.draw(graphic);
        }
    }

    private static void createAndShowGUI() {
        Frame frame = new Frame("Ksztalty");
        Component panel = new MyPanel();
        panel.setSize(new Dimension(300, 300));
        frame.add(panel, 0);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        frame.setSize(new Dimension(300, 300));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
