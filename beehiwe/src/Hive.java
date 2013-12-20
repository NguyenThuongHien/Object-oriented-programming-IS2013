import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 */

/**
 * @author wukat
 * 
 */
public class Hive {
	private boolean firstEntryFree;
	private boolean secondEntryFree;
	private ExecutorService execut = Executors.newCachedThreadPool();

	public Hive() {
		firstEntryFree = true;
		secondEntryFree = true;
	}

	public void symulate(int n) {
		for (int i = 1; i <= n; i++)
			execut.execute(new Bee(this, i));
	}

	public synchronized void flyThroughFirstEntry() throws InterruptedException {
		firstEntryFree = false;
		wait(1000);
		firstEntryFree = true;
	}

	public synchronized void flyThroughSecondEntry() throws InterruptedException {
		secondEntryFree = false;
		wait(1000);
		secondEntryFree = true;
	}

	/**
	 * @return the firstEntryFree
	 */
	public boolean isFirstEntryFree() {
		return firstEntryFree;
	}

	/**
	 * @return the secondEntryFree
	 */
	public boolean isSecondEntryFree() {
		return secondEntryFree;
	}

	/**
	 * @return the execut
	 */
	public ExecutorService getExecut() {
		return execut;
	}

}
