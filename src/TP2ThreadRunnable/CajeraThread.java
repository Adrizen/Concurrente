
package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class CajeraThread extends Thread{
    private String nombre;
    private Cliente cliente;
    private long initialTime;
    
    public CajeraThread(String nom, Cliente cli, long time){
        this.nombre = nom;
        this.cliente = cli;
        this.initialTime = time;
    }
    
    public void run(){
        System.out.println("La cajera "+this.nombre+" COMIENZA A PROCESARO LA COMPRA DEL CLIENTE "+this.cliente.getNombre()+" EN EL TIEMPO: "+(System.currentTimeMillis()-this.initialTime)/1000+" seg");
        
        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            this.esperarXSegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesando el producto "+(i+1)+" del cliente "+this.cliente.getNombre()+" ->Tiempo: "+(System.currentTimeMillis()-this.initialTime)/1000+" seg");
        }
        
        System.out.println("La cajera "+this.nombre+" HA TERMINADO DE PROCESAR "+this.cliente.getNombre()+" EN EL TIEMPO: "+(System.currentTimeMillis()-this.initialTime)/1000+" seg");
    }
    
   public void esperarXSegundos(int segundosProducto){
        try{
            Thread.sleep(segundosProducto*1000);
        } catch(InterruptedException e) {  }
    }
}
