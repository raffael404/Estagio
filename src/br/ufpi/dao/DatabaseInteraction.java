package br.ufpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufpi.model.Register;

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
		OCSConnection = getMySQLConnection(OCSServerName, OCSDatabaseName, OCSUserName, OCSPassword);
	}
	
	private Connection getMySQLConnection(String serverName, String databaseName, String userName, String password){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + databaseName, userName, password);
			System.out.println("alguma coisa");
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado!");
			return null;
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar ao Banco de Dados!");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Register> getRegisters(){
		try {
			Statement st = OCSConnection.createStatement();
			List<Register> registers = new ArrayList<Register>();
			for (int i = 0; i < softwares.size(); i++) {
				registers = new ArrayList<Register>();
				ResultSet rs = st.executeQuery("select accountinfo.HARDWARE_ID, TAG, USERID, IPADDR, softwares.NAME "
						+ "from softwares, accountinfo, hardware "
						+ "where softwares.HARDWARE_ID = accountinfo.HARDWARE_ID and hardware.ID = softwares.HARDWARE_ID and softwares.NAME like '%" + softwares.get(i) + "%' group by softwares.HARDWARE_ID;");
				while (rs.next()) {
					boolean exists = false;
					for (int j = 0; j < registers.size(); j++) {
						if (registers.get(j).getId() == rs.getInt(1)) {
							registers.get(j).getSoftwares().add(rs.getString(5));
							exists = true;
						}
					}
					if (!exists) {
						Register register = new Register(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
						register.getSoftwares().add(rs.getString(5));
						registers.add(register);						
					}
				}
			}
			return registers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
