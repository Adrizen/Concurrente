
package TP6MecanismosSincronizacion.Ejercicio6;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Observatorio observatorio = new Observatorio(4,4,2,-1);
        
        
     for (int i = 0; i < 2; i++) {
            new Thread(new Visitante("Visitante"+i,observatorio,"null")).start();
        }
        
        for (int i = 0; i < 2; i++) {
            new Thread(new Investigador("Investigador"+i,observatorio)).start();
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
