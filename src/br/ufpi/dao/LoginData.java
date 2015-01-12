package br.ufpi.dao;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.ufpi.exception.CommunicationErrorException;
import br.ufpi.model.Login;

public class LoginData {
	
	public static void save(Login login) throws CommunicationErrorException{
		try {
			BufferedWriter outFile = new BufferedWriter(new FileWriter("loginData.tmp"));
			outFile.write(login.getServer() + ";" + login.getUser() + ";");
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommunicationErrorException("Erro ao salvar dados de login!");
		}
	}
	
	public static Login charge() throws CommunicationErrorException{
		try {
			FileReader fr = new FileReader("loginData.tmp");
			Scanner scanner = new Scanner(fr).useDelimiter(";");
			String loginTmp[] = new String[2];
			int i = 0;
			while (scanner.hasNext()) {
				loginTmp[i] = scanner.next();
				i++;
			}
			Login login = new Login(loginTmp[0], loginTmp[1]);
			fr.close();
			scanner.close();
			return login;
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommunicationErrorException("Erro ao carregar dados de login!");
		}
	}
	
}
