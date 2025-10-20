package com.nt.Iservice;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nt.entity.Customer;

public interface CustomerService {
	
	public Customer insertCustomer(Customer customer);
	
	public Customer UpdatesCustomers(Customer customer);
	
	public Customer createdOrUpdatessCustomers(Customer customer);
	
	public Customer getByCustomersId(Long id);
	
	public List<Customer> getByAllCustomers();
	
	public Page<Customer> getByAllCustomersWithPaginations(int page,int size,String sortField,String pageDir);

}
