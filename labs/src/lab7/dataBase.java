/**
 * 
 */
package lab7;

import java.sql.*;

/**
 * @author wukat
 * 
 */
public class dataBase {
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
	            "CREATE TABLE Pracownicy ("
	            + "pesel VARCHAR(11) NOT NULL, "
	            + "nazwisko VARCHAR(64), PRIMARY KEY (pesel))");
	  }
	
	public void addEmpleyee() {
		connect();	
	}
	
	static public void main(String[] args) {
		dataBase db = new dataBase();
		db.printDB();
	}
}
