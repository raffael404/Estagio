package br.ufpi.exception;

public class DatabaseConnectionException extends Exception{
	public DatabaseConnectionException(String error) {
		super(error);
	}
}
