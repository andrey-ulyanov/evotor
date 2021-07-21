package org.demo.evotor.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
@JsonSerialize
public class Balance implements Serializable, IsExtras {

	private static final long serialVersionUID = -7792628281635548282L;

	/* Class */

	/**
	 * Make decimal value from minor currency amount.
	 * 
	 * @param balance
	 * @param point
	 * @return
	 */
	public static BigDecimal formatBalance(int balance, int point) {
		BigDecimal divider = new BigDecimal(Math.pow(10, point));
		BigDecimal result = new BigDecimal(balance).divide(divider, point, RoundingMode.HALF_UP);
		return result;
	}

	/* Instance */

	protected BigDecimal balance;

	/**
	 * 
	 * @param customerAccount
	 */
	public Balance(int balance, int point) {
		super();
		this.balance = formatBalance(balance, point);
	}

	/* ***** Get & Set ***** */

	public BigDecimal getBalance() {
		return balance;
	}

	/* ***** Override ***** */

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
