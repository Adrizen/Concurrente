
package TP4SeccionCritica.Ejercicio7;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    
    public static void main(String[] args) {
        Oficina oficina = new Oficina();
        
        for (int i = 0; i < 6; i++) {
            new Thread(new Usuario("Usuario"+i,oficina)).start();
        }
        
        for (int i = 0; i < 2; i++) {
            new Thread(new ImpresoraA("ImpresoraA" + i, oficina)).start();
        }
        
        for (int i = 0; i < 2; i++) {
            new Thread(new ImpresoraB("ImpresoraB" + i, oficina)).start();
        }

    }
    
    
    
    /*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
        Usuario, Impresora.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
        Oficina.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
        A la hora de imprimir algún documento en una impresora tipo A o B
Observaciones:
        Una misma impresora no puede imprimir más de un documento a la vez.
        El tipo C se puede imprimir en cualquiera.
*/
    
}
