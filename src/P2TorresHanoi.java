import java.io.IOException;

/**
 * Clase principal del programa de las torres de Hanoi.
 * @author Óscar Darias Plasencia
 * @since 02 March 2017
 */
public class P2TorresHanoi {
    public static void main (String[] args) {
        try {
            int tamanio = Integer.parseInt(args[0]);
            boolean debug;
            if (Boolean.parseBoolean(args[1]))
                debug = true;
            else
                debug = false;

            HanoiGame hanoi = new HanoiGame(tamanio);
            hanoi.solve(debug);
            System.out.println("PULSE ENTER PARA EJECUTAR EL ALGORITMO CÍCLICO. SE MOVERÁN LOS DISCOS DE NUEVO AL ORIGEN. ");
            System.in.read();
            hanoi.cyclicSolution(debug);

        } catch(IndexOutOfBoundsException|NumberFormatException e) {
            System.out.println("Debe introducir como parámetro el tamaño del problema.\n\n");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
