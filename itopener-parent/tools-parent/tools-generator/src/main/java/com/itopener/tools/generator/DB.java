package com.itopener.tools.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {
	
	private Connection conn;
	
	public Connection getConn(String className, String url, String user, String password) throws SQLException, ClassNotFoundException{
		Class.forName(className);
		return conn = DriverManager.getConnection(url, user, password);
	}
	
	public void close() throws SQLException{
		if(conn != null){
			conn.close();
		}
	}
}
