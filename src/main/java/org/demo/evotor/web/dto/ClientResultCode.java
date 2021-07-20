package org.demo.evotor.web.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public enum ClientResultCode {

	OK(0, "OK"), EXIST(1, "User already exist"), ERROR(2, "System error"), NOT_EXIST(3, "User is not exist"),
	AUTH_FAILED(4, "Password not valid");

	private Integer code;
	private String description;

	private ClientResultCode(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	@JsonValue
    public int toCode() {
        return code;
    }
	
}
