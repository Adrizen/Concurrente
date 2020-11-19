
package EjerciciosTeoria.Parte4.Ejercicio3;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    
    public static void main(String[] args) {
        Libro libro = new Libro(2,1);   // Libro(Cantidad lectores máxima, cantidad escritores máxima).
        
        for (int i = 0; i < 3; i++) {
            new Thread(new Lector("Lector"+i,libro)).start();
        }
        
        for (int i = 0; i < 2; i++) {
            new Thread(new Escritor("Escritor"+i,libro)).start();
        }
        
    }
    
    /* Como abordar este tipo de problemas:
Identificar el tipo de sincronización:
	-Sincronización por competencia/sincronización por cooperación.
    Sincronización por competencia?.
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
    Escritor y Lector.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
    Libro.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.

Observaciones:
    Puede haber varios lectores en el libro al mismo tiempo, pero no varios escritores.
    
    
    
    
*/
    
}
