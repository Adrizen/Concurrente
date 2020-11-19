package TP6MecanismosSincronizacion.Ejercicio8;


/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class CentroHemoterapia {
    private int CAMILLAS_MAXIMO;
    private int camillasActuales;
    private int REVISTAS_MAXIMO;
    private int revistasActuales;
    
    private Object revistas;
    
    public CentroHemoterapia(int numeroCamillas, int maximoRevistas){
        CAMILLAS_MAXIMO = numeroCamillas;
        camillasActuales = numeroCamillas;
        REVISTAS_MAXIMO = maximoRevistas;
        revistasActuales = maximoRevistas;
        revistas = new Object();
    }
    // Métodos de los Donantes.
    public synchronized void entrarDonante(String nombreDonante) throws InterruptedException {
        boolean tieneRevista = false;
        while (camillasActuales <= 0) {
            if (revistasActuales <= 0 && !tieneRevista) {   // No hay revistas y no tiene una. Entonces mira la tele.
                System.out.println(nombreDonante + " mira la tele.");
                this.wait();    // Espera a que se desocupe una revista.
            } else { 
                if (!tieneRevista) {    // Si hay revistas y no tiene agarra una.
                    revistasActuales--;
                    tieneRevista = true;
                    System.out.println(nombreDonante + " agarra una revista.");
                }
                this.wait();    // Si ya tenía revista, espera camilla. Si no tenía revista, agarrra una y espera una camilla también.
            }
        }
        if (tieneRevista) {  // Si tiene revista la devuelve, ya que consiguió una camilla.
            System.out.println(nombreDonante + ": Devuelve la revista.");
            revistasActuales++;
            this.notifyAll();   // Le avisa a sus bros que devolvió una revista.
        }
        System.out.println(nombreDonante + " consigue una camilla.");
        camillasActuales--;
    }

    public void donarSangre(String nombreDonante) throws InterruptedException{
        System.out.println(nombreDonante + ": COMIENZA SU EXTRACCIÓN.");
        Thread.sleep((int) (Math.random()*5000+2000));
        System.out.println(nombreDonante + ": TERMINA SU EXTRACCIÓN.");
    }
    
    public synchronized void salirDonante(String nombreDonante){
        System.out.println(nombreDonante + " se va del centro de hemoterapia.");
        camillasActuales++;
        this.notifyAll();
    }
    
}
