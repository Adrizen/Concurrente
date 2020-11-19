package TP4SeccionCritica.EjercicioBarberoDormilon;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class BarberiaSinSalaEspera {
    private Semaphore semSillon; // Indica si el sillón está ocupado con un cliente. 1 = está libre. Usado para exclusión mutua del sillón.                           
    private Semaphore semBarbero; // Indica si el barbero está durmiendo. 0 = está durmiendo. Usado para indicarle al barbero cuando empezar a trabajar. 
    private Semaphore semSalida; // Indica si el barbero terminó. 0 = el barbero no terminó. Usado para saber si el Cliente se puede retirar se la silla.
    private int sillas;

    public BarberiaSinSalaEspera(){
        this.sillas = 3;
        this.semSillon = new Semaphore(1);
        this.semBarbero = new Semaphore(0);
        this.semSalida = new Semaphore(0);
    }

    // En esta versión, si el barbero está ocupado con un cliente, los otros se van y no esperan.
    public void entrarBarberiaSinEspera() {
        try {
            if (semSillon.tryAcquire()) {
                System.out.println("El " + Thread.currentThread().getName() + " encuentra la barbería vacia, entra e intenta despertar al barbero para que le corte el pelo");
                System.out.println("El cliente se empieza a acomodar en la silla...");
                semBarbero.release();   // Da permiso al barbero a proceder.
                semSalida.acquire();    // Intenta irse pero no lo logra hasta que el barbero termine.
                System.out.println("El cliente se para, paga y se va.");
                semSillon.release();
            }
        } catch (InterruptedException e) {  }
    }
 
    // En esta versión el barbero atiende unicamente al primer cliente que entra a la barbería.
    public void cortarPeloSinEspera() {
        try {
            semBarbero.acquire();
            System.out.println("El barbero se espabila y comienza a trabajar");
            Thread.sleep(1000);
            System.out.println("El barbero recorta las patillas");
            Thread.sleep(3000);     //El barbero está laburando
            System.out.println("El barbero desmecha el flequillo");
            Thread.sleep(2000);     //El barbero está laburando
            System.out.println("El barbero termina");
            semSalida.release();    //El barbero terminó y le indica al cliente que se puede ir.
        } catch (InterruptedException e) {  }
    }
}