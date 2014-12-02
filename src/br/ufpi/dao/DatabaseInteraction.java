package br.ufpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInteraction {
	
	private Connection OCSConnection;
	private List<String> softwares;
	
	public DatabaseInteraction(String OCSServerName, String OCSDatabaseName, String OCSUserName, String OCSPassword,
			String MyServerName, String MyDatabaseName, String MyUserName, String MyPassword, String MyTableName) {
		softwares = new ArrayList<String>();
		Connection myConnection = getMySQLConnection(MyServerName, MyDatabaseName, MyUserName, MyPassword);
		try {
			Statement st = myConnection.createStatement();
			ResultSet rs = st.executeQuery("select * from " + MyTableName + ";");
			while (rs.next()) {
				softwares.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(softwares.get(0));
		//OCSConnection = getMySQLConnection(OCSServerName, OCSDatabaseName, OCSUserName, OCSPassword);
	}
	
	private Connection getMySQLConnection(String serverName, String databaseName, String userName, String password){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + databaseName, userName, password);
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado!");
			return null;
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar ao Banco de Dados!");
			return null;
		}
	}
	
}
