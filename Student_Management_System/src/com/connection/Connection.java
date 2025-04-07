package com.connection;

import java.sql.DriverManager;

public class Connection {
	public java.sql.Connection getConnection(){
		String url="jdbc:mysql://localhost:3306/sms";
		String uName="root";
		String uPsw="";
		java.sql.Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, uName, uPsw);
			if(con==null) {
				System.out.println("not connected");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
