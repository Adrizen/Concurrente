
package TP4SeccionCritica.EjercicioBarberoDormilon;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        
        Barberia barberia = new Barberia(); // Recurso compartido.
        //BarberiaSinSalaEspera barberia = new BarberiaSinSalaEspera(); //Recurso compartido.
        
        Cliente cliente1 = new Cliente(barberia);
        Cliente cliente2 = new Cliente(barberia);
        Cliente cliente3 = new Cliente(barberia);
        Cliente cliente4 = new Cliente(barberia);
        
        Barbero barbero = new Barbero(barberia);
        
        Thread hilo1 = new Thread(barbero, "Barbero");
        Thread hilo2 = new Thread(cliente1,"Cliente1");
        Thread hilo3 = new Thread(cliente2,"Cliente2");
        Thread hilo4 = new Thread(cliente3,"Cliente3");
        Thread hilo5 = new Thread(cliente4,"Cliente4");
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
    
    /*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
        Barbero y Cliente.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
        El sillon parece ser el recurso compartido.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
        Debe haber sincronización cuando el Cliente llega y despierta al barbero para sentarse y que le empiece a cortar el lope.
        Luego cuando el barbero termina de cortar el lope, le avisa al cliente que se puede ir (despues de garpar)
 */
    
}
