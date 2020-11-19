
package TP6MecanismosSincronizacion.Ejercicio8.Locks;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Donante implements Runnable{
    private String nombre;
    private CentroHemoterapia centro;
    private boolean tieneRevista;
    
    public Donante(String n, CentroHemoterapia c){
        this.nombre = n;
        this.centro = c;
        this.tieneRevista = false;
    }
    
    public void run(){  // Poner los sleep aquí.
        try{
            centro.entrarDonante(nombre);
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 6000));  // Tiempo donan2.
            centro.salirDonante(nombre);
        } catch(InterruptedException e) { }
    }
    
}
