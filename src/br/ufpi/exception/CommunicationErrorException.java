package br.ufpi.exception;

/**
 * @author Rafael
 *
 */
public class CommunicationErrorException extends Exception{
	
	private static final long serialVersionUID = -6907805686991333779L;
	
	/**
	 * @param error
	 */
	public CommunicationErrorException(String error) {
		super(error);
	}
}
