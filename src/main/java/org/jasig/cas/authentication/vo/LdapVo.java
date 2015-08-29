package org.jasig.cas.authentication.vo;

import java.io.Serializable;

public class LdapVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;
	private String baseDN;
	private String userName;
	private String password;
	
	public LdapVo(){
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBaseDN() {
		return baseDN;
	}

	public void setBaseDN(String baseDN) {
		this.baseDN = baseDN;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
