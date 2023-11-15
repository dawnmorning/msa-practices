package com.poscodx.emaillist.dto;


public class JsonResult {
	private String result;
	private Object data;
	private String message;

	private JsonResult() {

	}

	public String getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	private JsonResult(Object data) {
		this.result = "success";
		this.data = data;
	}

	public static JsonResult success(Object data) {
		return new JsonResult(data);
	}
	
	private JsonResult(String message) {
		this.result = "fail";
		this.message = message;
	}
	
	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}

}
