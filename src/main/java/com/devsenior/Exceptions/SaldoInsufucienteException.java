package com.devsenior.Exceptions;

public class SaldoInsufucienteException extends Exception {

    public SaldoInsufucienteException(String mensaje) {
        super(mensaje);
    }

    public SaldoInsufucienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
