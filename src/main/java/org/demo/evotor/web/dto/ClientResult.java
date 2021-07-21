package org.demo.evotor.web.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
@JsonSerialize
public class ClientResult implements Serializable {

	private static final long serialVersionUID = -7646523739717537366L;

	protected ClientResultCode result;
	protected Balance balance;

	public ClientResult(ClientResultCode result) {
		super();
		this.result = result;
	}

	public ClientResultCode getResult() {
		return result;
	}
	
	@JsonProperty("extras")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	public Balance getBalance() {
		return balance;
	}

	public ClientResult setBalance(Balance balance) {
		this.balance = balance;
		return this;
	}

	@Override
	public String toString() {
		return "ClientResult [result=" + result + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
		ClientResult other = (ClientResult) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}

}
