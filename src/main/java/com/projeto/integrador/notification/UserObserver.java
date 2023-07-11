package com.projeto.integrador.notification;

import com.projeto.integrador.Entity.Tweet;
import com.projeto.integrador.Entity.User;

public class UserObserver implements Observer {

    private final User user;

    public UserObserver(User user) {
        this.user = user;
    }

    @Override
    public void update(Tweet tweet) {
        System.out.println("User " + user.getUsername() + " received a notification about a new tweet: " + tweet.getMessage());
    }

}