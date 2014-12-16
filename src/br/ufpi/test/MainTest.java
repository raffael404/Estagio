package br.ufpi.test;

import br.ufpi.dao.DatabaseInteraction;
import br.ufpi.exception.DatabaseConnectionException;

public class MainTest {
	
	public static void main(String[] args) {
		try {
			DatabaseInteraction database = new DatabaseInteraction("10.20.15.164", "ocsweb", "root", "internship", "localhost", "softwares", "root", "root");
		} catch (DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Funcionou!!");
	}
	
}
