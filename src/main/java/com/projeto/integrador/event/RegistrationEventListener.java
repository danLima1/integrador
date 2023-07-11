package com.projeto.integrador.event;

import com.projeto.integrador.Entity.User;
import com.projeto.integrador.Service.EmailSenderService;
import com.projeto.integrador.Service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailService;


    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        String subject = "Verify your registration";
        userService.saveVerificationTokenForUser(token, user);
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        emailService.sendEmail(user.getEmail(), "Click the following link to activate your account\n" + url, subject);
    }
}