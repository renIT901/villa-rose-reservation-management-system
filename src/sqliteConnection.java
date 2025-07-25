import java.sql.*;
import javax.swing.*;

public class sqliteConnection {
	public static Connection dbConnector() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:VillaRoseDB.db");
			//JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
