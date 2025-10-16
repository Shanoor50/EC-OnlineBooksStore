package com.nt.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Iservice.UserRegisterService;
import com.nt.entity.UserRegister;
import com.nt.model.UserRequestDto;
import com.nt.repository.UserRegisterRepo;

@Service
public class UserRegisterServiceImp implements UserRegisterService {

    @Autowired
    private UserRegisterRepo userRepo;

    @Override
    public UserRegister insertUserRegister(UserRequestDto userRequestDto) {
        UserRegister user = new UserRegister();
        try {
            user.setFirstName(userRequestDto.getFirstName());
            user.setLastName(userRequestDto.getLastName());
            user.setEmail(userRequestDto.getEmail());
            user.setPassword(userRequestDto.getPassword());
            user.setContactId(userRequestDto.getContactId());

            // Save to DB
            user = userRepo.save(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
