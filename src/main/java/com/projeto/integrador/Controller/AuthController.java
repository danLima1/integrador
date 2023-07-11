package com.projeto.integrador.Controller;

import com.projeto.integrador.*;
import com.projeto.integrador.Entity.User;
import com.projeto.integrador.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public UserLoginDto authentication() throws Exception {
        User user = userService.authenticate();
        if(user.getUsername() != null && user.getPassword() != null){
            UserLoginDto userLoginDto =
                    new UserLoginDto(user.getId(),user.getUsername(), user.getPassword(), user.getAccountStatus().name());
            userLoginDto.setOldToken(userService.getUserOldToken(user.getId()).getToken());
            return userLoginDto;
        } else {
            throw new RuntimeException("User invalid credentials");
        }
    }
}