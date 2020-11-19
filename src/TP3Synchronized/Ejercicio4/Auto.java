
package TP3Synchronized.Ejercicio4;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Auto extends Vehiculo implements Runnable{
    
    private Surtidor surtidor;
    
    public Auto(String p, int m, String mar, int capaC, Surtidor surtidor){
        super(p,m,mar,capaC);
        this.surtidor = surtidor;
    }
    
    public void andar(){
        this.litrosCombustible = 10; //El auto ahora está en reserva y debe cargar combustible.
    }
    
    public void run(){
        System.out.println(Thread.currentThread().getName() + " comienza a andar");
        try{
            Thread.sleep(2000); //Simula el tiempo que tarde el auto en andar y llegar a reserva
        } catch (InterruptedException e) {  }
        this.andar();
        System.out.println(Thread.currentThread().getName() + " está en reserva");
        System.out.println(Thread.currentThread().getName() + " está esperando su turno en el surtidor");
        surtidor.llenarTanque();
        this.litrosCombustible = this.capacidadCombustible;
        System.out.println(Thread.currentThread().getName() + " cargó combustible y libera el surtidor");
    }
    
    
    
}
