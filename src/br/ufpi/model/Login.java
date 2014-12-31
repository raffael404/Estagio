package br.ufpi.model;

public class Login {
	private String OCSServer, OCSUser, OCSPassword, MyServer, MyUser, MyPassword;

	/**
	 * @param OCSServer
	 * @param OCSUser
	 * @param OCSPassword
	 * @param MyServer
	 * @param MyUser
	 * @param MyPassword
	 */
	public Login(String OCSServer, String OCSUser, String OCSPassword,
			String MyServer, String MyUser, String MyPassword) {
		this.OCSServer = OCSServer;
		this.OCSUser = OCSUser;
		this.OCSPassword = OCSPassword;
		this.MyServer = MyServer;
		this.MyUser = MyUser;
		this.MyPassword = MyPassword;
	}

	/**
	 * @return the OCSServer
	 */
	public String getOCSServer() {
		return OCSServer;
	}

	/**
	 * @return the OCSUser
	 */
	public String getOCSUser() {
		return OCSUser;
	}

	/**
	 * @return the OCSPassword
	 */
	public String getOCSPassword() {
		return OCSPassword;
	}

	/**
	 * @return the MyServer
	 */
	public String getMyServer() {
		return MyServer;
	}

	/**
	 * @return the MyUser
	 */
	public String getMyUser() {
		return MyUser;
	}

	/**
	 * @return the MyPassword
	 */
	public String getMyPassword() {
		return MyPassword;
	}
	
}
