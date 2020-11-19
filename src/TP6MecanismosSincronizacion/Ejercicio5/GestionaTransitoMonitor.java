
package TP6MecanismosSincronizacion.Ejercicio5;
import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class GestionaTransitoMonitor {
    private int CANTIDAD_MAXIMA;
    private int pasajesNortePorHacer;
    private int pasajesSurPorHacer;
    private int pasajesNorteRealizados;
    private int pasajesSurRealizados;
    private boolean reclamado;
    private String direccionReclamada;
    
    private int viajesNortePendientes;
    private int viajesSurPendientes;
    
    public GestionaTransitoMonitor(int maximoKirchner){
        CANTIDAD_MAXIMA = maximoKirchner;
        pasajesNorteRealizados = 0;
        pasajesSurRealizados = 0;
        reclamado = false;
        direccionReclamada = "";
        
        viajesNortePendientes = 0;
        viajesSurPendientes = 0;
    }
    
    public synchronized void contarViajes(String direccion){    // Parser.
        if (direccion.equals("norte")){
            viajesNortePendientes++;
        } else {
            viajesSurPendientes++;
        }
        //System.out.println("Norte: " + viajesNortePendientes + " Sur: " + viajesSurPendientes);
    }
    
    // Norte.
    public synchronized void entrarCocheDelNorte(String cocheID) throws InterruptedException{
        while (pasajesNortePorHacer >= CANTIDAD_MAXIMA || direccionReclamada.equals("sur")){
            this.wait();
        }
        pasajesNortePorHacer++;
        if (!reclamado){
            direccionReclamada = "norte";
            pasajesSurRealizados = 0;   // Reseteo el contador de sur, ya que al menos un pasaje norte se va a hacer.
            reclamado = true;
        }
    }
    
    public synchronized void salirCocheDelNorte(String cocheID){
        System.out.println(cocheID + ": SALE DEL PUENTE DESDE EL " + GREEN_BOLD + "NORTE." + RESET);
        pasajesNorteRealizados++;
        viajesNortePendientes--;
        if ((viajesNortePendientes == 0 || pasajesNorteRealizados == CANTIDAD_MAXIMA) && viajesSurPendientes > 0){
            direccionReclamada = "sur"; // Si se llegó a la cantidad máxima de viajes de un lado, ceder al otro.
            pasajesSurPorHacer = 0;
        } else {
            pasajesNortePorHacer = 0;   // Si llegué al límite, pero no hay viajes pendientes del sur y me quedan del norte, los hago.
        }
        this.notifyAll();
    }
    
    // Sur.
    public synchronized void entrarCocheDelSur(String cocheID) throws InterruptedException{
        while (pasajesSurPorHacer >= CANTIDAD_MAXIMA || direccionReclamada.equals("norte")){
            this.wait();
        }
        pasajesSurPorHacer++;
        if (!reclamado) {
            direccionReclamada = "sur";
            pasajesNorteRealizados = 0; // Reseteo el contador de norte. Porque al menos un pasaje de sur se va a hacer.
            reclamado = true;
        }
    }
    
    public synchronized void salirCocheDelSur(String cocheID){
        System.out.println(cocheID + ": SALE DEL PUENTE DESDE EL " + YELLOW_BOLD + " SUR." + RESET);
        pasajesSurRealizados++;
        viajesSurPendientes--;
        if ((viajesSurPendientes == 0 || pasajesSurRealizados == CANTIDAD_MAXIMA) && viajesNortePendientes > 0){
            direccionReclamada = "norte"; // Si se llegó a la cantidad máxima de viajes de un lado, ceder al otro.
            pasajesNortePorHacer = 0;
        } else {
            pasajesSurPorHacer = 0; // Si llegué al límite, pero no hay viajes pendientes del sur y me quedan del norte, los hago.
        }
        this.notifyAll();
    }
    
    // Ambos.
    public void cruzar(String cocheID, String direccion) throws InterruptedException{
        System.out.println(cocheID + ": CRUZA EL PUENTE DESDE EL " + direccion);
        Thread.sleep(4000);
    }
    
}
