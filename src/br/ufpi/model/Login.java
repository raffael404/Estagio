package br.ufpi.model;

/**
 * @author Rafael
 *
 */
public class Login {
	private String server, user;

	/**
	 * @param server
	 * @param user
	 */
	public Login(String server, String user) {
		this.server = server;
		this.user = user;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

}
