
package com.proyectofinal.bazar.exceptions;


public class ClienteAlreadyExistsException extends RuntimeException{
    public ClienteAlreadyExistsException(String message){
        super(message);
    }
    
}
