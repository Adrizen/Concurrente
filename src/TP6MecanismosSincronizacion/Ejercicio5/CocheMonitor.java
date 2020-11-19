
package TP6MecanismosSincronizacion.Ejercicio5;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class CocheMonitor implements Runnable{
    private String id;
    private String direccion;
    private GestionaTransitoMonitor gestor;
    
    public CocheMonitor(String i, String d, GestionaTransitoMonitor g){
        id = i;
        direccion = d;
        gestor = g;
    }

    public void run() {
        try {
            gestor.contarViajes(direccion);
            switch (direccion) {
                case "norte":
                    gestor.entrarCocheDelNorte(id);
                    gestor.cruzar(id, direccion);
                    gestor.salirCocheDelNorte(id);
                    break;
                case "sur":
                    gestor.entrarCocheDelSur(id);
                    gestor.cruzar(id, direccion);
                    gestor.salirCocheDelSur(id);
                    break;
            }
        } catch (InterruptedException e) { }
    }
}
