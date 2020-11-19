
package TP4SeccionCritica.Ejercicio9;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Taxi {
    //Colores para los println
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    
    //Semaforos.
    private Semaphore semTaxi;
    private Semaphore semTaxista;
    private Semaphore semSalida;
    private int turnosPorDia;
    
    
    public Taxi(){
        semTaxi = new Semaphore(1);             // Indica si el taxi está disponible. 1 = disponible.
        semTaxista = new Semaphore(0);          // Indica si el taxista está manejando el tacho. 0 = no maneja.
        semSalida = new Semaphore(0);           // Indica si el cliente se puede bajar. 0 = no puede bajar.
        this.turnosPorDia = 4;                  // Numero de clientes que el taxista puede atender en su turno antes de que acabe.
    }
    
    // Si el cliente encuentra el taxi disponible, se sube y viaja. Si no se va a pasear.
    public void buscarYSubirseTaxi(){
        try{
            if (semTaxi.tryAcquire()){
                System.out.println("El "+Thread.currentThread().getName()+" se sube al taxi, despierta al taxista y le indica su destino.");
                semTaxista.release();
                semSalida.acquire();
                System.out.println("El cliente agradece, paga y se va.");
                semTaxi.release();
            } else {
                pasear();
            }
        } catch (InterruptedException e) {}
    }
    
    // Cuando un cliente no encuentra un taxi se va a pasear por ahí a buscar uno.
    public void pasear(){
        try{
            System.out.println(ANSI_RED + Thread.currentThread().getName()+" no encontró un taxi disponible y se fue a pasear mientras busca." + ANSI_RESET);
            Thread.sleep(10000);    //El cliente pasea buscando un tacho.
            buscarYSubirseTaxi();
        } catch(InterruptedException e) {}
    }
    
    // Cuando un cliente llega al taxi, el taxista se despierta y maneja.
    public void conducir() {
        try {
            if (turnosPorDia > 0) {
                semTaxista.acquire();
                turnosPorDia--;
                System.out.println("El taxista se espabila y comienza a manejar.");
                Thread.sleep(5000);     // El taxista maneja a destino.
                System.out.println(ANSI_GREEN + "El taxista le indica al pasajero que llegaron." + ANSI_RESET);
                semSalida.release();
                dormir();
            }
        } catch (InterruptedException e) {  }
    }
    
    //Cuando el taxista termina un viaje, se duerme un ratito.
    public void dormir(){
        try{
            System.out.println("El taxista aprovecha que se fue el cliente para dormirse una siestica");
            Thread.sleep(5000);     // El taxista duerme.
            conducir();
        } catch(InterruptedException e) {}
    }
    
    
}

/*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
        Taxista y pasajero.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
        El taxi parece ser el recurso compartido.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
        Debe haber sincronización cuando el pasajero llega y entra al taxi, para avisarle al taxista que se despierte.
        Luego cuando el taxista llega a donde le pidió el pasajero, este le avisa para que se baje (y le pague). Así el pasajero no pregunta todo el rato si llegaron.
 */