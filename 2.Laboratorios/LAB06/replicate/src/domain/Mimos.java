package domain;

import java.awt.Color;

/**
 * Write a description of class Mimos here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mimos extends Cell {
    private AManufacturing amanufacturing;

    /**
     * Constructor for objects of class Mimos
     */
    public Mimos(AManufacturing am, int row, int column, boolean active) {
        super(am, row, column, active);
        amanufacturing = am;
        color = Color.orange;
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

    private void verifcar(int x, int y) {
        Thing vecina = null;
        vecina = amanufacturing.getThing(x, y);
        if (vecina != this) {
            if (vecina != null && !vecina.isActive()) {
                nextState = 'd';
                // Si alguna de las vecinas esta inactiva la celula mimos se inactiva
            }
            if (vecina != null && vecina.isActive()) {
                nextState = 'a';
                // Si alguna de las vecinas esta activa la celula mimos se activa
            }
        }
    }
}
