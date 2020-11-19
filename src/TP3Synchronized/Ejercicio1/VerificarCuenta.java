package TP3Synchronized.Ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class VerificarCuenta implements Runnable {

    private CuentaBanco cb = new CuentaBanco();


    public void run() {
        for (int i = 0; i <= 3; i++) {
            try {
                cb.hacerRetiro(10);
                if (cb.getBalance() < 0) {
                    System.out.println("La cuenta está sobregirada.");
                }
            } catch (InterruptedException e) {
                Logger.getLogger(VerificarCuenta.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
