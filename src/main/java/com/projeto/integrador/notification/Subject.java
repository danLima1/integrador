package com.projeto.integrador.notification;

import com.projeto.integrador.Entity.Tweet;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Tweet tweet);
}