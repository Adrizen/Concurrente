package TP5SemaforoGenerico.Ejercicio3;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        TrenTuristico trenTuristico = new TrenTuristico(3);
        
        // Creo, inicializo y arranco el hilo ControlTren.
        for (int i = 0; i < 1; i++) {
            new Thread(new ControlTren(trenTuristico)).start();
        }
        
        // Creo, inicializo y arranco el hilo de Vendedor.
        for (int i = 0; i < 1; i++) {
            new Thread(new VendedorTicket("Vendedor"+i,trenTuristico)).start();
        }
        
        // Creo, inicializo y arranco los hilos Pasajeros.
        for (int i = 0; i < 10; i++) {
            new Thread(new Pasajero("Pasajero"+i,trenTuristico)).start();
        }
    }
    /*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
    un huilo vendedorTicket, un hilo controlTren, N hilos Pasajeros (con N > cantidadAsientos)
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
    TrenTuristico.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
    Cuando un Pasajero quiere comprar un ticket.
    Cuando suben Pasajeros.
    Cuando bajan Pasajeros.
    Cuando el tren sale.
    Cuando el tren para.
*/
}
