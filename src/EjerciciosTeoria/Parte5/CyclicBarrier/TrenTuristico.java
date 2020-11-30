
package EjerciciosTeoria.Parte5.CyclicBarrier;
import static Colores.Colores.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class TrenTuristico {
    private CyclicBarrier barreraCiclica;   // Cuando la barrera se llena de pasajeros, el tren sale.
    private Semaphore asientos;             // 
    private Semaphore vendedorTicket;
    private Semaphore pasajeroComprador;
    private Semaphore ticketVendido;
    
    public TrenTuristico(int cantidadMaxima, ControlTren viajeTren){
        barreraCiclica = new CyclicBarrier(cantidadMaxima, viajeTren);  // Cuando la barrera se rompe se ejecuta el run de viajeTren.
        asientos = new Semaphore(cantidadMaxima);   // Semáforo general para 
        vendedorTicket = new Semaphore(1);
        pasajeroComprador = new Semaphore(0);
        ticketVendido = new Semaphore(0);
    }
    
    // Métodos de Pasajeros.
    public void reservarAsiento(String nombrePasajero) throws InterruptedException {
        System.out.println(GREEN_BOLD + nombrePasajero + " se dispone a comprar un ticket." + RESET);
        asientos.acquire();
    }

    public void comprarTicket(String nombrePasajero) throws InterruptedException {
        vendedorTicket.acquire();
        System.out.println(GREEN_BOLD + nombrePasajero + " le indica al vendedor que quiere un ticket." + RESET);
        pasajeroComprador.release();
        ticketVendido.acquire();            // Pasajero se bloquea hasta que le venden el ticket.
    }
    
    public void subirseTren(String nombrePasajero) throws InterruptedException, BrokenBarrierException{
        System.out.println(nombrePasajero + " se sube al tren.");
        barreraCiclica.await(); // Espera a que el tren se llene.
    }
    
    public void bajarseTren(){
        asientos.release();
    }

    // Métodos de VendedorTicket.
    public void venderTicket(String nombreVendedor) throws InterruptedException {
        pasajeroComprador.acquire();
        System.out.println(CYAN_BOLD + nombreVendedor + " empieza a imprimir un ticket para el Pasajero." + RESET);
        Thread.sleep(3000); // Tiempo que tarda en vender un ticket.
        System.out.println(CYAN_BOLD + nombreVendedor + " le vende un ticket al Pasajero." + RESET);
        ticketVendido.release();
        vendedorTicket.release();
    }

}
