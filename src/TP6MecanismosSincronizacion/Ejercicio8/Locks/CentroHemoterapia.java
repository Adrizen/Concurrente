
package TP6MecanismosSincronizacion.Ejercicio8.Locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import static Colores.Colores.*;


/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class CentroHemoterapia {
    private ReentrantLock lock;
    private int camillasActuales;   // Cantidad de camillas en un determinado momento. Al inicio de la ejecición están todas disponibles
    private int revistasActuales;   // Cantidad de revistas en un determinado momento. Al inicio de la ejecición están todas disponibles.
    
    private Condition avisarDonantes;   // Condición para avisarle a los donantes si se libera una revista o una camilla.
    
    private int turnoActual = 1;    // El n° de turno actual. El Donante con este número puede ser atendido.
    private int turnoSiguiente = 1;
    
    public CentroHemoterapia(int numCamillas, int numRevistas){
        lock = new ReentrantLock();
        this.camillasActuales = numCamillas;
        this.revistasActuales = numRevistas;
        avisarDonantes = lock.newCondition();
    }
    
    public void entrarDonante(String nombreDonante) throws InterruptedException{
        lock.lock();
        System.out.println(YELLOW_BOLD + nombreDonante + " entra a la clínica." + RESET);
        boolean tieneRevista = false;
        int miTurno = turnoSiguiente;
        turnoSiguiente++;   // El siguiente Donante tendrá un n° de ticket más alto.
        while (miTurno != turnoActual || camillasActuales <= 0) { // Si no me toca y no hay camillas espero.
            if (revistasActuales > 0 && !tieneRevista) {
                revistasActuales--;
                tieneRevista = true;
                System.out.println(nombreDonante + " toma una revista y espera.");
            } else {
                if (!tieneRevista) {
                    System.out.println(nombreDonante + " no pudo conseguir una revista, mira la tele y espera.");
                }
            }
            avisarDonantes.await();
        }
        camillasActuales--;
        if (tieneRevista){
            System.out.println(nombreDonante + " devuelve la revista que había tomado");
            revistasActuales++; // Devuelve la revista que había tomado mientras esperaba.
            avisarDonantes.signalAll();
        }
        turnoActual++;
        System.out.println(GREEN_BOLD + nombreDonante + " consigue una camilla y se prepara para donar sangre." + RESET);
        lock.unlock();   
    }
    
    public void salirDonante(String nombreDonante){
        lock.lock();
        System.out.println(nombreDonante + " deja la camilla y sale del centro de hemoterapia.");
        camillasActuales++;
        avisarDonantes.signalAll();
        lock.unlock();
    }
    
}
