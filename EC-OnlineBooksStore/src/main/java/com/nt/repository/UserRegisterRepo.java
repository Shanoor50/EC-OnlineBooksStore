package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.UserRegister;

@Repository
public interface UserRegisterRepo extends JpaRepository<UserRegister, Long> {

}
