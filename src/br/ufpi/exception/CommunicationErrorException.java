package br.ufpi.exception;

public class CommunicationErrorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6907805686991333779L;

	public CommunicationErrorException(String error) {
		super(error);
	}
}
