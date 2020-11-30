
package EjerciciosTeoria.Parte5.CyclicBarrier;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        ControlTren controlTren = new ControlTren();
        TrenTuristico trencito = new TrenTuristico(3,controlTren); // (cantidadMaxima, ControlTren viajeTren).
        
        // Hilos Pasajeros.
        for (int i = 0; i < 6; i++) {
            new Thread(new Pasajero("Pasajero"+i,trencito)).start();
        }
        
        for (int i = 0; i < 1; i++) {
            new Thread(new VendedorTicket("Vendedor"+i,trencito)).start();
        }
        
    }
    
}
