
package TP3Synchronized.Ejercicio3;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Prioridad {
    private char letraPrioritaria;
    
    public Prioridad(char letra){
        this.letraPrioritaria = letra;
    }
    
    public synchronized char getLetra(){
        return this.letraPrioritaria;
    }
    
    public synchronized void imprimir(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            System.out.println(letraPrioritaria);
        }
        this.cambiarPrioridad();
    }
    

    public synchronized void cambiarPrioridad(){
        switch(letraPrioritaria){
            case 'A': 
                letraPrioritaria = 'B';
                break;
            case 'B':
                letraPrioritaria = 'C';
                break;
            case 'C':
                letraPrioritaria = 'A';
                break;
        }
    }
}
