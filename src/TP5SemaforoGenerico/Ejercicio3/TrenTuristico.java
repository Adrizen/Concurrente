
package TP5SemaforoGenerico.Ejercicio3;

import java.util.concurrent.Semaphore;
import static Colores.Colores.*;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class TrenTuristico {
    private int cantidadAsientos;           // Indica la cantidad total de asientos que tiene el tren.
    private int asientosOcupados;           // Indica la cantidad de asientos con gente sentada.
    private Semaphore asientos;             // Semáforo general que tiene la misma cantidad de permisos que de asientos.
    private Semaphore vendedorTicket;       // Semáforo binario que indica si el vendedorTicket está disponible pa vender un ticket o no.
    private Semaphore pasajeroComprador;    // Semáforo binario que indica si un Pasajero quiere comprar un ticket.
    private Semaphore ticketVendido;        // Semáforo binario que indica cuando se vende un ticket.
    private Semaphore trenLleno;            // Semáforo binario que indica si el tren está lleno o no.
    private ReentrantLock subirTren;        // Lock para mutex al operar/consultar variables compartidas.
    
    
    public TrenTuristico(int cantidadMaxima){
        this.cantidadAsientos = cantidadMaxima;
        this.asientosOcupados = 0;
        this.asientos = new Semaphore(cantidadAsientos, true);    // Contiene la variable true para simular una fila.
        this.vendedorTicket = new Semaphore(1);
        this.pasajeroComprador = new Semaphore(0);
        this.ticketVendido = new Semaphore(0);
        this.subirTren = new ReentrantLock();
        this.trenLleno = new Semaphore(0);
    }
    
    // Métodos de Pasajeros.
    public void reservarAsiento(String nombrePasajero){
        try{
            asientos.acquire();        // Si el Pasajero no consigue lugar, se quedará esperando aquí, como en una fila.
            System.out.println(GREEN_BOLD + nombrePasajero + " consigue reservar un asiento y se dispone a comprar un ticket." + RESET);
        } catch (InterruptedException e) { }
    }

    public void comprarTicket(String nombrePasajero){
        try{
            vendedorTicket.acquire();
            System.out.println(GREEN_BOLD + nombrePasajero + " le indica al vendedor que quiere un ticket." + RESET);
            pasajeroComprador.release();
            ticketVendido.acquire();            // Pasajero se bloquea hasta que le venden el ticket.
        } catch (InterruptedException e) { }
    }
    
    public void subirse(String nombrePasajero) {
        subirTren.lock();
        System.out.println(GREEN_BOLD + nombrePasajero + " se sube al tren." + RESET);
        asientosOcupados++;
        if (asientosOcupados == cantidadAsientos) {     // Si se ocupa el último asiento el tren puede salír.
            trenLleno.release();
        }
        subirTren.unlock();
    }

    // Métodos de VendedorTicket.
    public void venderTicket(String nombreVendedor) {
        try {
            pasajeroComprador.acquire();
            System.out.println(CYAN_BOLD + nombreVendedor + " empieza a imprimir un ticket para el Pasajero." + RESET);
            Thread.sleep(3000);
            System.out.println(CYAN_BOLD + nombreVendedor + " le vende un ticket al Pasajero." + RESET);
            ticketVendido.release();
            vendedorTicket.release();
        } catch (InterruptedException e) { }
    }
    
    // Métodos de ControlTren.
    public void viajar() {
        try {
            trenLleno.acquire();
            System.out.println(YELLOW_BOLD + "El tren está lleno y puede partir." + RESET);
            Thread.sleep(7000);     // Tren viajan2.
            System.out.println(YELLOW_BOLD + "FIN DE RECORRIDO." + RESET);
            asientos.release(cantidadAsientos);
            asientosOcupados = 0;
        } catch (InterruptedException e) { }
    }
    
    // Nota: Puedo liberar TODOS los permisos juntos aquí porque no especifica que hacen los Pasajeros luego, sim embargo, si me dicen que luego de viajar hacen alguna otra actividad, 
    // entonces debería hacer que bajen de a uno (restando asientosOcupados--) y en el caso de que sea el último (usando un if) que recién ahí libere todos los permisos.
}
