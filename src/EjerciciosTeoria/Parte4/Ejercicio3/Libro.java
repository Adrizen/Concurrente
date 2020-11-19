
package EjerciciosTeoria.Parte4.Ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Libro {
    private ReentrantLock lockLibro;// Lock para operar variables.
    private Condition hayEscrito;   // Utilizado para indicar si hay nuevas cosas escritas.
    private Condition estaOcupado;  // Utilizado para saber si hay lectores leyendo.
    private int lectoresMaximos;    // Cantidad máxima de lectores para que no acaparen siempre la CPU.
    private int lectoresActuales;
    private int escritoresMaximos;
    private int escritoresActuales;
    
    
    public Libro(int maximoLectores, int maximoEscritores){
        lockLibro = new ReentrantLock();
        hayEscrito = lockLibro.newCondition();
        estaOcupado = lockLibro.newCondition();
        lectoresMaximos = maximoLectores;           
        lectoresActuales = 0;
        escritoresMaximos = maximoEscritores;
        escritoresActuales = 0;
    }
    
    // Métodos Lectores.
    public void hayEscrito(String nombreLector) throws InterruptedException {    // En este método espera a que se escriba algo nuevo.
        lockLibro.lock();
        while (lectoresActuales >= lectoresMaximos) {    // Primero comprueba si hay lugar.
            System.out.println(nombreLector + ": NO PUDE ENTRAR A LEER, LA PRÓXIMA VEZ SERÁ");
            hayEscrito.await();
        }
        lectoresActuales++;     // Consiguió lugar.
        lockLibro.unlock();
    }

    public void empezarLeer(String nombreLector) throws InterruptedException{
        System.out.println(nombreLector + ": VOY A LEER");
        Thread.sleep(3000);
    }
    
    public void terminarLeer(String nombreLector) throws InterruptedException{
        System.out.println(nombreLector + ": TERMINÉ DE LEER");
        lockLibro.lock();
        lectoresActuales--;
        if (lectoresActuales == 0) { // Si soy el último, dejá que entre el escritor.
            estaOcupado.signalAll();
        }
        hayEscrito.await();
        lockLibro.unlock();
    }
    
    // Métodos de Escritores.
    public void empezarEscribir(String nombreEscritor) throws InterruptedException{
        lockLibro.lock();
        estaOcupado.await();
        while (escritoresActuales >= escritoresMaximos) {
            System.out.println(nombreEscritor + ": NO PUDE ENTRAR A ESCRIBIR, LA PRÓXIMA VEZ SERÁ");
            estaOcupado.await();
        }
        escritoresActuales++;
        lockLibro.unlock();
        System.out.println(nombreEscritor + ": VOY A ESCRIBIR PA");
    }

    public void terminarEscribir(String nombreEscritor) throws InterruptedException {
        lockLibro.lock();
        Thread.sleep(4000); // Escribien2.
        System.out.println(nombreEscritor + ": TERMINÉ DE ESCRIBIR PA");
        escritoresActuales--;
        hayEscrito.signalAll();
        lockLibro.unlock();
    }

    // Sobre tener lock() y unlock() separados en distintos métodos como había antes:
    // "Silvia: El problema ahi, es que mientras un hilo escritor tiene el lock y esta escribiendo, ningun otro hilo puede intentar ingresar 
    // y no se quedaran en espera tampoco, o sea no llegan al await"
    // Andy crack: Se quedaría intentando agarrar lockLibro no?
    // Silvia: claro.
    
}
