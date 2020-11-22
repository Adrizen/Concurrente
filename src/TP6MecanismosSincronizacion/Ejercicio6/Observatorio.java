package TP6MecanismosSincronizacion.Ejercicio6;
import Timestamp.TSO;

import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Observatorio {
    private int capacidad;
    private int visitantesActuales;
    private int investigadoresActuales;
    private int investigacionesRealizadas;
    private final int INVESTIGACIONES_MAXIMAS;
    private int visitasRealizadas;
    private final int VISITAS_MAXIMAS;          // Limite de visitas para que los otros hilos no mueran de inanición.
    private int mantenimientosRealizados;
    private final int MANTENIMIENTOS_MAXIMOS;
    private final int CAPACIDAD_MAXIMA;
    private int visitantesActualesConSilla;
    private int mantenimientoActuales;         // Personas de mantenimiento dentro del museo.
    
    public Observatorio(int maximoCapacidad, int maximoInvestigaciones, int maximoVisitas , int maximoMantenimientos){
        capacidad = maximoCapacidad;
        CAPACIDAD_MAXIMA = maximoCapacidad;
        
        visitantesActuales = 0;
        investigadoresActuales = 0;
        
        investigacionesRealizadas = 0;
        INVESTIGACIONES_MAXIMAS = maximoInvestigaciones;
        
        visitasRealizadas = 0;
        VISITAS_MAXIMAS = maximoVisitas;
        
        mantenimientosRealizados = 0;
        MANTENIMIENTOS_MAXIMOS = maximoMantenimientos;
    }
    
    // Nota: Visitantes pueden estar con otros siempre que no se supere la capacidad máxima (afectada por Visitantes con silla de ruedas).
    //       Manteminiento pueden estar con otros siempre que no se supere la capacidad máxima.
    //       Investigadores tienen que estar completamente solos. (Solo uno)
    //       No pueden mezclarse distintos tipos de Personas al mismo tiempo en el observatorio.
    //       Hay limites para que no haya muerte por inanición.
    
    // Método de los Visitantes.
    public synchronized void entrarVisitante(String nombreVisitante, String tipo) throws InterruptedException{
        while (visitantesActuales >= capacidad || investigadoresActuales > 0 || visitasRealizadas >= VISITAS_MAXIMAS || mantenimientoActuales >= 1){    
            this.wait(); // Si no hay capacidad, hay investigadores o se superó el n° máximo de visitas, espero.
        }
        System.out.println(TSO.mostrarTiempo() + GREEN_BOLD + " - " + nombreVisitante + " entra. TIPO: " + tipo + RESET);
        if (tipo.equals("sillaDeRuedas")){
            visitantesActualesConSilla++;
            if (visitantesActualesConSilla == 1) { // El primer visitante con silla de ruedas reduce la capacidad.
                capacidad = capacidad / 2;
                System.out.println("CAPACIDAD REDUCIDA.");
            }
        }
        visitasRealizadas++;    // Cada visitante cuenta como una visita.
        visitantesActuales++;
    }
    
    public void estudiarEstrellas(String nombreVisitante) throws InterruptedException{
        System.out.println(nombreVisitante + " estudia las estrellas.");
        Thread.sleep(6000);
    }
    
    public synchronized void salirVisitante(String nombreVisitante, String tipo) {
        System.out.println(RED_BOLD + nombreVisitante + " sale del observatorio" + RESET);
        visitantesActuales--;
        if (tipo.equals("sillaDeRuedas")) {
            visitantesActualesConSilla--;
            if (visitantesActualesConSilla == 0) {  // Es el último en irse entonces reestablece la capacidad total.
                capacidad = CAPACIDAD_MAXIMA;
                System.out.println("SE HA REESTABLECIDO LA CAPACIDAD.");
            }
        }
        this.notifyAll();
        investigacionesRealizadas = 0;  // Reinicio contador.
        mantenimientosRealizados = 0;
    }
    
    // Métodos de los Investigadores. Los investigadores tienen que estar solo según la profe, solo UN como máximo.
    public synchronized void entrarInvestigador(String nombreInvestigador) throws InterruptedException{
        while (visitantesActuales > 0 || investigadoresActuales >= 1 || investigacionesRealizadas >= INVESTIGACIONES_MAXIMAS || mantenimientoActuales >= 1){ 
            this.wait();    // Si hay visitantes, otro investigador o si se superó el n° máximo de investigaciones, espero.
        }
        investigacionesRealizadas++;    // Cada investigador hace su investigación propia y suma al contador.
        investigadoresActuales++;
    }
    
    public void investigar(String nombreInvestigador) throws InterruptedException{
        System.out.println(CYAN_BOLD + nombreInvestigador + " investiga." + RESET);
        Thread.sleep(3000); // Tiempo de investigación.
    }
    
    public synchronized void salirInvestigador(String nombreInvestigador){
        System.out.println(nombreInvestigador + " terminó de investigar y se va.");
        investigadoresActuales--;
        this.notifyAll();
        visitasRealizadas = 0;  // Reinicio contador.
        mantenimientosRealizados = 0;
    }

    // Método de Mantenimiento.
    public synchronized void entrarMantenimiento(String nombreMantenimiento) throws InterruptedException{
        while (visitantesActuales > 0 || mantenimientoActuales >= CAPACIDAD_MAXIMA || mantenimientosRealizados >= MANTENIMIENTOS_MAXIMOS || investigadoresActuales > 0 ){
            this.wait();
        }
        System.out.println(YELLOW_BOLD + nombreMantenimiento + " ingresa al observatorio." + RESET);
        mantenimientoActuales++;
        mantenimientosRealizados++;     // Cada persona de mantenimiento cuenta como un "mantenimiento realizado".
    }
    
    public void asearMuseo(String nombreMantenimiento) throws InterruptedException{
        System.out.println(nombreMantenimiento + " LIMPIAN2");
        Thread.sleep(6000); // Tiempo que tarda el empleado en asear.
    }
    
    public synchronized void salirMantenimiento(String nombreMantenimiento){
        System.out.println(nombreMantenimiento + " sale del observatorio.");
        mantenimientoActuales--;
        visitasRealizadas = 0;  // Se reinicia el contador.
        investigacionesRealizadas = 0;
        this.notifyAll();
    }
    
}

