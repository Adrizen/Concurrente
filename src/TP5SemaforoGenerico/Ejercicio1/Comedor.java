
package TP5SemaforoGenerico.Ejercicio1;

import static Colores.Colores.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Comedor {
    private int cantidadComederosDisponibles;  // Indica la cantidad de comederos que no están ocupados.
    private int cantidadComederosTotal;        // Indica la cantidad total de comederos que hay.
    private Semaphore semPerros;               // Semáforo general para dar paso a los perros al comedero (cuando su especie lo reclamó).
    private Semaphore semGatos;                // Semáforo general para dar paso a los gatos al comedero (cuando su especie lo reclamó).
    private ReentrantLock operar;              // Lock para lograr el mutex y operar/consultar variables.
    private boolean reclamado;                 // Indica si algúna especie reclamó los comederos.
    
    public Comedor(int cant){
        this.cantidadComederosTotal = cant;
        this.cantidadComederosDisponibles = cant;
        semPerros = new Semaphore(1);   // Primero comen los rropes. Siempre.
        semGatos = new Semaphore(0);
        operar = new ReentrantLock();
        
        reclamado = false;
    }
    
    /*Supongo que los comederos siempre se llenan (como el ejercicio del tren).
      Supongo que siempre hay un mínimo de perros y gatos que llenan los comederos. (Los perros empiezan siempre y luego intercalan) */
    // Si no se pueden hacer estas suposiciones, debería crear un 'parser' que cuente a cada animal y así saber como operar.
    
    // Métodos de los Animales.
    public void entrar(String tipoAnimal) {
        try {
            if (tipoAnimal.equals("perro")) {
                semPerros.acquire();
                if (!reclamado){
                    reclamar(tipoAnimal);
                    semPerros.release((cantidadComederosTotal/2) - 1);
                }
            } else {
                semGatos.acquire();
                if (!reclamado){
                    reclamar(tipoAnimal);
                    semGatos.release(cantidadComederosTotal - 1);
                }
            }
        } catch (InterruptedException e) { }
    }
    
    private void reclamar(String tipoAnimal){
        reclamado = true;
        if (tipoAnimal.equals("perro")){
            cantidadComederosDisponibles = cantidadComederosDisponibles / 2;
            if (((cantidadComederosDisponibles % 2) == 1)  && (cantidadComederosTotal != 2) ) {
                cantidadComederosDisponibles++;     // Si la cantidad de comederos disponibles quedó impar y es distinto de 2 (caso especial)
            }                                       // entonces sumo uno más para que no explote en la siguiente iteración.
        } else {
            cantidadComederosDisponibles = 0;
        }
    }

    public void comer(String nombreAnimal){
        try{
            System.out.println(GREEN_BOLD + nombreAnimal + " come." + RESET);
            Thread.sleep(4000); // Tiempo que tardan en comer.
            System.out.println(GREEN_BOLD + nombreAnimal + " terminó de comer." + RESET);
        } catch(InterruptedException e) { }
    }

    public void salir(String nombreAnimal, String tipoAnimal){  // Los animales sale de a uno y suman el comedero que desocupan.
        System.out.println(nombreAnimal + " deja el comedero. " + RESET);
        operar.lock();
        cantidadComederosDisponibles++;
        if (cantidadComederosDisponibles == cantidadComederosTotal){    // Cuando se desocuparon todos, autoriza a la otra especie a entrar.
            reclamado = false;   // Ya no está reclamado.
            if (tipoAnimal.equals("perro")){
                semGatos.release();
            } else {
                semPerros.release();
            }
        }
        operar.unlock();
    }
}
