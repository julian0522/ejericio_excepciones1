package com.devsenior.Exceptions;

public class OperacionBancariaException extends Exception{
    
    /* 
        Utilizamos este constructor para que al crearse una instancia de esta clase s pueda pasar como
        parametro tambien a parte del mensaje de error, la causa que proboco esa excepcion, es decir la excepcion original
        y asi poder mantener el rastro de que origino realmente el error
    */
    public OperacionBancariaException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
