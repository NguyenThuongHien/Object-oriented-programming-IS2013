import java.io.IOException;

/**
 * @author wukat
 *
 */
public class MainBees {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 10;
		Hive hive = new Hive();
		hive.symulate(n);
		
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		hive.getExecut().shutdownNow();
	}
}
