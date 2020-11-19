
package TP4SeccionCritica.Ejercicio12;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Jaula jaulita = new Jaula();
        
        // Creo e inicio hilos.
        for (int i = 0; i < 10; i++) {
            new Thread(new Hamster("Hamster"+i,jaulita)).start();
        }
    }
    
    
/*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
        Hamsters.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
        Jaula.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
        Cuando un hamster quiere usar el plato de comida.
        Cuando un hasmter quiere usar la rueda.
        Cuando un hasmter quiere usar la hamaca
    Observaciones:
        Si un hamster no consigue ninguno de estos, se pone a jugar con los otros hamster que tampoco consiguieron los otros objetos.
        Creo que podría usar directamente un lock(); en vez de los if (tryLock()) y evitar usar recursión. Al fin y al cabo no espe-
        -cifica lo que hacen los hamster mientras no usar algún objeto.
*/
    
}
