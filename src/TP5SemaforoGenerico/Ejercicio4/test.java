
package TP5SemaforoGenerico.Ejercicio4;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        int numeracionAviones = 0;
        Aeropuerto aeropuerto = new Aeropuerto();
        TorreControl torre = new TorreControl(aeropuerto);
        
        // Creo, inicializo y arranco los hilos Avión que quieren aterrizar.
        for (int i = 0; i < 3; i++) {
            new Thread(new Avion("Avion" + numeracionAviones, "aterrizar", aeropuerto)).start();
            numeracionAviones++;
        }
        
        // Creo, inicializo y arranco los hilos Avión que quiere despegar.
        for (int i = 0; i < 3; i++) {
            new Thread(new Avion("Avion"+numeracionAviones, "despegar", aeropuerto)).start();
            numeracionAviones++;
        }
        
        Thread hiloTorre = new Thread(torre);
        hiloTorre.start();
    }
    /*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
    Aviones, TorreControl
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
    Aeropuerto.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
    Si dos aviones quieren aterrizar y despegar, se le da prioridad al aterrizaje.
    Observaciones:
    Cada 10 aterrizajes un despegue tendría prioridad para luego volver a contar desde 0.
    Aterrizar y despegar requieren un tiempo.
*/
}
