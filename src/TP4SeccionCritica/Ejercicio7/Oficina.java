
package TP4SeccionCritica.Ejercicio7;

import static Colores.Colores.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Oficina {
    private Semaphore semRetirarCopiaA;
    private Semaphore semRetirarCopiaB;
    private Semaphore semCopiaPendienteA;
    private Semaphore semCopiaPendienteB;
    private ReentrantLock lockCopiaA;   // Agrego estos locks e ints para stackear copias y que no sean los semáforos los que acumulen permisos.
    private ReentrantLock lockCopiaB;   // Ya que solo se pueden usar semáforos binarios hasta ahora.
    private int numeroCopiasPendienteA; // Aquí acumulo copias. Todo esto porque solo puedo usar semáforos binariEs.
    private int numeroCopiasPendienteB; // Aquí también acumulo copias.
    
    
    public Oficina(){
        this.semCopiaPendienteA = new Semaphore(0);
        this.semCopiaPendienteB = new Semaphore(0);
        this.semRetirarCopiaA = new Semaphore(0);
        this.semRetirarCopiaB = new Semaphore(0);
        this.lockCopiaA = new ReentrantLock();
        this.lockCopiaB = new ReentrantLock();
        this.numeroCopiasPendienteA = 0;
        this.numeroCopiasPendienteB = 0;
    }
    
    // Métodos de las Impresoras
        // ImpresorasA.
    public void agregarImpresionA(){
            lockCopiaA.lock();      // Mutex para sumar copias pendientes.
            numeroCopiasPendienteA++;
            if (numeroCopiasPendienteA == 1){
                semCopiaPendienteA.release();
            }
            lockCopiaA.unlock();
    }
    
    public void imprimirA(String nombreImpresora){
        try {
            semCopiaPendienteA.acquire();
            lockCopiaA.lock();         // Mutex para restar copias hechas.
            numeroCopiasPendienteA--;  // Quita, del contador, la copia que acaba de hacer.
            if (numeroCopiasPendienteA > 0) {
                semCopiaPendienteA.release();
            }
            lockCopiaA.unlock();
            System.out.println(YELLOW_BOLD + nombreImpresora + " empieza a imprimir: BRRR BRRR" + RESET);
            Thread.sleep(6000);
            System.out.println(YELLOW_BOLD + nombreImpresora + " terminó de imprimir" + RESET);
            semRetirarCopiaA.release();
            System.out.println(YELLOW_BOLD + nombreImpresora + " está lista para imprimir otro documento." + RESET);
        } catch (InterruptedException e) { }
    }
    
    public void retirarCopiaA(String nombreEmpleado){
        try {
            semRetirarCopiaA.acquire();
            System.out.println(GREEN_BOLD + nombreEmpleado + " retira su documento de ImpresoraA" + RESET);
        } catch (InterruptedException e) { }
    }
    
        // ImpresorasB.
    public void agregarImpresionB() {
        lockCopiaB.lock();      // Mutex para sumar copias pendientes.
        numeroCopiasPendienteB++;
        if (numeroCopiasPendienteB == 1){
            semCopiaPendienteB.release();
        }
        lockCopiaB.unlock();
    }
    
    public void imprimirB(String nombreImpresora) {
        try {
            semCopiaPendienteB.acquire();
            lockCopiaB.lock();         // Mutex para restar copias hechas.
            numeroCopiasPendienteB--;  // Quita, del contador, la copia que acaba de hacer.
            if (numeroCopiasPendienteB > 0) {
                semCopiaPendienteB.release();
            }
            lockCopiaB.unlock();
            System.out.println(nombreImpresora + " empieza a imprimir: BRRR BRRR" + RESET);
            Thread.sleep(6000);
            System.out.println(nombreImpresora + " terminó de imprimir" + RESET);
            semRetirarCopiaB.release();

            System.out.println(nombreImpresora + " está lista para imprimir otro documento." + RESET);
        } catch (InterruptedException e) {}
    }
    
        public void retirarCopiaB(String nombreEmpleado){
        try {
            semRetirarCopiaB.acquire();
            System.out.println(GREEN_BOLD + nombreEmpleado + " retira su documento de ImpresoraB" + RESET);
        } catch (InterruptedException e) { }
    }
    

}
