
package TP6MecanismosSincronizacion.Ejercicio1;

import java.util.concurrent.Semaphore;
import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class SalaFumadores {
    private Semaphore ponerTabaco;
    private Semaphore ponerPapel;
    private Semaphore ponerFosforos;
    private Semaphore terminarFumar;
    
    public SalaFumadores(){
        ponerTabaco = new Semaphore(0);
        ponerPapel = new Semaphore(0);
        ponerFosforos = new Semaphore(0);
        terminarFumar = new Semaphore(0);
    }
    
    // Métodos Fumadores.
    public void entrarFumar(int id) throws InterruptedException{
        switch(id){
            case 1:
                ponerTabaco.acquire();
                System.out.println("Fumador " + id + " pone tabaco.");
                break;
            case 2:
                ponerPapel.acquire();
                System.out.println("Fumador " + id + " pone papel.");
                break;
            case 3:
                ponerFosforos.acquire();
                System.out.println("Fumador " + id + " pone fosforos.");
                break;
        }
        System.out.println("Fumador " + id + " fuma. (xdddd)");
    }
    
    public void terminarFumar(){
        System.out.println("Fumador termina de fumar.");
        terminarFumar.release();
    }
    
    // Métodos Agente.
    public void colocar(int numeroRandom){
        switch(numeroRandom){
            case 1:
                System.out.println("Agente pone papel y fósforos.");
                ponerTabaco.release();
                break;
            case 2:
                System.out.println("Agente pone tabaco y fósforos.");
                ponerPapel.release();
                break;
            case 3:
                System.out.println("Agente pone papel y tabaco.");
                ponerFosforos.release();
                break;
        }
        try {
            terminarFumar.acquire();
        } catch (InterruptedException e) { }
    }
    
}
