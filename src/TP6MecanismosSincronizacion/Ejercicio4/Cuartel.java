
package TP6MecanismosSincronizacion.Ejercicio4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Cuartel {
    private final int CAPACIDAD_MAXIMA;         // Capacidad máxima del cuartel.
    private ReentrantLock cuartel;
    private Condition hayLugar;                 // Condición usada para cuando se libera un lugar en el cuartel.
    private Condition mostradores;              // Condición usada para cuando se libera una bandeja de comida del mostrador.
    private Condition mostradoresPostre;        // Condición usada para cuando se libera una 
    private Condition abridores;
    private int cantidadSoldadosActuales;
    private int cantidadAbridoresActuales;
    private int cantidadMAActuales;
    private int cantidadMPActuales;
    
    // " toma una bandeja con comida de uno de los 5 mostradores (mostradorAlmuerzo) que existen para tal efecto"...
        // Yo supongo que hay solo una bandera por mostrador y la devuelve al irse.
    // "En el caso que el soldado quiera postre, debe dirigirse a uno de los 3 mostradores que lo despachan (mostradorPostre)""
        // Idem anterior, hay una cantidad limitada de bandejas postre que devuelve al irse.
    
    
    public Cuartel(int max, int cantidadMostradoresAlmuerzo, int cantidadMostradoresPostre,int cantidadAbridores){
        CAPACIDAD_MAXIMA = max;
        cuartel = new ReentrantLock(true);
        mostradores = cuartel.newCondition();
        mostradoresPostre = cuartel.newCondition();
        abridores = cuartel.newCondition();
        hayLugar = cuartel.newCondition();
        cantidadSoldadosActuales = 0;
        cantidadAbridoresActuales = cantidadAbridores;
        cantidadMAActuales = cantidadMostradoresAlmuerzo;
        cantidadMPActuales = cantidadMostradoresPostre;
    }
    
    public void entrar(String nombreSoldati) throws InterruptedException{
        cuartel.lock();
        while (cantidadSoldadosActuales >= CAPACIDAD_MAXIMA){
            System.out.println(nombreSoldati + ": ESPERA FUERA DEL COMEDOR.");
            hayLugar.await();
        }
        cantidadSoldadosActuales++;
        cuartel.unlock();
        System.out.println(nombreSoldati + ": LOGRA ENTRAR AL COMEDOR.");
    }
    
    public void tomarComida(String nombreSoldati) throws InterruptedException{
        cuartel.lock();
        while (cantidadMAActuales <= 0){
            mostradores.await();
        }
        cantidadMAActuales--;
        cuartel.unlock();
        Thread.sleep(2000); // Tiempo que tarda en obtener la comida.
        System.out.println(nombreSoldati + ": LOGRA TOMAR UNA BANDEJA DE COMIDA.");
    }
    
    // Si el soldado quiere gaseosa.
    public void tomarAbridor(String nombreSoldati) throws InterruptedException{
        cuartel.lock();
        while (cantidadAbridoresActuales <= 0){
            abridores.await();
        }
        cantidadAbridoresActuales--;
        System.out.println(nombreSoldati + ": TOMA UN ABRIDOR");
        cuartel.unlock();
        Thread.sleep(3000); // Tiempo que tarda en agarrar el abridor y usarlo.
        System.out.println(nombreSoldati + ": ABRIÓ SU GASEOSA.");
        cuartel.lock();
        cantidadAbridoresActuales++;
        abridores.signalAll();
        cuartel.unlock();
    }
    
    public void tomarPostre(String nombreSoldati) throws InterruptedException{
        cuartel.lock();
        while (cantidadMPActuales <= 0){
            mostradoresPostre.await();
        }
        cantidadMPActuales--;
        cuartel.unlock();
        System.out.println(nombreSoldati + ": LOGRA TOMAR UNA BANDEJA DE POSTRE.");
        Thread.sleep(2000); // Tiempo en tomar el postre y colocarlo.
    }
    
    public void comer(String nombreSoldati) throws InterruptedException{
        System.out.println(nombreSoldati + ": COME.");
        Thread.sleep(6000);
        System.out.println(nombreSoldati + ": TERMINA DE COMER.");
    }
    
    public void salir(String nombreSoldati, boolean quierePostre){
        cuartel.lock();
        cantidadSoldadosActuales--;
        
        cantidadMAActuales++;
        if (quierePostre) {
            cantidadMPActuales++;
            System.out.println(nombreSoldati + ": DEVUELVE SU BANDEJA DE POSTRE.");
        }
        System.out.println(nombreSoldati + ": DEVUELVE SU BANDEJA DE COMIDA Y SE VA.");
        mostradores.signalAll();
        mostradoresPostre.signalAll();
        hayLugar.signalAll();
        cuartel.unlock();
    }
    
}
