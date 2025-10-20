package com.nt.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nt.Iservice.CustomerService;
import com.nt.entity.Customer;
import com.nt.exception.CustomerIdNotFoundException;
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
				throw new RuntimeException("Customer Not Available");
			}
		}
		return customer;
	}

	@Override
	public Customer getByCustomersId(Long id) {
		Optional<Customer> byId = customerRepo.findById(id);
		if(!byId.isPresent()) {
			throw new CustomerIdNotFoundException("Customer Id Not Found");
		}
		return byId.get();
	}

	@Override
	public List<Customer> getByAllCustomers() {
		List<Customer> list = customerRepo.findAll();
		return list;
	}

	@Override
	public Page<Customer> getByAllCustomersWithPaginations(int page, int size, String sortField, String pageDir) {
	    Sort sort = pageDir.equalsIgnoreCase("asc") 
	        ? Sort.by(sortField).ascending() 
	        : Sort.by(sortField).descending();

	    PageRequest request = PageRequest.of(page, size, sort);

	    return customerRepo.findAll(request);
	}


	

}
