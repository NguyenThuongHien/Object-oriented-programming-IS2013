package lab9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wukat
 *
 */
public class Philosopher implements Runnable {

    final static Fork[] Forks = {new Fork(), new Fork(), new Fork(), new Fork(), new Fork()};
    final int ID;
    final Fork left;
    final Fork right;

    private Philosopher(int ID) {
        this.ID = ID;
        if (ID == 0) {
            left = Forks[4];
            right = Forks[0];
        } else {
            left = Forks[ID - 1];
            right = Forks[ID];
        }
    }

    public void live() throws InterruptedException {
        while (true) {
            System.out.println(ID
                    + " właśnie kontempluje piękno otaczającego świata");
            Thread.currentThread();
            Thread.sleep(100);
            System.out.println(ID + " bierze lewy widelec");
            left.use();
            System.out.println(ID + " bierze prawy widelec");
            right.use();
            Thread.currentThread();
            Thread.sleep(200);
            System.out.println(ID
                    + " oddaje się przyziemnej przyjemności jedzenia");
            left.drop();
            right.drop();
        }
    }

    @Override
    public void run() {
        try {
            live();
        } catch (InterruptedException e) {
            System.out.println("koniec");
        }
    }

    public static void main(String[] args) {
        ExecutorService execut = Executors.newCachedThreadPool();
        execut.execute(new Philosopher(0));
        execut.execute(new Philosopher(1));
        execut.execute(new Philosopher(2));
        execut.execute(new Philosopher(3));
        execut.execute(new Philosopher(4));

        Thread.currentThread();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
        }
        execut.shutdownNow();
    }
}
