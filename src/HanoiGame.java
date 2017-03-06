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
        this.auxiliar = new HanoiTower(n, "Auxiliar", false);
        this.destination = new HanoiTower(n, "Destination", false);
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
     * @return Número de pasos acumulados.
     */
    private int run(int n, HanoiTower orig, HanoiTower aux, HanoiTower dest, boolean debug) {
        int steps = 0;
        try {
            if (n == 1) {
                dest.push(orig.pop());
                steps++;
                if (debug) this.showDebugInformation(orig, dest, true);
            } else {
                steps += this.run(n-1, orig, dest, aux, debug);
                dest.push(orig.pop());
                steps++;
                if (debug) this.showDebugInformation(orig, dest, true);
                steps += this.run(n-1, aux, orig, dest, debug);
                return steps;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return steps;
        }
        return steps;
    }

    /**
     * Este método muestra información del estado de las anillas y las torres.
     * Específico para el modo debug.
     * @param from Torre desde la que se ha movido una anilla.
     * @param to Torre hacia la que se ha movido una anilla.
     * @param wait Si es true, espera a que el usuario presione ENTER antes de mostrar cada paso
     */
    private void showDebugInformation(HanoiTower from, HanoiTower to, boolean wait) {
        try {
            System.out.println("---> Moviendo desde " + from.getName() + " hacia " + to.getName() + ".");
            System.out.println(this);
            System.out.println();
            if (wait)
                System.in.read();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método obtiene la solución mediante el algoritmo cíclico, que está implementado en otra función.
     */
    public void cyclicSolution(boolean debug) {
        int steps = this.clockWise(this.getTamProblema(), this.getDestination(), this.getOrigin(), this.getAuxiliar(), debug);
        System.out.println();
        System.out.println();
        System.out.println(this);
        System.out.println("Problema resuelto en " + steps + " pasos por el algoritmo cíclico.");
    }

    /**
     * Este método recursivo busca la solución al problema de forma cíclica, a favor de las agujas del reloj.
     * Esto quiere decir que los movimientos solo pueden ser X - Y - Z - X o X - Z - Y - X.
     * No solo se llama recursivamente a sí mismo sino al método de movimientos contrarios a las agujas del reloj para los movimientos X - Z - Y - X.
     * @param n Tamaño del subproblema.
     * @param xTower La torre origen.
     * @param yTower La torre auxiliar.
     * @param zTower La torre de destino.
     * @return Número de pasos acumulado.
     */
    private int clockWise(int n, HanoiTower xTower, HanoiTower yTower, HanoiTower zTower, boolean debug) {
        int steps = 0;
        try {
            if (n > 0) {
                steps += this.antiClockWise(n-1, xTower, zTower, yTower, debug);
                yTower.push(xTower.pop());
                if (debug)
                    this.showDebugInformation(xTower, yTower, false);
                steps++;
                steps += this.antiClockWise(n-1, zTower, yTower, xTower, debug);
                return steps;
            }
            return steps;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return steps;
    }

    /**
     * Este método recursivo se complementa con el método clockWise() para resolver el problema de forma cíclica.
     * Realiza movimientos contrarios a las agujas del reloj: X - Z - Y - X, mientras el otro método se movía X - Y - Z - X.
     * @param n Tamaño del subproblema.
     * @param xTower Torre de partida.
     * @param yTower Torre intermedia.
     * @param zTower Torre de destino.
     * @return Número de pasos acumulado.
     */
    private int antiClockWise(int n, HanoiTower xTower, HanoiTower yTower, HanoiTower zTower, boolean debug) {
        int steps = 0;
        try {
            if (n > 0) {
                steps += this.antiClockWise(n-1, xTower, yTower, zTower, debug);
                zTower.push(xTower.pop());
                if (debug)
                    this.showDebugInformation(xTower, zTower, false);
                steps++;
                steps += this.clockWise(n-1, yTower, xTower, zTower, debug);
                yTower.push(zTower.pop());
                if (debug)
                    this.showDebugInformation(zTower, yTower, false);
                steps++;
                steps += this.antiClockWise(n-1, xTower, yTower, zTower, debug);
            }
            return steps;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return steps;
    }
}











//END
