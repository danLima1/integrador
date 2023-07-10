package com.projeto.integrador.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.integrador.Entity.Users;
import com.projeto.integrador.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
     UserRepository userRepository;

    public Users submitDataofUser(Users user){
        return userRepository.save(user);
    }
    public Users displayUserData(String userId){
        return userRepository.findByUserId(userId);
    }
}