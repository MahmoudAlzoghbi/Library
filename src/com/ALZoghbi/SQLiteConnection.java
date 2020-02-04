package com.ALZoghbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
	
public static Connection testConnection() {
		
		String url = "jdbc:sqlite:D:\\SQLiteDatabaseBrowserPortable\\Data\\Library.db";
		
		Connection con = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
			
			//System.out.println("Connection Was Established");
			
		}catch (SQLException e) {
			System.out.println( e.getMessage());
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
