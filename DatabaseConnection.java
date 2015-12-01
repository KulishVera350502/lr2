package app;

import java.sql.*;

public class DatabaseConnection {
	
	protected Connection connection = dbConnector();
	
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:d:\\”˜Â·‡\\“–Ë“œŒ\\lr2\\lr2\\tvshows.sqlite");
			System.out.println(connection + "success!");
			return connection;
		}
		catch(Exception e) {
			return null;
		}
	}

}
