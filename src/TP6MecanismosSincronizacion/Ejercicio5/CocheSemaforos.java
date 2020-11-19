
package TP6MecanismosSincronizacion.Ejercicio5;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class CocheSemaforos implements Runnable{
    private String id;
    private String direccion;
    private GestionaTransitoSemaforos gestor;
    
    public CocheSemaforos(String i, String d, GestionaTransitoSemaforos g){
        id = i;
        direccion = d;
        gestor = g;
    }
    
    public void run() {
        try {
            switch (direccion) {
                case "norte":
                    gestor.entrarCocheDelNorte(id);
                    gestor.cruzar(id,direccion);
                    gestor.salirCocheDelNorte(id);
                    break;
                case "sur":
                    gestor.entrarCocheDelSur(id);
                    gestor.cruzar(id,direccion);
                    gestor.salirCocheDelSur(id);
                    break;
            }
        } catch (InterruptedException e) { }
    }
}
