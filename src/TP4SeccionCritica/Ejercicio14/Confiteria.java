package TP4SeccionCritica.Ejercicio14;

import java.util.concurrent.Semaphore;
import static Colores.Colores.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Confiteria {
    private int sillasDisponibles;
    private Semaphore semLugaresDisponibles;
    private Semaphore semBeber;
    private Semaphore semComer;
    private Semaphore semMozoDisponible;
    private Semaphore semBebidaSolicitada;
    private Semaphore semSirvioBebida;
    private Semaphore semComidaSolicitada;
    private Semaphore semComidaPreparada;
    
    public Confiteria(){
        sillasDisponibles = 2;                          // La cantidad de sillas disponibles en la Confitería.
        semLugaresDisponibles = new Semaphore(1);       // Se utiliza para lograr mutex a la hora de manipular/consultar la cantidad de sillas.
        semBeber = new Semaphore(0);                    // Se utiliza para que el Empleado sepa cuando puede beber.
        semComer = new Semaphore(0);                    // Se utiliza para que el Empleado sepa cuando puede comer.
        semMozoDisponible = new Semaphore(1);           // Se utiliza para saber si el Mozo está ocupado en alguna otra tarea.
        semBebidaSolicitada = new Semaphore(0);         // Se utiliza para que el Mozo sepa que alguien solicitó una bebida y que tiene que prepararla.
        semSirvioBebida = new Semaphore(0);             // Se utiliza para que el Empleado sepa cuando recibió la bebida y puede solicitar la comida.
        semComidaSolicitada = new Semaphore(0);         // Se utiliza para que el Cocinero sepa que alguien solicitó comida y que tiene que prepararla.
        semComidaPreparada = new Semaphore(0);          // Se utiliza para que el Empleado sepa cuando la comida está preparada.
        
    }
    
    // Métodos del Empleado.
    public boolean intentarSentarse(String nombreEmpleado){     // Primero checkea que haya lugares disponibles, si lo hay se sienta en una silla. Si no devuelve false.
        boolean disponible = false;
        try {
            semLugaresDisponibles.acquire();
            if (sillasDisponibles > 0){
                System.out.println(GREEN_BOLD + nombreEmpleado + " llegó a la confitería y encontró un lugar disponible. Se sienta en una mesa para ser atendido." + RESET);
                sillasDisponibles--;
                disponible = true;
            }
            semLugaresDisponibles.release();
        } catch (InterruptedException e) { }
        return disponible;
    }
    
    public void consumir(String nombreEmpleado) {       // Aquí el Empleado selecciona aleatoriamente algo del menú.
        int randomInt = ThreadLocalRandom.current().nextInt(1, 4);
        System.out.println(GREEN_BOLD + nombreEmpleado + " eligió: " + randomInt + " del menú" + RESET);
        try {
            switch (randomInt) {
                case 1:
                    semMozoDisponible.acquire();
                    semBebidaSolicitada.release();
                    semSirvioBebida.acquire();
                    beber(nombreEmpleado);
                    break;
                case 2:
                    semComidaSolicitada.release();
                    comer(nombreEmpleado);
                    break;
                case 3:
                    semMozoDisponible.acquire();
                    semBebidaSolicitada.release();
                    semSirvioBebida.acquire();
                    semComidaSolicitada.release();
                    beber(nombreEmpleado);
                    comer(nombreEmpleado);
                    break;
            }
        } catch (InterruptedException e) { }
    }
    
    public void beber(String nombreEmpleado){    // Empleado toma.
        try{
            semBeber.acquire();
            System.out.println(GREEN_BOLD + nombreEmpleado + " toma su bebida y empieza a beberla." + RESET);
            Thread.sleep(5000);
            System.out.println(GREEN_BOLD + nombreEmpleado + " terminó de tomar." + RESET);
        } catch(InterruptedException e) { }
    }
    
    public void comer(String nombreEmpleado){    // Empleado come.
        try{
            semComer.acquire();
            System.out.println(GREEN_BOLD + nombreEmpleado + " empieza a comer" + RESET);
            Thread.sleep(5000);
            System.out.println(GREEN_BOLD + nombreEmpleado + " termina su platillo." + RESET);
        } catch (InterruptedException e) { }
    }
    
    public void irse(String nombreEmpelado){    // Si un empleado terminó de consumir su pedido, libera su silla.
        try {
            semLugaresDisponibles.acquire();
            sillasDisponibles++;
            semLugaresDisponibles.release();
        } catch(InterruptedException e) { }
    }
    
    // Métodos del Mozo.
    public void prepararBebida(String nombreMozo){
        try {
            semBebidaSolicitada.acquire();
            System.out.println(PURPLE_BOLD + nombreMozo + " comienza a preparar la bebida que solicitó el Empleado" + RESET);
            Thread.sleep(3000);
            System.out.println(PURPLE_BOLD + nombreMozo + " terminó de preparar la bebida." + RESET);
        } catch (InterruptedException e) { }
    }
    
    public void servir(String nombreMozo){
        try {
            System.out.println(PURPLE_BOLD + nombreMozo + " toma la bebida del Empleado y se la lleva" + RESET);
            Thread.sleep(1500);
            System.out.println(PURPLE_BOLD + nombreMozo + " llevó el pedido hasta el Empleado" + RESET);
            semSirvioBebida.release();
            semBeber.release();
            semMozoDisponible.release();
        } catch (InterruptedException e) { }
    }
    
    // Métodos del Cocinero.
    public void prepararComida(String nombreCocinero) {
        try {
            semComidaSolicitada.acquire();
            System.out.println(CYAN_BOLD + nombreCocinero + " comienza a preparar la comida que solicitó el Empleado" + RESET);
            Thread.sleep(5000);
            System.out.println(CYAN_BOLD + nombreCocinero + " terminó de preparar la comida." + RESET);
            System.out.println(CYAN_BOLD + nombreCocinero + " le avisa al Empleado que ya puede retirar su pedido." + RESET);
            semComidaPreparada.release();
            semComer.release();
        } catch (InterruptedException e) { }
    }
    
        
}
