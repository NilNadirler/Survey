package com.example.survey.Results;

public class Result {
	
	private boolean success;
	private String message;
	private int id;
	
	public Result(boolean success) {
		this.success = success;
	}

	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}
	
	public Result(boolean success, String message,int id) {
		this(success);
		this.message = message;
		this.id = id;
	}

	public Result(boolean success, int id) {
		this(success);
		this.id = id;
	}

	public boolean isSuccess() {
		return this.success;
	}
	public String getMessage() {
		return this.message;
	}
	public int getId() {
		return this.id;
	}
}
