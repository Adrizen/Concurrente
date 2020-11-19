package pruebasPrimerParcial.hiloMensajero;

// A Java program to demonstrate working of 
// synchronized. 
import java.io.*; 
import java.util.*; 
  
// A Class used to send a message 
class Sender {

    public void send(String msg) {
        System.out.println("Sending\t" + msg);
        try {
            Thread.sleep(1000);
        } catch (Exception e) { }
        System.out.println("\n" + msg + "Sent");
    }

}
