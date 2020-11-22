
package Timestamp;
import java.sql.Timestamp;
/**
 *
 * @author Guillermo Andrés Pereyra.
 * Utilizar "import Timestamp.TSO;" en la clase a utilizar, luego "TSO.mostrarTiempo()" en el cartel a imprimir.
 * 
 */
public class TSO { // TimeStampOutput.
    private static Timestamp time;
    
    public static String mostrarTiempo(){   // Este método devuelve la Hora.Minutos.Segundos.Nanosegundos actuales en un String.
        String tiempo;
        time = new Timestamp(System.currentTimeMillis());
        tiempo = time.toString();
        tiempo = tiempo.substring(11);  // Recorto el String solo con lo que necesito.
        return tiempo;
    }
    
}
