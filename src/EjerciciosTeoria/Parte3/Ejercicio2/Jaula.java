
package EjerciciosTeoria.Parte3.Ejercicio2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Jaula {
    private Semaphore plato;
    private Semaphore rueda;
    
    public Jaula(){
        this.plato = new Semaphore(3, true);
        this.rueda = new Semaphore(1);
    }
    
    public void empezarAComer(String nombre){
        try{
            plato.acquire();
            System.out.println(nombre + " empieza a comer del plato");
            Thread.sleep(2500);
            System.out.println(nombre + " terminó de comer del plato y lo deja");
            plato.release();
        } catch (InterruptedException e) { }
    }
    
    public void rodar(String nombre){
        try{
            rueda.acquire();
            System.out.println(nombre + " empieza a rodar");
            Thread.sleep(3000);
            System.out.println(nombre + " terminó de rodar y baja de la rueda");
            rueda.release();
        } catch (InterruptedException e) { }
    }
    
}
