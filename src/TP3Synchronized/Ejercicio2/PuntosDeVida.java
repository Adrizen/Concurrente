
package TP3Synchronized.Ejercicio2;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class PuntosDeVida {
    private int puntos = 10;
    
    public synchronized int getVida(){
        return this.puntos;
    }
    
    
    public synchronized void modificarVida(int cantidad){
        this.puntos = this.puntos + cantidad;
    }
    
    
    //Este método no está synchronizado pero los getters y setters si lo estén.
    public synchronized void accion(int cantidad) throws InterruptedException{ 
        System.out.println("La vida actual es de: " + this.getVida());
        System.out.println(Thread.currentThread().getName() + " afecta con "+ cantidad +" los puntos de vida del personaje");
        this.modificarVida(cantidad);
        System.out.println(Thread.currentThread().getName() + " llevó a cabo su tarea");
        System.out.println("La vida actual ahora es de: " + this.getVida());
    }
}
