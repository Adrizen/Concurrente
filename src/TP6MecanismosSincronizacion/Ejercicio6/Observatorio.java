package TP6MecanismosSincronizacion.Ejercicio6;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Observatorio {
    private int capacidad;
    private int visitantesActuales;
    private int investigadoresActuales;
    private ReentrantLock lockObservatorio;
    private int investigacionesRealizadas;
    private final int INVESTIGACIONES_MAXIMAS;
    private int visitasRealizadas;
    private final int VISITAS_MAXIMAS;
    private int mantenimientosRealizados;
    private final int MANTENIMIENTOS_MAXIMOS;
    
    public Observatorio(int maximoCapacidad, int maximoInvestigaciones, int maximoVisitas , int maximoMantenimientos){
        capacidad = maximoCapacidad;
        visitantesActuales = 0;
        investigadoresActuales = 0;
        lockObservatorio = new ReentrantLock();
        
        investigacionesRealizadas = 0;
        INVESTIGACIONES_MAXIMAS = maximoInvestigaciones;
        
        visitasRealizadas = 0;
        VISITAS_MAXIMAS = maximoVisitas;
        
        mantenimientosRealizados = 0;
        MANTENIMIENTOS_MAXIMOS = maximoMantenimientos;
    }
    
    // Método de los Visitantes.
    public synchronized void entrarVisitante(String nombreVisitante, String tipo) throws InterruptedException{
        while (visitantesActuales >= capacidad || investigadoresActuales > 0 || visitasRealizadas >= VISITAS_MAXIMAS){    
            this.wait(); // Si no hay capacidad, espero. (Y bajo otras condiciones, también espero)
        }
        visitasRealizadas++;    // Cada visitante cuenta como una visita que se realizó. (No se cuenta todo el grupo, si no cada uno).
        visitantesActuales++;
    }
    
    public void estudiarEstrellas(String nombreVisitante) throws InterruptedException{
        System.out.println(nombreVisitante + " estudia las estrellas.");
        Thread.sleep(3000);
    }
    
    public synchronized void salirVisitante(String nombreVisitante){
        System.out.println(nombreVisitante + " sale del observatorio");
        visitantesActuales--;
        this.notifyAll();
        investigacionesRealizadas = 0;  // Reinicio contador.
    }
    
    // Métodos de los Investigadores.
    public synchronized void entrarInvestigador(String nombreInvestigador) throws InterruptedException{
        while (visitantesActuales > 0 || investigadoresActuales >= capacidad || investigacionesRealizadas >= INVESTIGACIONES_MAXIMAS){ 
            this.wait();    // Si hay visitantes, no puedo entrar. (Y bajo otras condiciones, también espero)
        }
        investigacionesRealizadas++;    // Cada investigador hace su investigacion propia y suma al contador. (No se cuenta todo el grupo, si no cada uno).
        investigadoresActuales++;
    }
    
    public void investigar(String nombreInvestigador) throws InterruptedException{
        System.out.println(nombreInvestigador + " investiga.");
        Thread.sleep(3000); // Tiempo de investigación.
    }
    
    public synchronized void salirInvestigador(String nombreInvestigador){
        System.out.println(nombreInvestigador + " terminó de investigar y se va.");
        investigadoresActuales--;
        this.notifyAll();
        visitasRealizadas = 0;  // Reinicio contador.
    }

    // Método de Mantenimiento.
    
}

