package com.danielpm1982.helloWorldJPA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {
	private String dbms = "mysql";
	private String serverName = "localhost";
	private String portNumber = "3306";
	private String scheme = "scheme1";
	private String userName= "root";
	private String password= "root";
	private Connection connect() throws SQLException{
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", this.userName);
		connectionProperties.put("password", this.password);
		Connection connection = DriverManager.getConnection("jdbc:"+dbms+"://"+serverName+":"+portNumber+"/"+scheme+"?useSSL=false", connectionProperties);
		return connection;
	}
	public static Connection getConnection(){
		try {
			return new MyConnector().connect();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
