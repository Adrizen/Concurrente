
package PereyraFAI1943;
import java.util.Scanner;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class mainEjercicio1 {

    public static void main(String[] args) {
        // Creo e inicializo clases y Threads.
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de asientos del tren");
        int numero = sc.nextInt();
        TrenTuristico tren = new TrenTuristico(numero);
        ControlTren control = new ControlTren(tren);
        Thread controlTren = new Thread(control);
        VendedorTicket vendedor = new VendedorTicket("Sr. Vendedor",tren);
        Thread vendedorTickets = new Thread(vendedor);
        
        // Inicio los hilos.
        controlTren.start();
        vendedorTickets.start();
        // Creo e inicio los Pasajeros.
        for (int i = 0; i < 6; i++) {
            new Thread(new Pasajero("Pasajero"+i,tren)).start();
        }
        
    }
    
    
}
