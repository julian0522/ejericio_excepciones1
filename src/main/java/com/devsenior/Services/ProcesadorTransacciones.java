package com.devsenior.Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.devsenior.Exceptions.OperacionBancariaException;
import com.devsenior.Exceptions.SaldoInsufucienteException;
import com.devsenior.Model.CuentaBancaria;

public class ProcesadorTransacciones  {
    
    /**
     * Metodo estatico que se encarga de gestionar las operaciones realizadas con las cuentas bancarias
     * 
     * @param cuenta Objeto de tipo CuentaBancaria
     * @param nombreArchivo Nombre del archivo donde se encuentran las transacciones realizadas por la cuenta
     * @throws OperacionBancariaException Excepcion Chequeada de negocio que se lanza cuando ocurre un problema con las transacciones
     */
    public static void procesarArchivo(CuentaBancaria cuenta, String nombreArchivo) throws OperacionBancariaException {

        /* 
            Uso del try with resources, simulamos la lectura de las transacciones para la cuenta bancaria desde
            un archivo plano .txt, por ello usamos este try catch para que al finalizar la operacion dentro de el
            se libere recursos y el objeto de tipo BufferedReader sea eliminado automaticamente
        */ 
        try ( BufferedReader br = new BufferedReader(new FileReader(nombreArchivo)) ) {
            String linea;
            // Implementamos un ciclo para rrecorrer cada una de las lineas del archivo plano
            // el metodo .readLine lo que hace es leer una linea del archivo, si esa linea tiene contenido la retorna en un string
            // si la linea no tiene contenido retorna null por ello la condicion es al siguientes
            while ( (linea = br.readLine()) != null ) {
                // Usamos un try catch para atrapar todas las excepciones lanzadas en el proceso para alojarlas en nuestra excepcion de negocio y asi propagarla
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
                        // si el tipo de operacion a realizar es deiferente de D o R se lanza una excepcion
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
