package org.demo.evotor.web.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
@JsonSerialize
public class ClientCommand implements Serializable {

	private static final long serialVersionUID = -4153548614980291396L;

	/* Instance */

	protected ClientCommandType type;
	
	@NotBlank
	@Size(max = 255 )	
	protected String login;
	
	@NotBlank
	@Size(max = 255)
	protected String password;

	/**
	 * Default
	 */
	public ClientCommand() {
		super();
	}

	/**
	 * 
	 * @param type
	 * @param login
	 * @param password
	 */
	public ClientCommand(ClientCommandType type, String login, String password) {
		super();
		this.type = type;
		this.login = login;
		this.password = password;
	}

	/* ***** Get & Set ***** */

	public ClientCommandType getType() {
		return type;
	}

	public ClientCommand setType(ClientCommandType type) {
		this.type = type;
		return this;
	}

	public String getLogin() {
		return login;
	}

	public ClientCommand setLogin(String login) {
		this.login = login;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public ClientCommand setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String toString() {
		return "ClientCommand [type=" + type + ", login=" + login + ", password=" + "*******" + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientCommand other = (ClientCommand) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
