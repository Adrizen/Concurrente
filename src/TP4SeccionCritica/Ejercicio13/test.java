
package TP4SeccionCritica.Ejercicio13;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Confiteria confiteria = new Confiteria();
        Empleado[] empleados = new Empleado[6];
        Thread[] hilosEmpleados = new Thread[6];
        
        Mozo mozo = new Mozo("Illidan",confiteria);
        Thread hiloMozo = new Thread(mozo);
         
        //Creo los hilos.
        for (int i = 0; i < empleados.length; i++) {
            empleados[i] = new Empleado("Sr. Empleado"+i,confiteria);
            hilosEmpleados[i] = new Thread(empleados[i]);
        }
        
        //Arranco los hilos.
        hiloMozo.start();
        
        for (int i = 0; i < hilosEmpleados.length; i++) {
            hilosEmpleados[i].start();
        }
    }
    
}

/*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
        Mozo, Empleados.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
        Confitería y 700 semáforos.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
        Empleado se fija si hay un lugar disponible (hay uno solo), si lo hay se queda a que le sirvan bebida.
        Empleado pide y espera que el Mozo termine de hacer su bebida.
        Mozo sirve al cliente y le indica que puede empezar a tomar.
        Empleado termina de tomar, deja la silla libre y vuelve a trabajar.
Observaciones:
        Mozo se dedica a su hobbie si no hay Empleados.
        Silvia en Discordia said: "El mozo se encarga de servir bebidas y el cocinero se encarga de la comida." 
            esto se nos escapo, no iba en el ej 13, es del 14.
        También dijo que en este ejercicio no se modela al Cocinero, porque no existe:
            "la interacción se da entre el Mozo y el Empleado".
        Si bien hay comida, solo se modela la interacción Empleado-Mozo.
*/