
package TP4SeccionCritica.Ejercicio13;

import java.util.concurrent.Semaphore;
import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Confiteria {
    
    //Variables.
    private Semaphore semAsiento;
    private Semaphore semBebidaSolicitada;
    private Semaphore semBebidaPreparada;
    private Semaphore semBeber;
    
    public Confiteria(){
        semAsiento = new Semaphore(1);  // Indica si hay un espacio libre o no para un empleado. 1 = hay lugar disponible.
        semBebidaSolicitada = new Semaphore(0);  // Indica si un Empleado realizó un pedido de bebida. 0 = no hay un pedido de bebida.
        semBebidaPreparada = new Semaphore(0);  // Indica si la bebida que solicitó el Empleado está lista. 0 = no está lista.
        semBeber = new Semaphore(0);   // Indica si el Empleado puede beber. 0 = no puede beber.
    }
    
    //Métodos que usara el Empleado.
    public boolean sentarse(){
        return (semAsiento.tryAcquire());
    }
    
    public void pedir(String nombreEmpleado){
        try{
            Thread.sleep(5000);
            System.out.println(GREEN_BOLD + "Luego de unos segundos "+nombreEmpleado+" se decide por algo" + RESET);
            semBebidaSolicitada.release();
        } catch(InterruptedException e) {}
    }
    
    public void comer(String nombreEmpleado){
        try{
            semBeber.acquire();
            System.out.println(GREEN_BOLD + nombreEmpleado + " empieza a beber y comer" + RESET);
            Thread.sleep(5000);
            System.out.println(GREEN_BOLD + nombreEmpleado + " terminó de beber y comer" + RESET);
        } catch(InterruptedException e) {}
    }
    
    public void dejarAsiento(){
        semAsiento.release();
    }
    
    //Métodos que usara el Mozo.
    public void prepararBebida(String nombreMozo){
        try{
            semBebidaSolicitada.acquire();
            System.out.println(PURPLE_BOLD + nombreMozo + " comienza a preparar la bebida que solicitó el Empleado" + RESET);
            Thread.sleep(3000);
            System.out.println(PURPLE_BOLD + nombreMozo + " terminó de preparar la bebida." + RESET);
            semBebidaPreparada.release();
        } catch(InterruptedException e) {}
    }
    
    public void servir(String nombreMozo){
        try{
            semBebidaPreparada.release();
            System.out.println(PURPLE_BOLD + nombreMozo + " toma una bandeja, coloca la bebida y comida en ella para servirsela al Empleado." + RESET);
            Thread.sleep(2000);
            System.out.println(PURPLE_BOLD + nombreMozo + " llevó el pedido hasta el Empleado y le indica que puede empezar a comer" + RESET);
            semBeber.release();
        } catch(InterruptedException e) {}
    }
    
}
