package com.nt.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Iservice.CustomerService;
import com.nt.entity.Customer;
import com.nt.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired CustomerRepo customerRepo;

	@Override
	public Customer insertCustomer(Customer customer) {
		
		Customer cus=customerRepo.save(customer);
		
		return cus;
	}

	@Override
	public Customer UpdatesCustomers(Customer customer) {
		Customer save = customerRepo.save(customer);
		return save;
	}

	@Override
	public Customer createdOrUpdatessCustomers(Customer customer) {
		if(customer.getId()==null) {
			customerRepo.save(customer);
		}else {
			Optional<Customer> byId = customerRepo.findById(customer.getId());
			if(byId.isPresent()) {
				Customer existData = byId.get();
				existData.setName(customer.getName());
				existData.setEmail(customer.getEmail());
				customerRepo.save(existData);
			}else {
				throw new RuntimeException("Customer Not Found");
			}
		}
		return customer;
	}

	@Override
	public Customer getByCustomersId(Long id) {
		Optional<Customer> byId = customerRepo.findById(id);
		if(!byId.isPresent()) {
			throw new RuntimeException("Customer Id Not Found");
		}
		return byId.get();
	}

}
