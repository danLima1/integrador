package com.projeto.integrador.exceptions;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String msg){
        super(msg);
    }
}