package br.ufpi.exception;

public class DatabaseConnectionException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6907805686991333779L;

	public DatabaseConnectionException(String error) {
		super(error);
	}
}
