
package TP4SeccionCritica.Ejercicio14;


/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Confiteria confiteria = new Confiteria();
        Mozo mozito = new Mozo("Illidan",confiteria);
        Thread hiloMozo = new Thread(mozito);
        Cocinero cocinero = new Cocinero("Ragnaros",confiteria);
        Thread hiloCocinero = new Thread(cocinero);
        
        // Creo e inicio hilos de Empleados.
        for (int i = 0; i < 3; i++) {
            new Thread(new Empleado("Empleado"+i,confiteria)).start();
        }
        
        hiloMozo.start();
        hiloCocinero.start();
    }
    
}

/*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
        Empleados, Mozo y Cocinero.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
        Confitería.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
        Empleado se fija si hay un lugar disponible (hay 2), si lo hay se queda a que le sirvan.
        Empleado pide bebida y/o comida.
        Mozo sirve la bebida (si Empleado la pidió) y le indica que puede pedir su comida (si quiere).
        Cocinero prepara la comida y cuando termina le avisa al Empleado para que la vaya a retirar.
        Empleado termina, deja una silla libre y vuelve a trabajar.
Observaciones:
        Mozo y Cocinero se dedican a su hobbie si no hay Empleados.
        Empleado puede pedir comida, bebida o ambas.
        A la hora de pedir el menú:
            1 = solo bebida.
            2 = solo comida.
            3 = comida y bebida.

*/