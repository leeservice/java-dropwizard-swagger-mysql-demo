package org.kainos.ea.client;

public class InvalidProductException extends Throwable{
    public InvalidProductException(String error){
        super(error);
    }
}
