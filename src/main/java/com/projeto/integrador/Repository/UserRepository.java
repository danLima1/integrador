package com.projeto.integrador.Repository;

import com.projeto.integrador.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String name);

    User findByEmail(String email);

    User findByPassword(String password);

}