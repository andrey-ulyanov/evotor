package org.demo.evotor.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name = Customer.TABLE)
public class Customer implements Serializable, Cloneable {

	private static final long serialVersionUID = -4998424914834498657L;

	/* Class */

	public static final String TABLE = "CUSTOMER";
	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_VERSION = "VER";
	public static final String COLUMN_TIMESTAMP = "TM";
	public static final String COLUMN_ACTIVE_AFTER = "ACTIVE_AFTER";
	public static final String COLUMN_ACTIVE_UNTIL = "ACTIVE_UNTIL";
	public static final String COLUMN_LOGIN = "LOGIN";
	public static final String COLUMN_PASSWORD = "PASSWORD";

	/* Instance */

	@Id
	@Column(name = COLUMN_ID)
	protected Long id;

	@Version
	@Column(name = COLUMN_VERSION)
	protected Integer version;

	@Column(name = COLUMN_TIMESTAMP)
	protected Date timestamp;

	@Column(name = COLUMN_ACTIVE_AFTER)
	protected Date activeAfter;

	@Column(name = COLUMN_ACTIVE_UNTIL)
	protected Date activeUntil;

	@Column(name = COLUMN_LOGIN)
	protected String login;

	@Column(name = COLUMN_PASSWORD)
	protected String password;

	/* Relations */

	protected transient CustomerAccount customerAccount;

	/**
	 * Default
	 */
	public Customer() {
		super();
		this.customerAccount = null;
	}

	/**
	 * Clone
	 * 
	 * @param another
	 */
	public Customer(Customer another) {
		this();
		this.id = another.id;
		this.version = another.version;
		this.timestamp = another.timestamp;
		this.activeAfter = another.activeAfter;
		this.activeUntil = another.activeUntil;
		this.login = another.login;
		this.password = another.password;
	}

	/* ***** Get & Set ***** */

	public Long getId() {
		return id;
	}

	public Customer setId(Long id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public Customer setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Customer setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public Date getActiveAfter() {
		return activeAfter;
	}

	public Customer setActiveAfter(Date activeAfter) {
		this.activeAfter = activeAfter;
		return this;
	}

	public Date getActiveUntil() {
		return activeUntil;
	}

	public Customer setActiveUntil(Date activeUntil) {
		this.activeUntil = activeUntil;
		return this;
	}

	public String getLogin() {
		return login;
	}

	public Customer setLogin(String login) {
		this.login = login;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Customer setPassword(String password) {
		this.password = password;
		return this;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public Customer setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
		return this;
	}

	/* ***** Override ***** */

	@Override
	public String toString() {
		return "Customer [id=" + id + ", version=" + version + ", timestamp=" + timestamp + ", activeAfter="
				+ activeAfter + ", activeUntil=" + activeUntil + ", login=" + login + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public Customer clone() throws CloneNotSupportedException {
		return new Customer(this);
	}

}
