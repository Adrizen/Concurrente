
package TP6MecanismosSincronizacion.Ejercicio8.Locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class CentroHemoterapia {
    private ReentrantLock lock;
    private int camillasActuales;
    private int revistasActuales;
    private Condition avisarDonantes;
    
    
    public CentroHemoterapia(int numCamillas, int numRevistas){
        lock = new ReentrantLock();
        this.camillasActuales = numCamillas;
        this.revistasActuales = numRevistas;
        avisarDonantes = lock.newCondition();
    }
    
    public void entrarDonante(String nombreDonante) throws InterruptedException{
        lock.lock();
        boolean tieneRevista = false;
        while (camillasActuales <= 0){
            if (revistasActuales > 0){
                revistasActuales--;
                tieneRevista = true;
                System.out.println(nombreDonante + " toma una revista y espera.");
            } else {
                System.out.println(nombreDonante + " no pudo conseguir una revista, mira la tele y espera.");
            }
            avisarDonantes.await();
        }
        camillasActuales--;
        if (tieneRevista){
            System.out.println(nombreDonante + " devuelve la revista que había tomado");
            revistasActuales++; // Devuelve la revista que había tomado mientras esperaba.
            avisarDonantes.signal();
        }
        System.out.println(nombreDonante + " consigue una camilla y se prepara para donar sangre.");
        lock.unlock();   
    }
    
    public void salirDonante(String nombreDonante){
        lock.lock();
        System.out.println(nombreDonante + " deja la camilla y sale del centro de hemoterapia.");
        camillasActuales++;
        avisarDonantes.signal();
        lock.unlock();
    }
    
}
