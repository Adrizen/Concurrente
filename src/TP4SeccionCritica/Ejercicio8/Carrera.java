
package TP4SeccionCritica.Ejercicio8;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Carrera {

    public static void main(String[] args) {
        long tiempoInicio;
        long tiempoFin;

        Atleta[] atleta = new Atleta[4];
        Thread[] hilos = new Thread[4];
        Testigo testigo = new Testigo();
        
        for (int i = 0; i < atleta.length; i++) {
            atleta[i] = new Atleta(testigo);
            hilos[i] = new Thread(atleta[i],"Atleta" + i);
        }
        
        tiempoInicio = System.currentTimeMillis();
        
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
        
        for (int i = 0; i < hilos.length; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {  }
        }
        
        tiempoFin = System.currentTimeMillis();
        
        System.out.println("Tiempo total de carrera: "+ ((tiempoFin - tiempoInicio)/1000)+" segundos" );
    }
    
}
