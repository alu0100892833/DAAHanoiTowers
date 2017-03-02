import java.io.IOException;

/**
 * Clase que representa el juego de las torres de Hanoi. Tiene instanciadas las tres torres como atributos.
 * @author Óscar Darias Plasencia
 * @since 02 March 2017
 */
public class HanoiGame {

    private HanoiTower origin, auxiliar, destination;
    private int tamProblema;


    /**
     * Constructor sin parámetros. Inicializa por defecto el juego con n=3 discos.
     */
    public HanoiGame() {
        this.tamProblema = 3;
        this.origin = new HanoiTower(3, "", true);
        this.auxiliar = new HanoiTower();
        this.destination = new HanoiTower();
    }

    /**
     * Constructor con parámetros. Inicializa el juego según lo indicado.
     * @param n Tamaño del juego, es decir, el número de discos con el que se va a jugar.
     */
    public HanoiGame(int n) {
        this.tamProblema = n;
        this.origin = new HanoiTower(n, "Origin", true);
        this.auxiliar = new HanoiTower(3, "Auxiliar", false);
        this.destination = new HanoiTower(3, "Destination", false);
    }

    public HanoiTower getOrigin() {
        return origin;
    }

    public HanoiTower getAuxiliar() {
        return auxiliar;
    }

    public HanoiTower getDestination() {
        return destination;
    }

    public void setOrigin(HanoiTower origin) {
        this.origin = origin;
    }

    public void setAuxiliar(HanoiTower auxiliar) {
        this.auxiliar = auxiliar;
    }

    public int getTamProblema() {
        return this.tamProblema;
    }

    @Override
    public String toString() {
        String output = this.getOrigin().toString() + "\n";
        output += this.getAuxiliar().toString() + "\n";
        output += this.getDestination().toString() + "\n";
        return output;
    }

    /**
     * Método que invoca a otro método que resuelve el problema. El motivo de tener esta función intermedia es poder pasar como parámetros a las torres en distinto orden según convenga en la recursividad.
     * Este método está destinado a ser llamado desde el exterior.
     * @param debug Ejecuta paso a paso o de forma directa, respectivamente, si es true o false. Simplemente se pasa al método run().
     */
    public void solve(boolean debug) {
        int steps = this.run(this.tamProblema, this.origin, this.auxiliar, this.destination, debug);
        System.out.println();
        System.out.println();
        System.out.println(this);
        System.out.println("Problema resuelto en " + steps + " pasos.");
    }

    /**
     * Algoritmo que resuelve el problema de las torres de Hanoi para el tamaño planteado.
     * @param debug Ejecuta paso a paso o de forma directa, respectivamente, si es true o false.
     */
    private int run(int n, HanoiTower orig, HanoiTower aux, HanoiTower dest, boolean debug) {
        int steps = 0;
        try {
            if (n == 1) {
                dest.push(orig.pop());
                steps++;
                if (debug) this.showDebugInformation(orig, dest);
            } else {
                steps += this.run(n-1, orig, dest, aux, debug);
                dest.push(orig.pop());
                steps++;
                if (debug) this.showDebugInformation(orig, dest);
                steps += this.run(n-1, aux, orig, dest, debug);
                return steps;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return steps;
        }
        return steps;
    }

    private void showDebugInformation(HanoiTower from, HanoiTower to) {
        try {
            System.out.println("---> Moviendo desde " + from.getName() + " hacia " + to.getName() + ".");
            System.out.println(this);
            System.out.println();
            System.in.read();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}











//END
