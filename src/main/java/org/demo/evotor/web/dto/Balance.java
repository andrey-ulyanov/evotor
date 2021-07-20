package org.demo.evotor.web.dto;

import java.io.Serializable;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public class Balance implements Serializable, Extras {

	private static final long serialVersionUID = -7792628281635548282L;

	protected String balance;

	public Balance(String balance) {
		super();
		this.balance = balance;
	}

	public String getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Balance [balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
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
		Balance other = (Balance) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		return true;
	}

}
