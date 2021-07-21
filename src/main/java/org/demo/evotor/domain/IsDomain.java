package org.demo.evotor.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public interface IsDomain {
	
	public static final String DEFUALT_HASH_TYPE = "SHA-256";

	/**
	 * 
	 * @return
	 */
	default Long generateID() {
		Long id = Calendar.getInstance().getTimeInMillis();
		id <<= 31;
		id |= id + Math.abs(this.hashCode());
		return id;
	}
	
	default String formatPassword(String passwordClear) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(DEFUALT_HASH_TYPE);
		String passwordHash = Base64.getEncoder().encodeToString(digest.digest(passwordClear.getBytes()));
		return passwordHash;
	}

	public Long getId();

	public IsDomain setId(Long id);

	public Integer getVersion();

	public IsDomain setVersion(Integer version);

	public Date getTimestamp();

	public IsDomain setTimestamp(Date timestamp);

}
