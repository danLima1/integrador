package com.projeto.integrador.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.integrador.Entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
    Users save(Users user);
    Users findByUserId(String userId);
}