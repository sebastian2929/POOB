package domain;

import java.awt.Color;

/**
 * Write a description of class Kriptonita here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Kriptonita extends Cell {

    private AManufacturing amanufacturing;

    /**
     * Constructor for objects of class Mimos
     */
    public Kriptonita(AManufacturing am, int row, int column, boolean active) {
        super(am, row, column, active);
        amanufacturing = am;
        color = Color.green;
    }

    @Override
    public void decide() {
        int limiteFila = amanufacturing.getSize() - 1;
        int limiteColumna = amanufacturing.getSize() - 1;
        int row = getRow();
        int column = getColumn();
        int posy = 0;
        for (int x = Math.max(0, row - 1); x <= Math.min(row + 1, limiteFila); x++) {
            if (x == row - 1) {
                for (int y = Math.max(0, column - 1); y <= Math.min(column + 1, limiteColumna); y++) {
                    verifcar(x, y);
                    posy = y;
                }
            }
            if (x == row) {
                verifcar(x, posy);
            }
            if (x == row + 1) {
                for (int y = Math.max(0, column + 1); y >= Math.min(column - 1, limiteColumna); y--) {
                    verifcar(x, y);

                }
            }
        }
        verifcar(row, column - 1);
    }

    public int shape() {
        return Thing.ROUND;
    }

    private void verifcar(int x, int y) {
        Thing vecina = null;
        if (x >= 0 && x < 50) {
            if (y >= 0 && y < 50) {
                vecina = amanufacturing.getThing(x, y);
            }
        }
        if (vecina != this) {
            if (vecina == null) {
                boolean active = false;
                if (x >= 0 && x < 50) {
                    if (y >= 0 && y < 50) {
                        Kriptonita kriptonita = new Kriptonita(amanufacturing, x, y, active);
                        amanufacturing.setThing(x, y, kriptonita);
                    }
                }
                // si no tiene vecino se crea una en esa posicion
            } else {
                nextState = 'a';
                // Si tiene vecinos la celula kriptonita se activa
            }
        }
    }
}
