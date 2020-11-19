
package TP4SeccionCritica.EjercicioBarberoDormilon;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */

// La barbería tiene un sala de espera y cada cliente que quiere cortarse suma 1 al contador "clientesEsperando".
public class Barberia {
    private Semaphore semSillon; // Indica si el sillón está ocupado con un cliente. 1 = está libre. Usado para exclusión mutua del sillón.                           
    private Semaphore semBarbero; // Indica si el barbero está durmiendo. 0 = está durmiendo. Usado para indicarle al barbero cuando empezar a trabajar. 
    private Semaphore semSalida; // Indica si el barbero terminó. 0 = el barbero no terminó. Usado para saber si el Cliente se puede retirar se la silla.

    public Barberia(){
        this.semSillon = new Semaphore(1);
        this.semBarbero = new Semaphore(0);
        this.semSalida = new Semaphore(0);
    }

    // Si el barbero está ocupado con un cliente, los otros esperan su turno en una silla. Luego se pelean por ser los proximos en atenderse.
    public void entrarBarberia() {
        try {
            semSillon.acquire();
            System.out.println("El " + Thread.currentThread().getName() + " le indica al barbero que quiere cortarse el pelo");
            System.out.println("El "+ Thread.currentThread().getName() +" se empieza a acomodar en la silla...");
            semBarbero.release();   // Da permiso al barbero a proceder.
            semSalida.acquire();    // Intenta irse pero no lo logra hasta que el barbero termine.
            System.out.println("El "+ Thread.currentThread().getName() +" se para, paga y se va.");
            semSillon.release();
        } catch (InterruptedException e) {  }
    }
    
    
    // El barbero trabaja en los clientes hasta que ya no haya más en la sala de espera.
    public void cortarPelo() {
        try {
            semBarbero.acquire();
            while (clientesEsperando > 0) {
                System.out.println("El barbero se espabila y comienza a trabajar");
                Thread.sleep(1000);
                System.out.println("El barbero recorta las patillas");
                Thread.sleep(3000);     //El barbero está laburando
                System.out.println("El barbero desmecha el flequillo");
                Thread.sleep(2000);     //El barbero está laburando
                System.out.println("El barbero termina");
                clientesEsperando--;
                semSalida.release();    //El barbero terminó y le indica al cliente que se puede ir.
                if (clientesEsperando > 0){
                    semBarbero.acquire();
                }
            }
        } catch (InterruptedException e) { }
    }

}
