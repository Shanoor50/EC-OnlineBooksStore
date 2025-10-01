package com.nt.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Iservice.UserRegisterService;
import com.nt.entity.UserRegister;
import com.nt.model.ResponseMessage;
import com.nt.model.UserRequestsDTO;
import com.nt.utility.Constants;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User Registration", description = "APIs for user registration in online bookstore")
@OpenAPIDefinition(
	    info = @Info(
	        title = "EC-OnlineBooksStore",
	        version = "1.0",
	        description = "API documentation for EC-OnlineBooksStore project"
	    )
	)
public class UserRegisterController {

    @Autowired
    private UserRegisterService userSer;

    @Operation(summary = "Create a new user", description = "Register a new user in the online bookstore")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/userregisters")
    public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody UserRequestsDTO userRequestsDTO) {
    	try {
    		if(userRequestsDTO.getEmail()==null || userRequestsDTO.getEmail().isEmpty() || userRequestsDTO.getPassword()==null || userRequestsDTO.getPassword().isEmpty()) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"email and password cannot be empty"));
    		}
    		 UserRegister insert=userSer.insertUserRegister(userRequestsDTO);
    		 if(insert!=null) {
    	    		//return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Online bookstore saved successfully",insert));

    			 return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.Http_CREATED,Constants.SUCCESS,"online bookstore save successfully",insert));
    		 }
    		 else {
    			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"User Register Failed",insert));
    		 }
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILED,"Internal server error"));
    	}
        
    }
}
