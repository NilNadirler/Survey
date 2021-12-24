package com.example.survey.Results;

public class ErrorResult extends Result {

	public ErrorResult() {
		super(false);
		
	}
	public ErrorResult(String message) {
		super(false,message);
		
	}
	public ErrorResult(String message, int id) {
		super(false,message,id);
		
	}
	public ErrorResult(int id) {
		super(false,id);
		
	}
}
