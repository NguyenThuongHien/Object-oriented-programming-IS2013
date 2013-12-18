/**
 * 
 */
package lab9;

/**
 * @author wukat
 *
 */
public class Fork {
	private boolean using = false;

	public synchronized void use() throws InterruptedException {
		if (using)
			wait();
		using = true;
	}

	public synchronized void drop() {
		using = false;
		notifyAll();
	}
}
