/**
 * @author wukat
 * 
 */
public class Bee implements Runnable {

	private final int ID;
	private final Hive hive;
	private int countIn;
	private int countOut;
	private long timeWaitingIn;
	private long timeWaitingOut;

	public Bee(Hive hive, int ID) {
		this.hive = hive;
		this.ID = ID;
		countIn = 0;
		countOut = 0;
		timeWaitingIn = 0;
		timeWaitingOut = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (true) {
				System.out.println(this + " podlatuje przez przelot 1");
				if (hive.isFirstEntryFree()) {
					hive.flyThroughFirstEntry();
					System.out.println(this + " wlatuje przez przelot 1");
				} else {
					System.out.println(this
							+ " przelot 1 zajęty, sprawdza przelot 2");
					if (hive.isSecondEntryFree()) {
						hive.flyThroughSecondEntry();
						System.out.println(this + " wlatuje przez przelot 2");
					} else {
						System.out.println(this
								+ " przelot 2 zajęty, oczekuje przelot 2");
						long temp = System.currentTimeMillis();
						hive.flyThroughSecondEntry();
						timeWaitingIn += System.currentTimeMillis() - temp;
						System.out.println(this + " wlatuje przez przelot 2");
					}
				}
				countIn++;
				System.out.println(this + " podlatuje przez przelot 1");
				if (hive.isFirstEntryFree()) {
					hive.flyThroughFirstEntry();
					System.out.println(this + " wylatuje przez przelot 1");
				} else {
					System.out.println(this
							+ " przelot 1 zajęty, sprawdza przelot 2");
					if (hive.isSecondEntryFree()) {
						hive.flyThroughSecondEntry();
						System.out.println(this + " wylatuje przez przelot 2");
					} else {
						System.out.println(this
								+ " przelot 2 zajęty, oczekuje przelot 2");
						long temp = System.currentTimeMillis();
						hive.flyThroughSecondEntry();
						timeWaitingOut += System.currentTimeMillis() - temp;
						System.out.println(this + " wylatuje przez przelot 2");
					}
				}
				countOut++;
			}
		} catch (InterruptedException e) {
			double averageIn = timeWaitingIn/countIn;
			double averageOut = timeWaitingOut/countOut;
			System.out.println(ID + " " + countIn + " " + averageIn + " " + countOut + " " + averageOut);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pszczoła " + ID;
	}
}
