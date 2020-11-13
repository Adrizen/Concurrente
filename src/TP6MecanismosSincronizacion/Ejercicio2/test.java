
package TP6MecanismosSincronizacion.Ejercicio2;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        int cantidadPersonas = 30;
        int cantidadJubilados = 0;
        GestorSala gestor = new GestorSala(2,1,25,30,1); // (int capMax, int capReduc, int tempActual,int umbral, int ciclosMaximos).
        
        Persona[] personas = new Persona[cantidadPersonas];
        Thread[] hilosPersonas = new Thread[cantidadPersonas];
        
        Persona[] personasJubiladas = new Persona[cantidadJubilados];
        Thread[] hilosJubilados = new Thread[cantidadJubilados];
        
        Medidor medidor = new Medidor(gestor);
        Thread m = new Thread(medidor, "Medidor");
        m.start();

        // Hilos Jubila2.
        for (int i = 0; i < personasJubiladas.length; i++) {
            personasJubiladas[i] = new Persona(true, gestor);
            hilosJubilados[i] = new Thread(personasJubiladas[i], "Jubilado" + i);
            hilosJubilados[i].start();
        }

        // Hilos Personas.
        for (int i = 0; i < personas.length; i++) {
            personas[i] = new Persona(false, gestor);
            hilosPersonas[i] = new Thread(personas[i], "Persona" + i);
            hilosPersonas[i].start();
        }
        
    }
    
}
