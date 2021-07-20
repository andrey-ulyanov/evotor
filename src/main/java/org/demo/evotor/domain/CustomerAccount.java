package org.demo.evotor.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name = CustomerAccount.TABLE)
public class CustomerAccount implements Serializable, Cloneable {

	private static final long serialVersionUID = 6856048406132837155L;

	/* Class */

	public static final String TABLE = "CUSTOMER_ACCOUNT";
	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_VERSION = "VER";
	public static final String COLUMN_TIMESTAMP = "TM";
	public static final String COLUMN_CURRENCY = "CURRENCY";
	public static final String COLUMN_BALANCE = "BALANCE";

	/**
	 * 
	 */
	public static final BiConsumer<CustomerAccount, Map<String, Object>> MAPPER = (CustomerAccount domain,
			Map<String, Object> map) -> {
		map.put(CustomerAccount.COLUMN_ID, domain.id);
		map.put(CustomerAccount.COLUMN_VERSION, domain.version);
		map.put(CustomerAccount.COLUMN_TIMESTAMP, domain.timestamp);
		map.put(CustomerAccount.COLUMN_CURRENCY, domain.currency);
		map.put(CustomerAccount.COLUMN_BALANCE, domain.balance);
	};

	/**
	 * 
	 */
	public static final BiConsumer<Map<String, Object>, CustomerAccount> UNMAPPER = (Map<String, Object> map,
			CustomerAccount domain) -> {
		domain.id = (Long) map.get(CustomerAccount.COLUMN_ID);
		domain.version = (Integer) map.get(CustomerAccount.COLUMN_VERSION);
		domain.timestamp = (Date) map.get(CustomerAccount.COLUMN_TIMESTAMP);
		domain.currency = (Currency) map.get(CustomerAccount.COLUMN_CURRENCY);
		domain.balance = (Integer) map.get(CustomerAccount.COLUMN_BALANCE);
	};

	/* Instance */

	@Id
	@Column(name = COLUMN_ID)
	protected Long id;

	@Version
	@Column(name = COLUMN_VERSION)
	protected Integer version;

	@Column(name = COLUMN_TIMESTAMP)
	protected Date timestamp;

	@Column(name = COLUMN_TIMESTAMP)
	protected Currency currency;

	@Column(name = COLUMN_TIMESTAMP)
	protected Integer balance;

	/**
	 * Default
	 */
	public CustomerAccount() {
		super();
	}

	/* ***** Get & Set ***** */

	public Long getId() {
		return id;
	}

	public CustomerAccount setId(Long id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public CustomerAccount setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public CustomerAccount setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public Currency getCurrency() {
		return currency;
	}

	public CustomerAccount setCurrency(Currency currency) {
		this.currency = currency;
		return this;
	}

	public Integer getBalance() {
		return balance;
	}

	public CustomerAccount setBalance(Integer balance) {
		this.balance = balance;
		return this;
	}

	/* ***** Override ***** */

	@Override
	public String toString() {
		return "Account [id=" + id + ", version=" + version + ", timestamp=" + timestamp + ", currency=" + currency
				+ ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		CustomerAccount other = (CustomerAccount) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (currency != other.currency)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
