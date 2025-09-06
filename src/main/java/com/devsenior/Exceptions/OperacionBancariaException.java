package com.devsenior.Exceptions;

public class OperacionBancariaException extends Exception{
    
    public OperacionBancariaException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
