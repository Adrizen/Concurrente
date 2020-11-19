
package TP6MecanismosSincronizacion.Ejercicio2;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class GestorSala {
    private final int CAPACIDAD_MAXIMA;     // Capacidad máxima del museo. (PDF: 50).
    private final int CAPACIDAD_REDUCIDA;   // Capacidad reducida si se llega al umbral de temperatura. (PDF: 35)
    private int capacidadActual;            // Capacidad actual del museo.
    private int visitantesActuales;         // Cantidad de visitantes que hay actualmente en el museo.
    private int temperaturaActual;          // Temperatura actual del museo.
    private final int UMBRAL;               // Umbral máximo de temperatura. (PDF: 30)
    private int cantidadJubiladosEntrar;    // Cantidad de jubilados que quieren entrar.
    private int CICLOS;                     // Indica cada cuantas personas se checkea la temperatura.
    private int ciclosActuales;             // Cantidad de ciclos actuales.
    private boolean reducida;               // Indica si la capacidad del museo está reducida.
    private final int TEMP_ACTUAL;          // Indica la temperatura inicial normal del museo.
    
    private int iteracionesHastaReestablecer;
    
    public GestorSala(int capMax, int capReduc, int tempActual,int umb, int ciclosMaximos){
        CAPACIDAD_MAXIMA = capMax;
        CAPACIDAD_REDUCIDA = capReduc;
        capacidadActual = capMax;
        TEMP_ACTUAL = tempActual;
        temperaturaActual = tempActual;
        visitantesActuales = 0;
        UMBRAL = umb;
        cantidadJubiladosEntrar = 0;
        CICLOS = ciclosMaximos;
        ciclosActuales = 0;
        reducida = false;
        
        iteracionesHastaReestablecer = 0;
        
    }
  // El ejercicio no especifica ciertas cosas con la temperatura, aquí está lo que yo asumí.
  // Nota: Ciclos: Ocurren cada vez que sale una Persona del museo. Al llegar a x ciclos, se checkea la temperatura.
  //      Checkeo: Por cada checkeo, la temperatura sube un punto y se la observa. Cuando se llega al umbral máximo, se reduce la capacidad del 
  //               museo.
     
    // Métodos de Persona.
    public synchronized void entrarSala() throws InterruptedException{
        while (visitantesActuales >= capacidadActual || cantidadJubiladosEntrar > 0 || ciclosActuales >= CICLOS){
            this.wait();
        }
        visitantesActuales++;   // La persona entró.
    }
    
    public synchronized void entrarSalaJubilado() throws InterruptedException{
        cantidadJubiladosEntrar++;
        while (visitantesActuales >= capacidadActual || ciclosActuales >= CICLOS){
            this.wait();
        }
        visitantesActuales++;
        cantidadJubiladosEntrar--;  // El jubilado entró.
        this.notifyAll();   // Avisa que entró.
    }
    
    public void visitar() throws InterruptedException{  // Agregado x mi.
        System.out.println(Thread.currentThread().getName() + ": COMIEZA A RECORRER EL MUSEO.");
        Thread.sleep(5000); // Tiempo que tarda en recorrer.
        //System.out.println(Thread.currentThread().getName() + ": TERMINA DE VISITAR.");
    }
    
    public synchronized void salirSala(){
        System.out.println(Thread.currentThread().getName() + ": SALE DEL MUSEO.");
        visitantesActuales--;
        ciclosActuales++;   // Aumenta un ciclo por persona que sale. Al llegar a CICLOS (que es el máximo) se checkea la temperatura.
        this.notifyAll();
    }
    
    // Métodos de Medidor.
    public synchronized void notificarTemperatura(int temperatura) throws InterruptedException{
        while (ciclosActuales < CICLOS){
            this.wait();    // Si no se cumplen los ciclos para el checkeo, espero.
        }
        temperaturaActual++;    // La temperatura sube un punto por cada checkeo.
        System.out.println("TEMPERATURA ACTUAL: " + temperaturaActual);
        if (!reducida && temperaturaActual >= UMBRAL){ // Si la cap. no está reducida y está por encima del umbral: REDUCIR CAPACIDAD.
            reducida = true;
            capacidadActual = CAPACIDAD_REDUCIDA;
            System.out.println("La temperatura actual es mayor o igual a " + UMBRAL + ", se redujo la capacidad del museo.");
        } else {    // Si está reducida y por debajo del umbral: VOLVER A LA CAPACIDAD ANTERIOR.
            if (reducida && iteracionesHastaReestablecer >= 3) {
                iteracionesHastaReestablecer = 0;
                reducida = false;
                temperaturaActual = TEMP_ACTUAL;  // Se reinicia la temperatura.
                capacidadActual = CAPACIDAD_MAXIMA;
                System.out.println("Se ha reestrablecido la capacidad total del museo.");
            } else {
                if (reducida){
                    iteracionesHastaReestablecer++;
                }
            }
        }
        ciclosActuales = 0;
        this.notifyAll();
    }

}
