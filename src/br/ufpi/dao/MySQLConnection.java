package br.ufpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
//	private static String status;
	
	public static Connection getMySQLConnection(String serverName, String databaseName, String userName, String password){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + databaseName, userName, password);
//			if (connection == null)
//				status = "Conectado";
//			else
//				status = "N�o foi poss�vel realizar a conex�o";
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver n�o encontrado!");
			return null;
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel conectar ao Banco de Dados!");
			return null;
		}
	}
	
//	public static String getConnectionStatus(){
//		return status;
//	}
	
}
