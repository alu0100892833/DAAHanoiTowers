
import java.util.ArrayList;


/**
 * Esta clase será la plantilla para la creación de las distintas torres.
 * Cada instancia será una torre Hanoi
 * @author Óscar Darias Plasencia
 * @since 02/03/2017
 */
public class HanoiTower {

    private ArrayList<HanoiDisk> disks;
    private int maxDiscos;
    private String name;


    /**
     * Constructor básico. No recibe parámetros, no es la torre principal, y fija por defecto el máximo de discos a 3.
     */
    public HanoiTower() {
        this.disks = new ArrayList<HanoiDisk>();
        this.maxDiscos = 3;
        this.name = "";
    }

    /**
     * Constructor principal de la clase.
     * @param max Es n, el número de discos con el que se va a jugar.
     * @param inicial Indica si los discos comienzan en esta torre.
     */
    public HanoiTower(int max, String name, boolean inicial) {
        this.maxDiscos = max;
        this.name = name;
        this.disks = new ArrayList<HanoiDisk>();
        if (inicial)
            for (int i = max; i > 0; i--) {
                HanoiDisk newDisk = new HanoiDisk(i);
                this.disks.add(newDisk);
            }
    }

    /**
     * Este método permite extraer un disco de la torre. Comprueba que el número de discos en la torre no sea cero.
     * @return El disco situado en la "cima" de la torre. Este es eliminado de la misma.
     * @throws IndexOutOfBoundsException En el caso de que no haya discos en la torre.
     */
    public HanoiDisk pop() throws IndexOutOfBoundsException {
        if (this.disks.size() == 0)
            throw new IndexOutOfBoundsException("La torre de la que se intenta extraer está vacía. ");
        HanoiDisk result = this.getLast();
        this.disks.remove(disks.size() - 1);
        return result;
    }

    /**
     * Devuelve el tamaño del problema, es decir, el número de discos con el que se está jugando.
     * @return Número de discos del problema.
     */
    public int getMaxDiscos() {
        return this.maxDiscos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Permite obtener una referencia al disco situado más arriba en la torre.
     * @return Referencia al HanoiDisk situado más arriba en la torre.
     */
    public HanoiDisk getLast() {
        if (!this.disks.isEmpty())
            return this.disks.get(this.disks.size() - 1);
        else
            throw new IndexOutOfBoundsException("La torre a la que se intenta acceder está vacía. ");
    }

    /**
     * Este método mete un nuevo disco en la torre.
     * @param newDisk El nuevo disco que queremos introducir en la torre.
     * @throws Exception En el caso de que ya haya otro disco más pequeño en la torre.
     * @throws IndexOutOfBoundsException En el caso de que la torre no admita más discos.
     */
    public void push(HanoiDisk newDisk) throws Exception {
        if (this.disks.size() == this.maxDiscos)
            throw new IndexOutOfBoundsException("La torre a la que se intenta añadir un disco ya está llena. ");
        if (this.size() > 0)
            if (this.getLast().getSize() < newDisk.getSize())
                throw new Exception("Se ha intentado introducir un disco en una pila donde ya hay otro disco más pequeño. ");
        this.disks.add(newDisk);
    }

    public int size() {
        return this.disks.size();
    }

    public String toString() {
        String output = this.getName() + " = [ ";
        for (int i = 0; i < this.disks.size(); i++) {
            output += this.disks.get(i).getSize() + ", ";
        }
        return output + " ]";
    }
}
