
package EjerciciosTeoria.Parte3.Ejercicio3;
import static Colores.Colores.*;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Barberia {
    private int sillasDisponibles;
    private int sillasOcupadas;
    private Semaphore barberoCortando;   // Creo que tengo que poner un semáforo si o si.
    
    public Barberia(int disponibles){
        this.sillasDisponibles = disponibles;
        this.sillasOcupadas = 0;
        this.barberoCortando = new Semaphore(0);    // Indica si el barbero está cortandole a algún Cliente y evita que este último se vaya antes de tiempo.
    }
    
    // Métodos del Cliente.
    public synchronized void cortarsePelo(String nombreCliente){
        try{
            while (sillasOcupadas >= sillasDisponibles){    // Lo encierro acá.
                System.out.println(RED_BOLD + nombreCliente + " espera afuera de la barbería" + RESET);
                this.wait();
            }
            System.out.println(GREEN_BOLD + nombreCliente + " se logra sentar en una silla y espera a que el barbero le corte" + RESET);
            sillasOcupadas++;
            this.notifyAll();
        } catch (InterruptedException e) { }
    }

    public void irse(String nombreCliente) {
        try {
            barberoCortando.acquire();
            System.out.println(GREEN_BOLD + nombreCliente + " se va de la barbería con su nuevo corte" + RESET);
        } catch (InterruptedException e) { }
    }

    // Métodos del barbero.
    public synchronized void cortarPelo(String nombreBarbero){
        try{
            if (sillasOcupadas > 0){
                System.out.println(CYAN_BOLD + nombreBarbero + " empieza a atender al Cliente" + RESET);
                Thread.sleep(3000);
                System.out.println(CYAN_BOLD + nombreBarbero + " termina de atender al Cliente" + RESET);
                sillasOcupadas--;
                this.notifyAll();
                barberoCortando.release();
            }
        } catch (InterruptedException e) { }
    }
}
