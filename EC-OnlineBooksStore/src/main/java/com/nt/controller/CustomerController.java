package com.nt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Iservice.CustomerService;
import com.nt.entity.Customer;
import com.nt.model.ResponseMessage;
import com.nt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Create User Custmers",description = "e commerece online books store  register the users")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "user register successfully"),
     @ApiResponse(responseCode = "400",description = "user register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
    @PostMapping("/customersave")
    public ResponseEntity<ResponseMessage> createCustomers(@RequestBody Customer customer) {
        try {
            if (customer.getEmail() == null || customer.getEmail().isEmpty() ||
                customer.getName() == null || customer.getName().isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage(
                                HttpStatus.BAD_REQUEST.value(),
                                Constants.FAILED,
                                "Email & name cannot be empty"));
            }

            Customer savedCustomer = customerService.insertCustomer(customer);

            if (savedCustomer != null) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(new ResponseMessage(
                                HttpStatus.CREATED.value(),
                                Constants.SUCCESS,
                                "Customer saved successfully",
                                savedCustomer));
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage(
                                HttpStatus.BAD_REQUEST.value(),
                                Constants.FAILED,
                                "Customer save failed"));
            }

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            Constants.FAILED,
                            "Internal server error: " + e.getMessage()));
        }
    }
    
    @PutMapping("/customerupdate")
    public ResponseEntity<ResponseMessage> customerUpdate(@RequestBody Customer customer){
    	try {
    		if(customer.getEmail()==null || customer.getEmail().isEmpty() || customer.getName()==null || customer.getName().isEmpty()) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"email & name cannot be empty"));
    		}
    		if(customer.getId()==null) {
    			Customer insertCustomers = customerService.insertCustomer(customer);
    			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"customer update successfully",insertCustomers));
    		}else {
    			Customer updateCustomers =customerService.UpdatesCustomers(customer); 
    			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"customer updated successfully",updateCustomers));
    		}
    	}catch(Exception e) {
    		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILED,"Internal Server error"));
    	}
    }
    
    @PostMapping("/customersaveOrupdate")
    public ResponseEntity<ResponseMessage> customerSaveOrUpdate(@RequestBody Customer customer){
    	try {
    		if(customer.getEmail()==null || customer.getEmail().isEmpty() || customer.getName()==null || customer.getName().isEmpty()) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"email & name cannot be empty"));
    		}
    		if(customer.getId()==null) {
    			Customer insertCustomer = customerService.insertCustomer(customer);
    			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"custmer updated successfully",insertCustomer));
    			}else {
    			
    			Customer createdOrUpdatessCustomers = customerService.createdOrUpdatessCustomers(customer);
    			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"customer saved successfully",createdOrUpdatessCustomers));
    		}
    	}catch(Exception e) {
    		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILED, "Internal server error"));
    	}
    }
    
    @GetMapping("/getByCustmerId/{id}")
    public ResponseEntity<ResponseMessage> customerORUpdates(@PathVariable Long id){
    	
    	Customer byCustomersId = customerService.getByCustomersId(id);
    	if(byCustomersId!=null) {
    		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"customer id getting successfully",byCustomersId));
    	}else {
    		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer id getting Failed", byCustomersId));
    	}
    }
    
    @GetMapping("/getAllCustomers")
    public ResponseEntity<ResponseMessage> getAllCustomer(){
    	List<Customer> byAllCustomers = customerService.getByAllCustomers();
    	if(byAllCustomers!=null) {
    		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"customer getting all successfully",byAllCustomers));
    	}else {
    		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"customer id getting Failed",byAllCustomers));
    	}
    }
    
    @GetMapping("/getAllCustmerswithpagination")
    public ResponseEntity<ResponseMessage> getByAllCustomerpagination(@RequestParam int page,@RequestParam int size,@RequestParam String sortField,@RequestParam String pageDir){
    	Page<Customer> byAllCustomerPaga=customerService.getByAllCustomersWithPaginations(page,size,sortField,pageDir);
    	
    	if(byAllCustomerPaga!=null) {
    		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"All Customers getting with pagination successfully",byAllCustomerPaga));
    	}else {
    		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"All Customers Fail to get pagination",byAllCustomerPaga));
    	}
    }
}
