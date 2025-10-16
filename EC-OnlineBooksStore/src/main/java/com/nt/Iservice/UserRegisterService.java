package com.nt.Iservice;

import com.nt.entity.UserRegister;
import com.nt.model.UserRequestDto;

public interface UserRegisterService {
    UserRegister insertUserRegister(UserRequestDto userRequestDto);
}
