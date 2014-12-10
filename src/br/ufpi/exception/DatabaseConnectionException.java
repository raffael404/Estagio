package br.ufpi.exception;

public class DatabaseConnectionException extends Exception{
	public DatabaseConnectionException() {
		super("Connection error!");
	}
}
