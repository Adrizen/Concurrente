
package TP4SeccionCritica.Ejercicio8;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Testigo {
    //Colores para los carteles.
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    private Semaphore semTestigo;           // Indica si alguien tiene le testigo.
    
    public Testigo(){
        this.semTestigo = new Semaphore(1);     
    }
    
    public void correr(){
        try{
            semTestigo.acquire();
            System.out.println(ANSI_GREEN + Thread.currentThread().getName()+" empieza a correr"+ANSI_RESET);
            int tiempoCarrera = (int)((Math.random() * (11 - 9 + 1) + 9)*1000);
            Thread.sleep(tiempoCarrera);
            System.out.println(ANSI_RED + Thread.currentThread().getName()+" deja el testigo"+ANSI_RESET);
            semTestigo.release();
        } catch(InterruptedException e) {}
    }
    

    
    
    
    /*Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
        Atletas.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
        Testigo.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
    */
}
