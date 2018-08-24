package com.danielpm1982.helloWorldJPA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

//Must create only 'scheme1' manually. The tables are created and destroyed programmatically.

public class Main {
	private static String selectName = "SELECT * FROM Client c WHERE c.name LIKE ?";
	private static String nameToSearch = null;
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	public static void main(String[] args) {
		try {
			start();
		} catch (Exception e) {
			System.out.println("\n-------------------------------------------------");
			System.out.println("A "+e.getClass().getSimpleName()+" has occurred!");
			System.out.println(e.getMessage()!=null?e.getMessage():"");
			System.out.println("-------------------------------------------------");
		}
	}
	public static void start() throws NullPointerException{
		finalizeDB(); //in case previous DB exists.
		initializeDB();
		nameToSearch = JOptionPane.showInputDialog(null, "Type a name to search at DB. Or nothing to search ALL:", "MyConnector", JOptionPane.INFORMATION_MESSAGE).toLowerCase();
		System.out.println("Starting DB search...");
		if(nameToSearch!=null) {
			System.out.println("Searching for: "+(nameToSearch.isEmpty()?"all registries":nameToSearch)+"...");
			searchName();
		} else {
			throw new NullPointerException("Error! Variable nameToSearch's value is null!");
		}
		finalizeDB();
		System.out.println("Program ended!");
	}
	private static void initializeDB() {
		connection = MyConnector.getConnection();
		try {
			Statement statement = connection.createStatement();
			System.out.println("Creating table...");
			String sql = "CREATE TABLE Client " +
	                   "(id INTEGER NOT NULL AUTO_INCREMENT, " +
	                   " name VARCHAR(255), " + 
	                   " PRIMARY KEY ( id ))"; 
			statement.executeUpdate(sql);
			System.out.println("Table created!");
			System.out.println("Truncating table...");
			statement.executeUpdate("TRUNCATE TABLE Client");
			System.out.println("Truncated!");
			System.out.println("Inserting registries..."); //inserting sample values!!!!!
			statement.executeUpdate("INSERT INTO Client(name) values('client1')");
			statement.executeUpdate("INSERT INTO Client(name) values('client2')");
			statement.executeUpdate("INSERT INTO Client(name) values('client3')");
			statement.executeUpdate("INSERT INTO Client(name) values('client4')");
			statement.executeUpdate("INSERT INTO Client(name) values('client5')");
			System.out.println("Inserted!");
			System.out.println("DB initialized and populated!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(statement!=null&&!statement.isClosed()) {
					statement.close();
				}
				if(connection!=null&&!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private static void finalizeDB() {
		connection = MyConnector.getConnection();
		try {
			Statement statement = connection.createStatement();
			System.out.println("Destroying database...");
			String sql = "DROP TABLE if exists Client"; 
			statement.executeUpdate(sql);
			System.out.println("Table dropped!");
			System.out.println("DB Destroyed!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(statement!=null&&!statement.isClosed()) {
					statement.close();
				}
				if(connection!=null&&!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private static void searchName() {
		connection = MyConnector.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement(selectName, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			preparedStatement.setString(1, "%"+nameToSearch+"%");
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				System.out.println("id: "+rs.getInt(1)+" name: "+rs.getString(2));
			}
			rs.last();
			System.out.println(rs.getRow()+" registry(ies) found!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement!=null&&!preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if(connection!=null&&!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
