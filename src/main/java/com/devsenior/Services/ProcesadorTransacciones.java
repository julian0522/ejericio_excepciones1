package com.devsenior.Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.devsenior.Exceptions.OperacionBancariaException;
import com.devsenior.Exceptions.SaldoInsufucienteException;
import com.devsenior.Model.CuentaBancaria;

public class ProcesadorTransacciones  {
    
    public static void procesarArchivo(CuentaBancaria cuenta, String nombreArchivo) throws OperacionBancariaException {

        try ( BufferedReader br = new BufferedReader(new FileReader(nombreArchivo)) ) {
            String linea;
            while ( (linea = br.readLine()) != null ) {
                try {
                    
                    String[] partes = linea.split(" ");
                    String tipo = partes[0];
                    double monto = Double.parseDouble(partes[1]);

                    if ("D".equalsIgnoreCase(tipo)){
                        var nuevoMonto = cuenta.depositar(monto);
                        System.out.println(String.format("""
                            Monto Depositado con exito en la cuenta: %d
                             - Monto Ingresado: %.1f
                             - Saldo Actual en la cuenta: %.1f
                            """, cuenta.getNumeroCuenta(), monto, nuevoMonto));
                    }
                    else if ("R".equalsIgnoreCase(tipo)){
                        var nuevoMonto = cuenta.retirar(monto);
                        System.out.println(String.format("""
                            Monto Retirado con exito en la cuenta: %d
                             - Monto Ingresado: %.1f
                             - Saldo Actual en la cuenta: %.1f
                            """, cuenta.getNumeroCuenta(), monto, nuevoMonto));
                    }
                    else{
                        throw new NumberFormatException("operacion Desconocida " + tipo);
                    }

                } catch (SaldoInsufucienteException ex) {
                    throw new OperacionBancariaException("Error al retirar Fondos: ", ex);
                }catch (NumberFormatException ex){
                    throw new OperacionBancariaException("Formato de numero invalido en linea " + linea, ex);
                }

            }
        } catch (IOException e) {
            throw new OperacionBancariaException("Error de lectura del archivo", e);
        }
    }
}
