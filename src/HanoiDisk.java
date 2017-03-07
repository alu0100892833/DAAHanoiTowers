/**
 * Clase muy básica que representa los discos del juego de las torres de Hanoi.
 * Tienen un atributo size que representa su tamaño.
 */
public class HanoiDisk {

    private int size;

    public HanoiDisk(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
