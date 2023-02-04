package controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbController {
	
	private String dbname="java-chatApp";
	private String username="root";
	private String password="";
	
	public Connection dblink;
	
	
	public Connection getConnect() {
		String url="jdbc:mysql://localhost/"+dbname;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dblink=DriverManager.getConnection(url,username,password);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dblink;
	}
}
