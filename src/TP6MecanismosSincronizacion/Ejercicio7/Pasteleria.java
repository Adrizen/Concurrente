
package TP6MecanismosSincronizacion.Ejercicio7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import static Colores.Colores.*;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Pasteleria {
    private final int PESO_MAXIMO;      // Peso máximo que pueden tener las cajas.
    private ReentrantLock hornos;
    private int pesoCajaActual;         // El peso actual de la caja que se está llenando.
    private Condition retirarCaja;      // Para avisarle al "Reponedor" que tiene que quitar la caja actual y colocar una nueva.
    private Condition pastelNuevo;      // Para indicarle a los "Empaquetadores" que hay un pastel recién salido del horno. (chuyo)
    private Condition cajaNueva;        // Para indicarle a los "Empaquetadores" que el "Reponedor" puso una nueva caja vacia.
    private Cola cinta;                 // La cinta donde se colocan los pasteles, se modela como una cola porque los empaquetadores
                                        // van tomando los pasteles siempre más cercanos al mostrador.
    private boolean hayPasteles;        // Se usa para indicar cuando hay pasteles en la cinta disponibles para ser guardados en cajas.
                                        // si me dejaran usar semáforos, se podría solucionar con eso y no con un booleano.
    
    private ReentrantLock reponedor;    // Lock usado para la interacción entre 'Reponedor' y 'Empaquetador' para quitar y colocar nuevas cajas.
                                        // No se usa el lock 'general' porque de esta forma los 'Hornos' podrían seguir trabajando 
                                        // (ya que tienen otro lock).
    private int empaquetadorasTotales;
    private int pastelesTomados;
    private Condition esperarRestoEmpaquetadoras;
    
    private ReentrantLock mutexEmpaquetadoras;
    
    public Pasteleria(int pesoMax){
        PESO_MAXIMO = pesoMax;
        hornos = new ReentrantLock();
        reponedor = new ReentrantLock();
        pesoCajaActual = 0;
        retirarCaja = reponedor.newCondition();
        pastelNuevo = hornos.newCondition();
        cajaNueva = reponedor.newCondition();
        cinta = new Cola();
        hayPasteles = false;
        
        empaquetadorasTotales = 0;
        pastelesTomados = 0;
        esperarRestoEmpaquetadoras = reponedor.newCondition();
        
        mutexEmpaquetadoras = new ReentrantLock();
    }
    
    // Métodos de los Hornos (3).
    public void producir(String nombreHorno, String tipo, int peso){
        hornos.lock();
        System.out.println(CYAN_BOLD + nombreHorno + " TIPO: " + tipo + " produce un pastel de: " + peso + " kilo(s)." + RESET);
        cinta.poner(peso);  // Coloca el pastel en la cinta. (Modelada como una cola).
        hayPasteles = true;
        System.out.println(cinta.toString());
        pastelNuevo.signal();
        hornos.unlock();
    }

    // Métodos de los Empaquetadores (2).
    public synchronized void encenderMaquinitas() {
        empaquetadorasTotales++;
    }

    public void esperarPastel(String nombreEmpaquetador) throws InterruptedException {
        hornos.lock();
        while (!hayPasteles){
            pastelNuevo.await();
        }
        hornos.unlock();
    }

    public void colocarPastel(String nombreEmpaquetador,int pesoPastel) throws InterruptedException { // Los brazos empaquetadores toman un pastel y lo colocan en una caja.
        mutexEmpaquetadoras.lock();
        pastelesTomados++;
        while ((pesoCajaActual + pesoPastel) > PESO_MAXIMO) {   // Si entra al while, no pudo colocar el pastel porque supera el peso máximo.
            System.out.println(RED_BOLD + nombreEmpaquetador + ": EL PASTEL SUPERA EL PESO MÁXIMO DE LA CAJA." + RESET);
            System.out.println(RED_BOLD + nombreEmpaquetador + ": PESO DE LA CAJA ACTUAL: " + pesoCajaActual + ". PESO DEL PASTEL TOMADO: " + pesoPastel + "." + RESET);
            reponedor.lock();
            if (pastelesTomados == empaquetadorasTotales){  // Solo si todas las empaquetadoras fueran preguntadas por su peso y no pueden colocar
                                                            // sus pasteles. Se solicita una nueva caja.
                System.out.println(nombreEmpaquetador + " SOLICITA UNA NUEVA CAJA.");
                retirarCaja.signal();
                cajaNueva.await();
            } else {
                System.out.println("Falta(n) " + (empaquetadorasTotales - pastelesTomados) + " maquina(s) por revisar." );
                mutexEmpaquetadoras.unlock();
                esperarRestoEmpaquetadoras.await(); // El resto de empaquetadoras espera a que las demas sean consultadas. "Se intenta llenar las cajas lo máximo posible"
                mutexEmpaquetadoras.lock();
            }
            esperarRestoEmpaquetadoras.signal();    // Antes de perder el lock, van avisando de a una a las demas reponedoras.
            reponedor.unlock();
        }
        System.out.println(GREEN_BOLD + nombreEmpaquetador + " coloca el pastel de peso " + pesoPastel + " en la caja." + RESET);
        pesoCajaActual = pesoCajaActual + pesoPastel;
        pastelesTomados--;
        mutexEmpaquetadoras.unlock();
    }
    
    public int tomarPastel(String nombreEmpaquetador){
        hornos.lock();
        int pesoPastel = 0;
        pesoPastel = (int) cinta.obtenerFrente();
        cinta.sacar();      // Quita el pastel de la cinta.
        if (cinta.esVacia()) {   // Si no quedan pasteles en la cinta, seteo el boolean en false para parar a los "Empaquetadores" en el await() de arriba.
            hayPasteles = false;
        }
        System.out.println(nombreEmpaquetador + " toma un pastel con un peso de " + pesoPastel + " de la cinta.");
        hornos.unlock();
        return pesoPastel;
    }
    
    // Métodos del Reponedor (1).
    public void retirarCaja(String nombreReponedor) throws InterruptedException{
        reponedor.lock();
        retirarCaja.await();
        System.out.println(YELLOW_BOLD + nombreReponedor + " RETIRA UNA CAJA." + RESET);
        reponedor.unlock();
    }

    public void reponerCaja(String nombreRetirador) {
        reponedor.lock();
        pesoCajaActual = 0;
        System.out.println(YELLOW_BOLD + nombreRetirador + " REPUSO UNA CAJA VACIA." + RESET);
        cajaNueva.signal();
        reponedor.unlock();
    }
    
    
}
