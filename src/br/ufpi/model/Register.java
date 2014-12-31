package br.ufpi.model;


public class Register {
	private int id;
	private String tag;
	private String user;
	private String ip;
	private String software;
	
	/**
	 * @param id
	 * @param tag
	 * @param user
	 * @param ip
	 * @param software
	 */
	public Register(int id, String tag, String user, String ip, String software) {
		this.id = id;
		this.tag = tag;
		this.user = user;
		this.ip = ip;
		this.software = software;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @return the software
	 */
	public String getSoftware() {
		return software;
	}
	
}
