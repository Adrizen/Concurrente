
package TP5SemaforoGenerico.Ejercicio4;
import static Colores.Colores.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Aeropuerto {
    private int aterrizajesRequeridos;      // Cantidad de aterrizajes que se quieren hacer.
    private int aterrizajesRealizados;      // Cantidad de aterrizajes que se hicieron (se reinician al haber un despegue)
    private int despeguesRequeridos;        // Cantidad de despegues que se quieren hacer.
    private Semaphore semPista;             // Indica si la pista está en uso o no.
    private Semaphore solicitud;            // Indica la cantidad de solicitures a la torre de control (aterrRequeridos + despegRequeridos).
    private Semaphore semDespegues;          
    private Semaphore semAterrizajes;
    private ReentrantLock operar;           // Lock para operar con mutex las variables.
    private Semaphore semPistaAterrizar;    // Indica si un Avión puede usar la pista para aterrizar.
    private Semaphore semPistaDespegar;     // Indica si un Avión puede usar la pista para despegar.
    
    public Aeropuerto() {
        aterrizajesRequeridos = 0;
        aterrizajesRealizados = 0;
        semPista = new Semaphore(0);
        semDespegues = new Semaphore(0);
        semAterrizajes = new Semaphore(0);
        operar = new ReentrantLock();
        solicitud = new Semaphore(0);
        semPistaAterrizar = new Semaphore(0);
        semPistaDespegar = new Semaphore(0);
    }
    
    // Métodos de los Aviones.
    public void informarTorre(String numeracionAvion, String tipo){
        if (tipo.equals("aterrizar")){
            semAterrizajes.release();
            operar.lock();
            aterrizajesRequeridos++;    // Opera con mutex.
            operar.unlock();
        } else {    // Despegue
            semDespegues.release();
            operar.lock();
            despeguesRequeridos++;      // Opera con mutex.
            operar.unlock();
        }
        System.out.println(GREEN_BOLD + numeracionAvion + " informa que quiere: " + tipo + RESET);
        solicitud.release();
    }
    
    public void realizarAccion(String numeracionAvion, String tipo){
        try {
            if (tipo.equals("aterrizar")){
                semPistaAterrizar.acquire();
                aterrizajesRequeridos--;
                aterrizajesRealizados++;
            } else {
                semPistaDespegar.acquire();
                despeguesRequeridos--;
                aterrizajesRealizados = 0;  // Se reinicia el contador.
            }
            System.out.println(YELLOW_BOLD + numeracionAvion + " comienza a " + tipo + RESET);
            Thread.sleep(5000); // Tiempo que tarda en realizar su acción.
            System.out.println(YELLOW_BOLD + numeracionAvion + " terminó de " + tipo + RESET);
            semPista.release();
        } catch (InterruptedException e) { }
    }
    
    // Métodos de TorreControl
    public void autorizar() {
        try {
            solicitud.acquire();
            if (aterrizajesRequeridos > 0 && aterrizajesRealizados < 10) {
                semAterrizajes.acquire();
                semPistaAterrizar.release();
                System.out.println(CYAN_BOLD + "La torre de control autorizó a un avión a aterrizar." + RESET);
            } else {
                if (despeguesRequeridos > 0 || aterrizajesRealizados >= 10) {
                    semDespegues.acquire();
                    semPistaDespegar.release();
                    System.out.println(CYAN_BOLD + "La torre de control autorizó a un avión a despegar." + RESET);
                }
            }
            semPista.acquire();
        } catch (InterruptedException e) { }
    }
}
