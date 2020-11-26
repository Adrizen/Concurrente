
package TP6MecanismosSincronizacion.Ejercicio7;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Empaquetador implements Runnable{
    private String nombre;
    private Pasteleria pasteleria;
    
    public Empaquetador(String n, Pasteleria p) {
        nombre = n;
        pasteleria = p;
    }
    
    public void run(){
        pasteleria.encenderMaquinitas();
        while (true) {
            try {
                pasteleria.esperarPastel(nombre);
                int pesoPastel = pasteleria.tomarPastel(nombre);
                pasteleria.colocarPastel(nombre, pesoPastel);
            } catch (InterruptedException e) { }
        }
    }
    
}
