package TP6MecanismosSincronizacion.Ejercicio5;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class GestionaTransitoSemaforos {

    private Semaphore semPuente;
    private Semaphore semNorte;
    private Semaphore semSur;
    private boolean reclamado;

    private int cantidadCochesNorte;
    private int cantidadCochesSur;

    public GestionaTransitoSemaforos() {
        semPuente = new Semaphore(0, true);
        semNorte = new Semaphore(1, true);
        semSur = new Semaphore(1, true);
        reclamado = false;
        
        cantidadCochesNorte = 0;
        cantidadCochesSur = 0;
    }

    // Norte.
    public void entrarCocheDelNorte(String cocheID) throws InterruptedException {
        semNorte.acquire();
        cantidadCochesNorte++;
        if (!reclamado){
            semSur.acquire();
            reclamado = true;
        }
        semNorte.release();
    }

    public synchronized void salirCocheDelNorte(String cocheID) throws InterruptedException {
        System.out.println(cocheID + ": SALE DEL PUENTE DESDE EL NORTE");
        cantidadCochesNorte--;
        if (cantidadCochesNorte == 0) {   // Si soy el último, dejo pasar a los del sur.
            semSur.release();
            reclamado = false;
        }
    }

    // Sur.
    public void entrarCocheDelSur(String cocheID) throws InterruptedException {
        semSur.acquire();
        cantidadCochesSur++;
        if (!reclamado){
            semNorte.acquire();
            reclamado = true;
        }
        semSur.release();
    }

    public synchronized void salirCocheDelSur(String cocheID) {
        System.out.println(cocheID + ": SALE DEL PUENTE DESDE EL SUR");
        cantidadCochesSur--;
        if (cantidadCochesSur == 0){
            semNorte.release();
            reclamado = false;
        }
    }

    // Universal.
    public void cruzar(String cocheID, String direccion) throws InterruptedException {
        System.out.println(cocheID + ": EMPIEZA A CRUZAR EL PUENTE DESDE " + direccion);
        Thread.sleep(4000);  // Cruzan2.
    }

}
