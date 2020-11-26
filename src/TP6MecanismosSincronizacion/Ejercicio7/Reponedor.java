
package TP6MecanismosSincronizacion.Ejercicio7;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Reponedor implements Runnable{
    private String nombre;
    private Pasteleria pasteleria;
    
    public Reponedor(String n, Pasteleria p){
        nombre = n;
        pasteleria = p;
    }
    
    public void run() {
        try {
            while (true) {
                pasteleria.retirarCaja(nombre);
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 7000));  // Tiempo que tarda en colocar una nueva caja.
                pasteleria.reponerCaja(nombre);
            }
        } catch (InterruptedException e) { }

    }

}
