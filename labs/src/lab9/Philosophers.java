/**
 * 
 */
package lab9;

/**
 * @author wukat
 * 
 */
public class Philosophers implements Runnable {

	final Integer fork1 = 1, fork2 = 1, fork3 = 1, fork4 = 1, fork5 = 1;
	final int ID;

	private Philosophers(int ID) {
		this.ID = ID;
	}

	synchronized public void getForks(int ID)  {
		switch (ID) {
		case 1:
			synchronized (fork5) {
				System.out.println("Filozof " + ID + " ma lewy widelec.");
				synchronized (fork1) {
					System.out.println("Filozof " + ID + " ma prawy widelec.");
//					 while (true) {
					 try {
							Thread.currentThread();
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//					 }
				}
			}
			break;
		case 2:
			synchronized (fork1) {
				System.out.println("Filozof " + ID + " ma lewy widelec.");
				synchronized (fork2) {
					System.out.println("Filozof " + ID + " ma prawy widelec.");
					// while (true) {
					// }
					 try {
							Thread.currentThread();
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			break;
		case 3:
			synchronized (fork2) {
				System.out.println("Filozof " + ID + " ma lewy widelec.");
				synchronized (fork3) {
					System.out.println("Filozof " + ID + " ma prawy widelec.");
					// while (true) {
					// }
					 try {
							Thread.currentThread();
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			break;
		case 4:
			synchronized (fork3) {
				System.out.println("Filozof " + ID + " ma lewy widelec.");
				synchronized (fork4) {
					System.out.println("Filozof " + ID + " ma prawy widelec.");
					// while (true) {
					// }
					 try {
							Thread.currentThread();
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			break;
		case 5:
			synchronized (fork4) {
				System.out.println("Filozof " + ID + " ma lewy widelec.");
				synchronized (fork5) {
					System.out.println("Filozof " + ID + " ma prawy widelec.");
//					 while (true) {
//					 }
					 try {
							Thread.currentThread();
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			break;
		}
//		while (true) {
//			System.out.println("Watek" + ID);
//		}
	}

	public void run() {
		getForks(ID);
	}

	public static void philosopherStart(int ID) {
		new Thread(new Philosophers(ID)).start();
	}

	public static void main(String[] args) {
		philosopherStart(5);
		philosopherStart(1);
		philosopherStart(2);
		philosopherStart(3);
		philosopherStart(4);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
