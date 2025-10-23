package com.nt.exception;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nt.model.ErrorResponseMessage;
import com.nt.utility.Constants;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(CustomerIdNotFoundException.class)
	public ResponseEntity<Object> customerHandlerException(CustomerIdNotFoundException ex){
		List<String> details=new ArrayList<>();
		details.add("Error:Customer Id Not Available");
		details.add("Detailed Message:"+ex.getLocalizedMessage());
		details.add("Timestamp:"+System.currentTimeMillis());
		ErrorResponseMessage error=new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILURE,"NOT AVAILABLE",details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookIdNotFoundException.class)
	public ResponseEntity<Object> BookIdNotFoundException(BookIdNotFoundException ex){
		List<String> details=new ArrayList<>();
		details.add("Error:Customer Id Not Available");
		details.add("Detailed Message:"+ex.getLocalizedMessage());
		details.add("Timestamp:"+System.currentTimeMillis());
		ErrorResponseMessage error=new ErrorResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILURE,"NOT AVAILABLE",details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

}
