package lab1;

import java.util.Arrays;

/**
 * @class Pesel - implementation of pesel number
 * @author wukat
 * 
 */
public class Pesel {
	private int[] pesel = null; // int array pesel

	/**
	 * @brief Constructor
	 * @param PESEL
	 *            - number as string
	 */
	public Pesel(String PESEL) {
		pesel = new int[11];
		for (int i = 0; i < PESEL.length(); i++) {
			if (PESEL.charAt(i) >= '0' && PESEL.charAt(i) <= '9')
				pesel[i] = PESEL.charAt(i) - '0';
			else
				pesel[i] = -1;
			if (PESEL.length() < 11)
				pesel[0] = -1;
		}
	}

	/**
	 * @brief Checks if all numbers are OK (0-9)
	 * @return true if are.
	 */
	public boolean checkNumbers() {
		boolean flag = true;
		for (int i = 0; i < 11 && flag; i++) {
			if (pesel[i] == -1)
				flag = false;
		}
		return flag;
	}

	/**
	 * @brief Function checks check number of pesel
	 * @return true if it's OK
	 */
	public boolean checkCheckNumber() {
		return (10 - (1 * pesel[0] + 3 * pesel[1] + 7 * pesel[2] + 9 * pesel[3]
				+ 1 * pesel[4] + 3 * pesel[5] + 7 * pesel[6] + 9 * pesel[7] + 1
				* pesel[8] + 3 * pesel[9]) % 10) % 10 == pesel[10];
	}

	/**
	 * @brief Function checks data
	 * @return true if it's proper
	 */
	public boolean checkData() {
		int year = pesel[0] * 10 + pesel[1];
		int month = pesel[2] * 10 + pesel[3];
		int day = pesel[4] * 10 + pesel[5];

		boolean flag = true;
		if (month >= 1 && month <= 12)
			year += 1900;
		else if (month >= 21 && month <= 32) {
			month -= 20;
			year += 2000;
		} else if (month >= 41 && month <= 52) {
			month -= 40;
			year += 2100;
		} else if (month >= 61 && month <= 72) {
			month -= 60;
			year += 2200;
		} else if (month >= 81 && month <= 92) {
			month -= 80;
			year += 1800;
		} else
			flag = false;

		if (flag && day > 0 && day <= 31) {
			if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day > 30)
				flag = false;
			else if ((month == 2) && (day > 29))
				flag = false;
			else if ((month == 2) && (day == 29))
				if (!((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)))
					flag = false;
		} else
			flag = false;
		return flag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesel other = (Pesel) obj;
		if (!Arrays.equals(pesel, other.pesel))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pesel [pesel=" + Arrays.toString(pesel) + "]";
	}
	
	
}
