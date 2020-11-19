/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4SeccionCritica.Ejercicio11;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) throws InterruptedException {
        Supermercado superM = new Supermercado();
        Cliente cliente1 = new Cliente("Cliente 1", new int[]{2, 2, 1, 5, 2, 3}, superM);
        Cliente cliente2 = new Cliente("Cliente 2", new int[]{1, 3, 5, 1, 1}, superM);
        
        Thread hiloC1 = new Thread(cliente1);
        Thread hiloC2 = new Thread(cliente2);
        
        hiloC1.start();
        hiloC2.start();
    }
    
    /*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
        Cajera, Clientes.
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
        Supermercado;
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
        Cuando un Cliente llega, se pone en la fila para ser atendido por al cajera (por ahora, es una sola).
*/

}
