package com.devsenior;

import com.devsenior.Exceptions.OperacionBancariaException;
import com.devsenior.Model.CuentaBancaria;
import com.devsenior.Services.ProcesadorTransacciones;

public class Main {
    public static void main(String[] args) {
        CuentaBancaria cuentaJulian = new CuentaBancaria(123456, 100d);

        try {
            ProcesadorTransacciones.procesarArchivo(cuentaJulian, "transacciones.txt");
            System.out.println("Saldo final: " + cuentaJulian.getDineroActual());
        } catch (OperacionBancariaException e) {
            System.err.println("Se produjo un error en la operación bancaria: " + e.getMessage());
            e.printStackTrace();
        }
    }
}