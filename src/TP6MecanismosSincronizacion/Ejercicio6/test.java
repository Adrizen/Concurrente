
package TP6MecanismosSincronizacion.Ejercicio6;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Observatorio observatorio = new Observatorio(4,4,5,2); // (maximoCapacidad,maximoInvestigaciones,maximoVisitas,maximoMantenimientos)
        
        // Visitantes sin silla de ruedas.
        for (int i = 0; i < 3; i++) {
            new Thread(new Visitante("Visitante" + i, observatorio, "")).start();
        }

        // Visitantes con sillas de rueda.
        for (int i = 0; i < 2; i++) {
            new Thread(new Visitante("Visitante" + i, observatorio, "sillaDeRuedas")).start();
        }
        
        // Investigadores.
        for (int i = 0; i < 5; i++) {
            new Thread(new Investigador("Investigador" + i, observatorio)).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Mantenimiento("Mantenimiento" + i, observatorio)).start();
        }
        
    }
    
    /*
Como abordar este tipo de problemas:
Identificar el tipo de sincronización:
	-Sincronización por competencia/sincronización por cooperación.
    Sincro. por cooperación.
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
    Visitantes, Mantenimiento, Investigador.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
*/
    
}
