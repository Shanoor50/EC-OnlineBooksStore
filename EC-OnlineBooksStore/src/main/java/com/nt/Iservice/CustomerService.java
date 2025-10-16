package com.nt.Iservice;

import com.nt.entity.Customer;

public interface CustomerService {
	
	public Customer insertCustomer(Customer customer);
	
	public Customer UpdatesCustomers(Customer customer);
	
	public Customer createdOrUpdatessCustomers(Customer customer);
	
	public Customer getByCustomersId(Long id);

}
