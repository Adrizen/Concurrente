
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
    private int camillasActuales;
    private int revistasActuales;
    
    private Condition avisarDonantes;
    
    private int turnoActual = 1;
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
        while (miTurno != turnoActual && camillasActuales <= 0) {
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
        System.out.println(GREEN_BOLD + nombreDonante + " consigue una camilla y se prepara para donar sangre." + RESET);
        lock.unlock();   
    }
    
    public void salirDonante(String nombreDonante){
        lock.lock();
        System.out.println(nombreDonante + " deja la camilla y sale del centro de hemoterapia.");
        camillasActuales++;
        turnoActual++;
        avisarDonantes.signalAll();
        lock.unlock();
    }
    
}
