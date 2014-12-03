package br.ufpi.test;

import br.ufpi.dao.DatabaseInteraction;

public class MainTest {
	
	public static void main(String[] args) {
		DatabaseInteraction database = new DatabaseInteraction("10.20.15.164", "ocsweb", "root", "internship",
				"localhost", "teste", "root", "root", "software");
		System.out.println("deu certo!");
	}
	
}
