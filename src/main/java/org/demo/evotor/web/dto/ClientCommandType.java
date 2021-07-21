package org.demo.evotor.web.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
@JsonSerialize
public enum ClientCommandType {

	CREATE("create"), GET_BALANCE("get-balance");

	private String type;

	private ClientCommandType(String type) {
		this.type = type;
	}

	@JsonValue
	public String getType() {
		return type;
	}
}
