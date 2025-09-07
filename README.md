# ⚡ Ejercicios Combinados de Excepciones en Java

## 1. Gestión de Cuentas Bancarias Avanzada

### Clase `CuentaBancaria` con métodos:
- `depositar(double monto)`
- `retirar(double monto)` → lanza **SaldoInsuficienteException** (checked).

### Simulación de lectura de transacciones desde un archivo:
- Maneja **IOException** y **FileNotFoundException**.  
- Si el archivo tiene valores no numéricos → lanza **NumberFormatException**.  
- Envuelve todos los errores en **OperacionBancariaException** (wrapper).  
- Asegúrate de cerrar el archivo con **try-with-resources**.  

👉 Aquí se aplica: **checked**, **unchecked**, **personalizada**, **wrapper**, **try-with-resources**.
