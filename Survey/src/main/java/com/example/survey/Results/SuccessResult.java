package com.example.survey.Results;

public class SuccessResult extends Result {

	public SuccessResult() {
		super(true);
	}
	public SuccessResult(String message) {
		super(true,message);
	}
	public SuccessResult(String message, int id) {
		super(true,message,id);
		
	}
	public SuccessResult(int id) {
		super(true,id);
		
	}
}
