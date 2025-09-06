package com.devsenior.Model;

import com.devsenior.Exceptions.SaldoInsufucienteException;

public class CuentaBancaria {
    private Integer numeroCuenta;
    private Double dineroActual;

    public CuentaBancaria(Integer numeroCuenta, Double dineroActual){
        this.numeroCuenta = numeroCuenta;
        this.dineroActual = dineroActual;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public Double getDineroActual() {
        return dineroActual;
    }

    public Double depositar(Double dinero){
        this.dineroActual += dinero;
        return dineroActual;
    }

    // En este metodo se implemento el uso de una excepcion personalizada de tipo cheked
    public Double retirar(Double dinero) throws SaldoInsufucienteException{
        if (dinero > dineroActual){
            throw new SaldoInsufucienteException("El saldo de su cuenta es insufuciente");
        }

        dineroActual -= dinero;
        return dineroActual;
    }

}
