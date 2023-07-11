package com.projeto.integrador.notification;

import com.projeto.integrador.Entity.Tweet;

public interface Observer {
    void update(Tweet tweet);
}
