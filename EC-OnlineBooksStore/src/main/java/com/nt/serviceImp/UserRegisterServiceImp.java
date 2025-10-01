package com.nt.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Iservice.UserRegisterService;
import com.nt.entity.UserRegister;
import com.nt.model.UserRequestsDTO;
import com.nt.repository.UserRegisterRepo;

@Service
public class UserRegisterServiceImp implements UserRegisterService {

	@Autowired
	private UserRegisterRepo userRepo;
	
	@Override
	public String insertUserRegister(UserRequestsDTO userRequestsDTO) {
		UserRegister user=new UserRegister();
		try {
			user.setFirstName(userRequestsDTO.getFirstName());
			user.setLastName(userRequestsDTO.getLastName());
			user.setEmail(userRequestsDTO.getEmail());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "Save Successfully";
	}

}
