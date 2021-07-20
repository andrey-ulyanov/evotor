package org.demo.evotor.domain;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public interface IsDomain {

	/**
	 * 
	 * @return
	 */
	default Long generateID() {
		Long id = Calendar.getInstance().getTimeInMillis();
		;
		id <<= 31;
		id |= id + Math.abs(this.hashCode());
		return id;
	}

	public Long getId();

	public IsDomain setId(Long id);

	public Integer getVersion();

	public IsDomain setVersion(Integer version);

	public Date getTimestamp();

	public IsDomain setTimestamp(Date timestamp);

}
