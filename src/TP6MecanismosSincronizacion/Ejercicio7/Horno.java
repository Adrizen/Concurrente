
package TP6MecanismosSincronizacion.Ejercicio7;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Horno implements Runnable{
    private String nombre;
    private String tipo;
    private int peso;
    private Pasteleria pasteleria;
    
    public Horno(String n, String t, int pe ,Pasteleria p){
        nombre = n;
        tipo = t;
        peso = pe;
        pasteleria = p;
    }
    
    public void run() {
        try {
            while (true) {
                System.out.println(nombre + " " + tipo + " HORNEAN2.");
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5000));  // Tiempo en preparar el pastel.
                pasteleria.producir(nombre, tipo, peso);
            }
        } catch (InterruptedException e) { }
    }
    
}
