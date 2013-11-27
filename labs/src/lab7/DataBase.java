/**
 * 
 */
package lab7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lab1.Pesel;
import lab4.PracownikEtatowy;

/**
 * @author wukat
 * 
 */
public class DataBase {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(
                            "jdbc:mysql://mysql.agh.edu.pl/wkasper", "wkasper",
                            "epvexFoe");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printDB() {
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM books");
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
		} catch (SQLException ex) {
			
		} finally {
			// zwalniamy zasoby, które nie będą potrzebne
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}
		}
	}
	
	public Boolean findByISBN(String ISBN) {
		int counter = 0;
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM books WHERE ISBN='" + ISBN + "'");
			while (rs.next()) {
				counter++;
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
		} catch (SQLException ex) {
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}	
		}
		return counter > 0;
	}
	
	public void deleteByISBN(String ISBN) {
		try {
			connect();
			stmt = conn.createStatement();

			stmt.executeUpdate("DELETE FROM books WHERE ISBN='" + ISBN + "'");

		} catch (SQLException ex) {
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}	
		}
	}
	
	public Boolean findByAuthor(String author) {
		int counter = 0;
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM books WHERE author='" + author + "'");
			while (rs.next()) {
				counter++;
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
		} catch (SQLException ex) {
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}	
		}
		return counter > 0;
	}

	public void deleteByAuthor(String author) {
		try {
			connect();
			stmt = conn.createStatement();

			stmt.executeUpdate("DELETE FROM books WHERE author='" + author + "'");

		} catch (SQLException ex) {
			// handle any errors
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}	
		}
	}
	
	public void createTable() throws SQLException {
		connect();
	    stmt = conn.createStatement();
	    stmt.executeUpdate(
	            "CREATE TABLE IF NOT EXISTS Pracownicy ("
	            + "pesel VARCHAR(11) NOT NULL, "
	            + "wynagrodzenie DOUBLE, PRIMARY KEY (pesel))");
	  }
	
	public void addEmpleyee(Pesel pesel, double wynagrodzenie) {
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO `Pracownicy` VALUES (" + pesel.toString() + "," + wynagrodzenie + ")");
		} catch (SQLException ex) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}	
		}		
	}
	
	public String findEmployee(Pesel pesel) {
		String result = "";
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Pracownicy WHERE pesel='" + pesel.toString() + "'");
			while (rs.next()) {
				result = result + rs.getString(1) + " | " + rs.getString(2) + "\n";
				System.out.println(rs.getString(1) + " | " + rs.getString(2));
			}
		} catch (SQLException ex) {
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}	
		}
		return result;
	}
	
	public void removeEmployee(Pesel pesel) {
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM Pracownicy WHERE pesel='" + pesel.toString() + "'");

		} catch (SQLException ex) {
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}	
		}
	}
	
	public void setValue(Pesel pesel, double wyn) {
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE Pracownicy SET wynagrodzenie = " + wyn + " WHERE pesel='" + pesel.toString() + "'");

		} catch (SQLException ex) {
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}	
		}
	}
	
	public void printAllEmpleoyees() {
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Pracownicy");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "   " + rs.getString(2));
			}
		} catch (SQLException ex) {
			
		} finally {
			// zwalniamy zasoby, które nie będą potrzebne
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} 

				stmt = null;
			}
		}
	}
	
	static public void main(String[] args) {
		DataBase db = new DataBase();
		db.printDB();
		System.out.println("\n\n\n");
		db.findByAuthor("Marcel Proust");
		System.out.println("\n\n\n");
		db.findByISBN("1234567891234");
		
		try {db.createTable();}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
		Kadry pracownicy = new Kadry();
		pracownicy.dodajPracownika(new PracownikEtatowy(new Pesel("93070301541"), 1043));
		pracownicy.dodajPracownika(new PracownikEtatowy(new Pesel("93070301515"), 1057.43));
		pracownicy.znajdz(new Pesel("93070301513"));
		pracownicy.setWynagrodzenieBrutto(new Pesel("93070301541"), 9999);
		pracownicy.wyswietlWysztkich();
	}
}
