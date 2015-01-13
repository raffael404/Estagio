package br.ufpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufpi.exception.CommunicationErrorException;
import br.ufpi.model.Register;
import br.ufpi.util.SortRegisters;

public class DatabaseInteraction {
	
	private Connection OCSConnection;
	private Connection myConnection;
	
	public DatabaseInteraction(String OCSServerName, String OCSDatabaseName, String OCSUserName, String OCSPassword,
			String MyServerName, String MyDatabaseName, String MyUserName, String MyPassword) throws CommunicationErrorException {
		myConnection = getMySQLConnection(MyServerName, MyDatabaseName, MyUserName, MyPassword);
		OCSConnection = getMySQLConnection(OCSServerName, OCSDatabaseName, OCSUserName, OCSPassword);
		try {
			Statement st = myConnection.createStatement();
			st.executeUpdate("create table if not exists softwares(software varchar(50) primary key);");
		} catch (SQLException e) {
			throw new CommunicationErrorException("Erro durante a criação das tabelas!");
		}
	}
	
	private Connection getMySQLConnection(String serverName, String databaseName, String userName, String password) throws CommunicationErrorException{
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + databaseName, userName, password);
			return connection;
		} catch (ClassNotFoundException e) {
			throw new CommunicationErrorException("Driver não encontrado!");
		} catch (SQLException e) {
			throw new CommunicationErrorException("Erro de conexão com o Banco de dados " + databaseName + "!");
		}
	}
	
	public List<Register> getRegisters() throws CommunicationErrorException{
		
		List<String> softwares;
		try {
			Statement st = myConnection.createStatement();
			softwares = new ArrayList<String>();
			ResultSet rs = st.executeQuery("select * from softwares;");
			while (rs.next()) {
				softwares.add(rs.getString(1));
			}
			st.close();
		} catch (SQLException e) {
			throw new CommunicationErrorException("Erro de conexão com o seu Banco de Dados!");
		}
		
		try {
			Statement st = OCSConnection.createStatement();
			List<Register> registers = new ArrayList<Register>();
			for (int i = 0; i < softwares.size(); i++) {
//				registers = new ArrayList<Register>();
				ResultSet rs = st.executeQuery("select accountinfo.HARDWARE_ID, TAG, USERID, IPADDR, softwares.NAME "
						+ "from softwares, accountinfo, hardware "
						+ "where softwares.HARDWARE_ID = accountinfo.HARDWARE_ID and hardware.ID = softwares.HARDWARE_ID and softwares.NAME like '%" + softwares.get(i) + "%' group by softwares.HARDWARE_ID;");
				while (rs.next()) {
					Register register = new Register(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
					registers.add(register);						
				}
			}
			st.close();
			SortRegisters.quickSort(registers, 0, registers.size()-1);
			return registers;
		} catch (SQLException e) {
			throw new CommunicationErrorException("Erro de conexão com o Banco de Dados do OCS!");
		}
	}
	
	public List<String> getSoftwares() throws CommunicationErrorException{
		try {
			Statement st = myConnection.createStatement();
			List<String> softwares = new ArrayList<String>();
			ResultSet rs = st.executeQuery("select * from softwares;");
			while (rs.next()) {
				softwares.add(rs.getString(1));
			}
			st.close();
			return softwares;
		} catch (SQLException e) {
			throw new CommunicationErrorException("Erro ao acessar a lista de softwares!");
		}
	}
	
	public void insertSoftware(String software) throws CommunicationErrorException{
		try {
			Statement st = myConnection.createStatement();
			st.executeUpdate("insert into softwares values('" + software + "');");
			st.close();
		} catch (SQLException e) {
			throw new CommunicationErrorException("Erro ao inserir software!");
		}
	}
	
	public void deleteSoftware(String software) throws CommunicationErrorException{
		try {
			Statement st = myConnection.createStatement();
			st.executeUpdate("delete from softwares where software = '" + software + "';");
			st.close();
		} catch (SQLException e) {
			throw new CommunicationErrorException("Erro ao apagar software!");
		}
	}
	
	public void closeConnection() throws CommunicationErrorException{
		try {
			myConnection.close();
			OCSConnection.close();
		} catch (SQLException e) {
			throw new CommunicationErrorException("Erro ao fechar a conexão!");
		}
		
	}
}
