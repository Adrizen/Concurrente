
package PereyraFAI1943;


import java.util.concurrent.Semaphore;
import static PereyraFAI1943.Colores.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class TrenTuristico {
    private int asientosTotales;
    private int asientosDisponibles;
    private int asientosOcupados;
    private Semaphore semVendedor;
    private Semaphore semTicketSolicitado;
    private Semaphore semTicketVendido;
    private Semaphore semTrenParo;
    private Semaphore semTrenLleno;
    private ReentrantLock sentarse;
    private boolean bajando;
    private ReentrantLock bloqueo;
    
    public TrenTuristico(int num){
        this.asientosTotales = num;                       // Indica la cantidad TOTAL de asientos que tiene el tren.
        this.asientosDisponibles = this.asientosTotales;// Indica cuantos asientos con Pasajeros ya sentados hay.
        this.asientosOcupados = 0;                      // Indica cuantos asientos ya tienen un cliente asignado.
        this.semVendedor = new Semaphore(1);            // Se utiliza para saber si el vendedor de tickets está disponible u ocupado vendiendo tickets. 1 = está disponible.
        this.semTicketSolicitado = new Semaphore(0);    // Se utiliza para saber si un Pasajero quiere un ticket. 0 = nadie quiere un ticket.
        this.semTicketVendido = new Semaphore(0);       // Se utiliza para saber si se vendió el ticket y permitir al pasajero subír al tren. 0 = no se vendió todavía.
        this.semTrenParo = new Semaphore(0);            // Se utiliza para saber si el tren está detenido o no. 0 = no está detenido.
        this.sentarse = new ReentrantLock();            // Se utiliza para que los Pasajeros se sienten de a uno y no haya problemas al modificar variables concurrentemente.
        this.semTrenLleno = new Semaphore(0);           // Se utiliza para indicar cuando el tren está lleno y puede partir. 0 = no está lleno.
        this.bajando = false;                           // Se utiliza para saber si pasajeros estan bajando. Se usa para que primero bajen todos y después puedan subir nuevos pasajeros.
        this.bloqueo = new ReentrantLock();             // Se utiliza para mutex y restar/sumas asientos de manera atómica.
    }
     
    // Métodos Pasajero.   
    public boolean reservarAsiento(String nombrePasajero){ 
        bloqueo.lock();
        boolean hayLugar = false;                                       
        if (!bajando &&  asientosOcupados != asientosTotales){
            System.out.println(GREEN_BOLD + nombrePasajero + " se subirá al tren." + RESET);
            asientosOcupados++;
            hayLugar = true;
        } else{
            System.out.println(RED_BOLD + nombrePasajero + " no pudo reservar un asiento." + RESET);
        }
        bloqueo.unlock();
        return hayLugar;
    }
    
    public void comprarTicket(String nombrePasajero){   // Pasajero compra un ticket, sube al tren y 
        try{
            semVendedor.acquire();
            System.out.println(GREEN_BOLD + nombrePasajero + " le solicita al vendedor un ticket." + RESET);
            semTicketSolicitado.release();
            semTicketVendido.acquire();
            System.out.println(GREEN_BOLD + nombrePasajero + " recibe el ticket y sube al tren." + RESET);
            sentarse.lock();
            asientosDisponibles--;      // El Pasajero se sienta finalmente en su asiento reservado anteriormente.
            if (asientosDisponibles == 0){
                System.out.println(GREEN_BOLD + nombrePasajero + " es el último en subír y le dice al maquinista que ya puede arrancar" + RESET);
                semTrenLleno.release();
            }
            sentarse.unlock();
            
        } catch (InterruptedException e) {}
        
    }
    
    public void bajarse(String nombrePasajero){ // Los Pasajeros bajan de a uno. Está synchronizado como la reserva de asientos, así es atómico.
        try{
            semTrenParo.acquire();
            System.out.println(nombrePasajero + " se baja del tren.");
            asientosOcupados--;
            asientosDisponibles++;
            if (asientosOcupados != 0){  // Aún hay gente que quiere bajar.
                semTrenParo.release();
            } else{
                bajando = false;
            }
        } catch(InterruptedException e) {}
    }
    
    // Métodos VendedorTicket
    public void venderTicket(String nombreVendedor){    // El VendedorTicket le vende un ticket a un Pasajero que tenga un asiento reservado.
        try{
            semTicketSolicitado.acquire();
            System.out.println(CYAN_BOLD + nombreVendedor + " va a venderle un ticket al pasajero." + RESET);
            Thread.sleep(4000); // Tiempo que tarda en vender el ticket.
            System.out.println(CYAN_BOLD + nombreVendedor + " vendió un ticket" + RESET);
            semTicketVendido.release(); // Vendió el ticket.
            semVendedor.release();  // VendedorTicket se desocupó.
        } catch(InterruptedException e) {}
    }
    
    // Métodos ControlTren.
    public void hacerRecorrido(){   // Una vez que el tren está lleno, se inicia el recorrido.
        try{
            semTrenLleno.acquire();
            bajando = true;
            System.out.println(YELLOW_BOLD + "El tren está lleno y sale a pasear por el lugar." + RESET);
            Thread.sleep(6000); // Tiempo de viaje.
            System.out.println(YELLOW_BOLD + "El tren completó el recorrido." + RESET);
            semTrenParo.release();  // El tren paró y los Pasajeros pueden empezar a bajarse.
        } catch(InterruptedException e) {}
        
    }
    
    
}
