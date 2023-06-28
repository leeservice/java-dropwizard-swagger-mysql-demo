package org.kainos.ea.client;

public class OrderDoesNotExistException extends Throwable {
        @Override
        public String getMessage(){
            return "Order does not exist in the database";
        }
    }

