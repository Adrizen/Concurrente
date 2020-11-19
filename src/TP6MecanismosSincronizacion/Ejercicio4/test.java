
package TP6MecanismosSincronizacion.Ejercicio4;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Cuartel cuartel = new Cuartel(1,2,2,2); // (capacidad, cantMostrAlm, cantMostrPos, cantAbrid).
        
        System.out.println("Nombre -   quiereGaseosa - quierePostre");
        for (int i = 0; i < 2; i++) {
            double random = Math.random();  // Usado para obtener true/false aleatorio en quierePostre y quiereGaseosa.
                        // Para que sea random usar la lógica de aca abajo.
                        //System.out.println("Soldado"+i+"   " +(random < 0.25)+"           "+(random < 0.75));
            System.out.println("Soldado"+i+"   " +true+"           "+true);
            new Thread(new Soldado("Soldado"+i,true,true,cuartel)).start();
        }

    }
    
}
