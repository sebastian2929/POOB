package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * Write a description of class Franja here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Franja extends Artefact implements Thing, Serializable {
    protected char nextState;
    protected Color color;
    protected AManufacturing aManufactuing;
    protected int row, column;
    boolean active;

    /**
     * Constructor for objects of class Franja
     */
    public Franja(AManufacturing am, int row, int column, boolean active) {
        aManufactuing = am;
        this.row = row;
        this.column = column;
        this.active = active;
        state = (active ? Artefact.ACTIVE : Artefact.INACTIVE);
        nextState = (active ? Artefact.ACTIVE : Artefact.INACTIVE);
        aManufactuing.setThing(row, column, (Thing) this);
        color = Color.magenta;
    }

    public void decide() {
        int limiteFila = aManufactuing.getSize() - 1;

        if (row > 0 || row < limiteFila) {
            int superior = Math.max(0, row - 1);
            int inferior = Math.min(row + 1, limiteFila);
            int left = Math.max(0, column - 1);
            int right = Math.min(column + 1, limiteFila);
            Thing arribaDerecha = aManufactuing.getThing(superior, right);
            Thing abajoIzquierda = aManufactuing.getThing(inferior, left);
            if (arribaDerecha == null) {
                new Franja(aManufactuing, superior, right, active);
            }
            if (abajoIzquierda == null) {
                new Franja(aManufactuing, inferior, left, active);
            }
        }
    }

    /**
     * Returns the row
     * 
     * @return
     */
    public final int getRow() {
        return row;
    }

    /**
     * Returns tha column
     * 
     * @return
     */
    public final int getColumn() {
        return column;
    }

    /**
     * Change its actual state
     */
    public final void change() {
        step();
        state = nextState;
    }
}
